package com.garagonic.estimationAssistant.UIEntities;

import com.garagonic.estimationAssistant.repository.Goods;

import java.util.Date;

public class UIGoods {

    private int id;

    private byte[] image;
    private String name;
    private Date inDate;
    private String manualDateInput;
    private Integer quote;
    private Double estimate;
    private String type;

    public UIGoods(Goods goods) {
        this.id = goods.getId();
        this.image = goods.getImage();
        this.name = goods.getName();
        this.quote = mapIntToInteger(goods.getQuote());
        this.manualDateInput = goods.getManualDateInput();
        this.inDate = goods.getInDate();
        this.estimate =mapDoubleToDoubleObject(goods.getEstimate());
        this.type = goods.getType();
    }

    public Goods goodsConverter(){
        Goods goods = new Goods();
        goods.setId(id);
        goods.setImage(image);
        goods.setName(name);
        goods.setManualDateInput(manualDateInput);
        goods.setQuote(mapIntegerToInt(quote));
        goods.setEstimate(mapDoubleObjectToDouble(estimate));
        goods.setType(type);
        return goods;
    }

    private  int mapIntegerToInt(Integer i){
        return  i == null ? 0 : i;
    }

    private Integer mapIntToInteger(int i) {
        return i == 0 ? null : i;
    }

    private  double mapDoubleObjectToDouble(Double i){
        return  i == null ? 0 : i;
    }

    private Double mapDoubleToDoubleObject(double i) {
        return i == 0 ? null : i;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public String getManualDateInput() {
        return manualDateInput;
    }

    public void setManualDateInput(String manualDateInput) {
        this.manualDateInput = manualDateInput;
    }

    public Integer getQuote() {
        return quote;
    }

    public void setQuote(Integer quote) {
        this.quote = quote;
    }

    public Double getEstimate() {
        return estimate;
    }

    public void setEstimate(Double estimate) {
        this.estimate = estimate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
