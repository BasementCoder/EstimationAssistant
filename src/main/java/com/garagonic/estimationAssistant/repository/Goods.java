package com.garagonic.estimationAssistant.repository;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity(name = "goods")
public class Goods implements Serializable {


    public Goods() {
        inDate = Calendar.getInstance().getTime();
    }

    @Id
    @Column(length = 35)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private byte[] image;

//    private String imagePath;

    private String name;
    private Date inDate;
    private String manualDateInput;
    private int quote;
    private double estimate;
    private String type;

//    public String getImagePath() {
//        return imagePath;
//    }
//
//    public void setImagePath(String imagePath) {
//        this.imagePath = imagePath;
//    }

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

    public int getQuote() {
        return quote;
    }

    public void setQuote(int quote) {
        this.quote = quote;
    }

    public double getEstimate() {
        return estimate;
    }

    public void setEstimate(double estimate) {
        this.estimate = estimate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}