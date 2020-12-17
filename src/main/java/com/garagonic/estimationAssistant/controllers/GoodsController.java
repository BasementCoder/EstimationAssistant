package com.garagonic.estimationAssistant.controllers;

import com.garagonic.estimationAssistant.UIEntities.UIGoods;
import com.garagonic.estimationAssistant.common.ErrorCodes;
import com.garagonic.estimationAssistant.repository.Goods;
import com.garagonic.estimationAssistant.service.GoodsService;
import com.garagonic.estimationAssistant.tools.Fn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/")
    public ModelAndView searchAdd(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("MainAppPage");
        if (request.getParameter("search") != null) {
            Goods goods = search(request, response);
            try {
                List<Goods> goodsList = goodsService.getGoodsList(goods);
                mv.addObject("goodsList", goodsList);
                mv.addObject("lastGoodsSearchFilter", new UIGoods(goods));
                request.getSession().setAttribute("lastGoodsSearchFilter", goods);
            } catch (RuntimeException re) {
                if (ErrorCodes.EMPTY_SEARCH.equals(re.getMessage())) {
                    mv.addObject("showErrorMessage", Boolean.TRUE);
                    mv.addObject("lastGoodsSearchFilter", new UIGoods(goods));
                } else {
                    throw re;
                }
            }
            mv.setViewName("MainAppPage");
        } else if (request.getParameter("addGoods") != null) {
            mv.setViewName("addGoodsPage");
        } else {
            Object lastGoodsSearchFilter = request.getSession().getAttribute("lastGoodsSearchFilter");
            if (lastGoodsSearchFilter != null) {
                Boolean inStockOnly = (Boolean) request.getSession().getAttribute("inStockOnly");
                List<Goods> goodsList = goodsService.getGoodsList((Goods) lastGoodsSearchFilter);
                mv.addObject("goodsList", goodsList);
                mv.addObject("lastGoodsSearchFilter", new UIGoods((Goods) lastGoodsSearchFilter));
                mv.addObject("inStockOnly", inStockOnly);
            }
        }
        return mv;
    }

    @RequestMapping(value = "/getImage/{id}")
    public void getImage(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id) throws IOException {
        Goods goods = goodsService.getGoods(id);
        byte[] image = goods.getImage();
        if (image != null) {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(image);
        }
    }

    @RequestMapping(value = "/addGoodsPage")
    public ModelAndView AddGoodsPage(HttpServletRequest request) {
        Goods goods;
        boolean errorsPresent = false;
        ModelAndView mv = new ModelAndView();

        mv.setViewName("addGoodsPage");
        if (request.getParameter("cancel") != null) {
            mv.setViewName("redirect:/");
        } else if (request.getParameter("addGoods") != null) {
            goods = getGoodsFromRequest(request, 0);
                goodsService.addGoods(goods);
            if (!errorsPresent) {
                mv.addObject("addedGoods", null);
                List<Goods> goodsList = goodsService.getGoodsList(goods);
                mv.addObject("goodsList", goodsList);
                mv.addObject("inStockOnly", Boolean.FALSE);
                request.getSession().setAttribute("inStockOnly", Boolean.FALSE);
                request.getSession().setAttribute("lastGoodsSearchFilter", goods);
                mv.setViewName("MainAppPage");
            }
        }
        return mv;
    }

    private Goods getGoodsFromRequest(HttpServletRequest request, int id) {
        Goods goods = new Goods();
        goods.setId(id);

//        goods.setImage(convertParameterToInt(request, "image"));

        goods.setName(request.getParameter("name"));
        goods.setManualDateInput(request.getParameter("manualDateInput"));
        goods.setQuote(convertParameterToInt(request,"quote"));
        goods.setEstimate(convertParameterToDouble(request,"estimate"));
        goods.setType(request.getParameter("type"));
        Part imagePart = null;
        try {
            imagePart = request.getPart("image");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        InputStream inputStream = null;
        if (imagePart != null) {
            long fileSize = imagePart.getSize();
            String fileContent = imagePart.getContentType();
            try {
                byte[] imageBites = new byte[(int) fileSize];
                inputStream = imagePart.getInputStream();
                inputStream.read(imageBites);
                goods.setImage(imageBites);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return goods;
    }


    private Integer convertParameterToInt(HttpServletRequest request, String parameterName) {
        String parameter = request.getParameter(parameterName);
        return Fn.isStringEmpty(parameter) ? 0 : parseInt(parameter);
    }

    private Double convertParameterToDouble(HttpServletRequest request, String parameterName) {
        String parameter = request.getParameter(parameterName);
        return Fn.isStringEmpty(parameter) ? 0 : parseDouble(parameter);
    }

    private Goods search(HttpServletRequest request, HttpServletResponse response) {
        return getGoodsFromRequest(request, 0);
    }

    @RequestMapping(value = "/{id}/delete")
    public ModelAndView deleteGoods(HttpServletRequest request, @PathVariable("id") int id) {
        goodsService.removeGoods(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:../");
        return mv;
    }

    @RequestMapping(value = "/{id}/edit")
    public ModelAndView openEditGoodsPage(@PathVariable("id") long id) {
        Goods goods = goodsService.getGoods((int) id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("goods", new UIGoods(goods));
        mv.setViewName("editGoodsPage");
        return mv;
    }

    @RequestMapping(value = "/{id}/edit/submit")
    public ModelAndView editGoods(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) {
        boolean errorsPresent = false;
        ModelAndView mv = new ModelAndView();
        if (request.getParameter("submit") != null) {
            Goods editedGoods = getGoodsFromRequest(request, id);
            try {
                goodsService.updateGoods(editedGoods);
            } catch (RuntimeException e) {
               //TODO add exception handling
            }
        }

        if (!errorsPresent) {
            mv.setViewName("redirect:../../");
        }
        return mv;
    }
}
