package com.garagonic.estimationAssistant.service;

import com.garagonic.estimationAssistant.repository.Goods;
import com.garagonic.estimationAssistant.repository.GoodsRepository;
import com.garagonic.estimationAssistant.tools.Fn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    public void addGoods(Goods goods) {
        if (goods != null) {
            goodsRepository.save(goods);
        } else {
            throw new RuntimeException("Goods cannot be null.");
        }
    }

    public void updateGoods(Goods updateDataGoods) {
        if (updateDataGoods != null) {
            Goods goods = goodsRepository.getOne(updateDataGoods.getId());
            goods.setPo(updateDataGoods.getPo());
            goods.setWo(updateDataGoods.getWo());
            goods.setSo(updateDataGoods.getSo());
            goods.setCustomer(updateDataGoods.getCustomer());
            goods.setTitle(updateDataGoods.getTitle());
            goods.setRack(updateDataGoods.getRack());
            goods.setShelf(updateDataGoods.getShelf());
            goods.setShelfPosition(updateDataGoods.getShelfPosition());

            goodsRepository.save(goods);
        }
    }

    public List<Goods> getGoodsList(Goods goods) {

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIncludeNullValues().withIgnorePaths("id", "comment", "inDate", "outDate", "barcode", "inStock" );

        if (goods != null) {
            if (goods.getPo() == 0) {
                exampleMatcher = exampleMatcher.withIgnorePaths("po");
            }
            if (goods.getWo() == 0) {
                exampleMatcher = exampleMatcher.withIgnorePaths("wo");
            }
            if (goods.getSo() == 0) {
                exampleMatcher = exampleMatcher.withIgnorePaths("so");
            }
            if (Fn.isStringEmpty(goods.getCustomer())) {
                exampleMatcher = exampleMatcher.withIgnorePaths("customer");
            }
            if (Fn.isStringEmpty(goods.getTitle())) {
                exampleMatcher = exampleMatcher.withIgnorePaths("title");
            }
            if (Fn.isStringEmpty(goods.getRack())) {
                exampleMatcher = exampleMatcher.withIgnorePaths("rack");
            }
            if (Fn.isStringEmpty(goods.getShelf())) {
                exampleMatcher = exampleMatcher.withIgnorePaths("shelf");
            }
            if (goods.getShelfPosition() == 0) {
                exampleMatcher = exampleMatcher.withIgnorePaths("shelfPosition");
            }

//             TODO Add search by stock availability option
//        if (onlyCurrentGoods) {
//            searchResults = findCurrentGoods(searchResults);
//        }
        }
        else {
            goods = new Goods();
        }

        return goodsRepository.findAll(Example.of(goods, exampleMatcher));
    }

    public Goods getGoods(int id) {
        return goodsRepository.findById(id).orElse(null);
    }

    public void removeGoods(int id) {
        Goods goods = goodsRepository.findById(id).orElse(null);
        if (goods != null) {
            goods.setOutDate(new Date());
            goods.setInStock(false);
            goodsRepository.save(goods);
        }
    }


}
