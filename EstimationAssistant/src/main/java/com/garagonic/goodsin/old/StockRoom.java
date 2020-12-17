package com.garagonic.estimationAssistant.old;

import com.garagonic.estimationAssistant.repository.Goods;
import com.garagonic.estimationAssistant.tools.Fn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StockRoom implements Serializable {

    private List<Goods> goodsList = new ArrayList();

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List goodsList) {
        this.goodsList = goodsList;
    }

    public void addGoods(Goods goods) {
        goodsList.add(goods);
    }


    public boolean removeGoodsByLocation(String location, List<Goods> currentGoods) {
        boolean removedSuccessfully = false;
        List<Goods> searchResults = findByLocation(location, currentGoods);
        if (!searchResults.isEmpty()) {
            searchResults.get(0).setOutDate(Calendar.getInstance().getTime());
            removedSuccessfully = true;
        }
        return removedSuccessfully;
    }

    public List<Goods> findGoods(
            int po,
            int wo,
            int so,
            String customer,
            String title,
            String location,
            boolean onlyCurrentGoods) {
        List<Goods> searchResults = goodsList;

        if (po != 0) {
            searchResults = findByPO(po, searchResults);
        }
        if (wo != 0) {
            searchResults = findByWO(wo, searchResults);
        }
        if (so != 0) {
            searchResults = findBySO(so, searchResults);
        }
        if (Fn.isStringPopulated(customer)) {
            searchResults = findByCustomer(customer, searchResults);
        }
        if (Fn.isStringPopulated(title)) {
            searchResults = findByTitle(title, searchResults);
        }
        if (Fn.isStringPopulated(location)) {
            searchResults = findByLocation(location, searchResults);
        }
        if (onlyCurrentGoods) {
            searchResults = findCurrentGoods(searchResults);
        }

        return searchResults;
    }

    private List<Goods> findByPO(int po, List<Goods> searchedGoodsList) {
        List<Goods> searchResults = new ArrayList<>();
        for (Goods goods : searchedGoodsList) {

            if (goods.getPo() == po) {
                searchResults.add(goods);
            }
        }
        return searchResults;
    }

    private List<Goods> findByWO(int wo, List<Goods> searchedGoodsList) {
        List<Goods> searchResults = new ArrayList<>();
        for (Goods goods : searchedGoodsList) {

            if (goods.getWo() == wo) {
                searchResults.add(goods);
            }
        }
        return searchResults;
    }

    private List<Goods> findBySO(int so, List<Goods> searchedGoodsList) {
        List<Goods> searchResults = new ArrayList<>();
        for (Goods goods : searchedGoodsList) {

            if (goods.getSo() == so) {
                searchResults.add(goods);
            }
        }
        return searchResults;
    }

    private List<Goods> findByCustomer(String customer, List<Goods> searchedGoodsList) {
        List<Goods> searchResults = new ArrayList<>();
        for (Goods goods : searchedGoodsList) {

            if (customer.equalsIgnoreCase(goods.getCustomer())) {
                searchResults.add(goods);
            }
        }
        return searchResults;
    }

    private List<Goods> findByTitle(String title, List<Goods> searchedGoodsList) {
        List<Goods> searchResults = new ArrayList<>();
        for (Goods goods : searchedGoodsList) {

            if (title.equalsIgnoreCase(goods.getTitle())) {
                searchResults.add(goods);
            }
        }
        return searchResults;
    }

    public List<Goods> findCurrentGoodsByLocation(String location, List<Goods> searchedGoodsList) {
        return (findByLocation(location, findCurrentGoods(searchedGoodsList)));
    }

    private List<Goods> findByLocation(String location, List<Goods> searchedGoodsList) {
        List<Goods> searchResults = new ArrayList<>();
        for (Goods goods : searchedGoodsList) {

            if (location.equalsIgnoreCase(goods.getLocation())) {
                searchResults.add(goods);
            }
        }
        return searchResults;
    }

    private List<Goods> findCurrentGoods(List<Goods> searchedGoodsList) {
        List<Goods> searchResults = new ArrayList<>();
        for (Goods goods : searchedGoodsList) {

            if (goods.getOutDate() == null) {
                searchResults.add(goods);
            }
        }
        return searchResults;
    }

}

