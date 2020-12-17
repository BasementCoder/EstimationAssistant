package com.garagonic.estimationAssistant.controllers;

import com.garagonic.estimationAssistant.repository.Goods;
import com.garagonic.estimationAssistant.service.GoodsService;
import com.garagonic.estimationAssistant.tools.Fn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static java.lang.Integer.parseInt;

@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/")
    public ModelAndView searchAdd(HttpServletRequest request, HttpServletResponse response) {
        Goods goods;
        ModelAndView mv = new ModelAndView();
        mv.setViewName("goodsInPage");
        if (request.getParameter("search") != null) {
            goods = search(request, response);
            List<Goods> goodsList = goodsService.getGoodsList(goods);
            mv.addObject("goodsList", goodsList);
            mv.setViewName("goodsInPage");
        } else if (request.getParameter("addGoods") != null) {
            mv.setViewName("addGoodsPage");
        }
        return mv;
    }

    @RequestMapping(value = "/addGoodsPage")
    public ModelAndView AddGoodsPage(HttpServletRequest request, HttpServletResponse response) {
        Goods goods = new Goods();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("addGoodsPage");
        if (request.getParameter("cancel") != null) {
            mv.setViewName("goodsInPage");
        } else if (request.getParameter("addGoods") != null) {
            goods = add(request,response);
            List<Goods> goodsList = goodsService.getGoodsList(goods);
            mv.addObject("goodsList", goodsList);
            mv.setViewName("goodsInPage");
        }
        return mv;
    }

        private Goods add(HttpServletRequest request, HttpServletResponse response) {
        Goods goods = getGoodsFromRequest(request, 0);

        if (goods.getTitle() == null || goods.getRack() == null || goods.getShelf() == null || goods.getShelfPosition() == 0) {
            return null;
        } else {
            goodsService.addGoods(goods);
            return goods;
        }
//        return goods;
    }

    private Goods getGoodsFromRequest(HttpServletRequest request, int id) {
        Goods goods = new Goods();
        goods.setId(id);
        goods.setPo(convertParameterToInt(request, "po"));
        goods.setWo(convertParameterToInt(request, "wo"));
        goods.setSo(convertParameterToInt(request, "so"));
        goods.setCustomer(request.getParameter("customer"));
        goods.setTitle(request.getParameter("title"));
        goods.setRack(request.getParameter("rack"));
        goods.setShelf(request.getParameter("shelf"));
        goods.setShelfPosition(convertParameterToInt(request, "shelfPosition"));

        /**
         *  default values
         */
        goods.setInDate(new java.util.Date());
        goods.setInStock(true);

        return goods;
    }

    private int convertParameterToInt(HttpServletRequest request, String parameterName) {
        String parameter = request.getParameter(parameterName);
        return Fn.isStringEmpty(parameter) ? 0 : parseInt(parameter);
    }

    private Goods search(HttpServletRequest request, HttpServletResponse response) {
        Goods goods = getGoodsFromRequest(request, 0);
        return goods;
    }

    @RequestMapping(value = "/{id}/delete")
    public ModelAndView deleteGoods(HttpServletRequest request, @PathVariable("id") int id) {
        goodsService.removeGoods(id);
        List<Goods> goodsList = goodsService.getGoodsList(null);
        ModelAndView mv = new ModelAndView();
        mv.addObject("goodsList", goodsList);
        mv.setViewName("redirect:../");
        return mv;
    }

    @RequestMapping(value = "/{id}/edit")
    public ModelAndView openEditGoodsPage(@PathVariable("id") int id) {
        Goods goods = goodsService.getGoods(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("goods", goods);
        mv.setViewName("editGoodsPage");
        goodsService.addGoods(goods);
        return mv;
    }

    @RequestMapping(value = "/{id}/edit/submit")
    public ModelAndView editGoods(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("submit") != null) {
            Goods editedGoods = getGoodsFromRequest(request, id);
            goodsService.updateGoods(editedGoods);
        }

        List<Goods> goodsList = goodsService.getGoodsList(null);
        ModelAndView mv = new ModelAndView();
        mv.addObject("goodsList", goodsList);
        mv.setViewName("redirect:../../");
        return mv;
    }
}
