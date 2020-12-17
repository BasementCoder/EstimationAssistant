package com.garagonic.estimationAssistant.service;

import com.garagonic.estimationAssistant.common.ErrorCodes;
import com.garagonic.estimationAssistant.repository.Goods;
import com.garagonic.estimationAssistant.repository.GoodsRepository;
import com.garagonic.estimationAssistant.tools.Fn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service
public class GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    public void addGoods(Goods goods) {
            goodsRepository.save(goods);
    }

    public void updateGoods(Goods updateDataGoods) {
        if (updateDataGoods != null) {
            Goods goods = goodsRepository.getOne(updateDataGoods.getId());

//            goods.setImagePath(updateDataGoods.getImagePath());
            goods.setImage(updateDataGoods.getImage());
            goods.setName(updateDataGoods.getName());
            goods.setManualDateInput(updateDataGoods.getManualDateInput());
            goods.setInDate(updateDataGoods.getInDate());
            goods.setQuote(updateDataGoods.getQuote());
            goods.setEstimate(updateDataGoods.getEstimate());
            goods.setType(updateDataGoods.getType());

            Set<ConstraintViolation<Goods>> constraintViolations = Fn.getEntityValidator().validate(goods);
            if (constraintViolations.size() > 0) {
                throw new RuntimeException(constraintViolations.iterator().next().getMessage());

            }
            goodsRepository.save(goods);
        }
    }

//    public List<Goods> getGoodsList(Goods goods) {
//        return getGoodsList(goods);
//    }

    public List<Goods> getGoodsList(Goods goods) {

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIncludeNullValues().withIgnorePaths("id", "inDate","image");
        boolean filterAdded = false;
        if (goods != null) {

            if (goods.getImage() == null) {
                exampleMatcher = exampleMatcher.withIgnorePaths("image");
            } else { filterAdded = true; }

            if (Fn.isStringEmpty(goods.getName())) {
                exampleMatcher = exampleMatcher.withIgnorePaths("name");
            } else { filterAdded = true; }
            
            if (goods.getQuote() == 0) {
                exampleMatcher = exampleMatcher.withIgnorePaths("quote");
            } else { filterAdded = true; }

            if (goods.getEstimate() == 0) {
                exampleMatcher = exampleMatcher.withIgnorePaths("estimate");
            } else { filterAdded = true; }

            if (Fn.isStringEmpty(goods.getManualDateInput())) {
                exampleMatcher = exampleMatcher.withIgnorePaths("manualDateInput");
            } else { filterAdded = true; }

            if (Fn.isStringEmpty(goods.getType())) {
                exampleMatcher = exampleMatcher.withIgnorePaths("type");
            } else { filterAdded = true; }

//            if (inStockOnly != null && inStockOnly) {
//                goods.setInStock(true);
//            }
//            else {
//                exampleMatcher = exampleMatcher.withIgnorePaths("inStock");
//            }
        }
        if (!filterAdded) {
            throw new RuntimeException(ErrorCodes.EMPTY_SEARCH);
        }

        return goodsRepository.findAll(Example.of(goods, exampleMatcher));
    }

    public Goods getGoods(int id) {
        return goodsRepository.findById(id).orElse(null);
    }

    public void removeGoods(int id) {
        Goods goods = goodsRepository.findById(id).orElse(null);
        if (goods != null) {
//            goods.setOutDate(new Date());
//            goods.setInStock(false);
            goodsRepository.deleteById(goods.getId());
        }
    }


}
