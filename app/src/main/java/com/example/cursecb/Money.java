package com.example.cursecb;
//Создадим класс валюта
public class Money {
    private String id;
    private Integer mNumCode;
    private String mCharCode;
    private Integer mNominal;
    private String mName;
    private Double mValue;
    private Double mPrevious;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumCode() {
        return mNumCode;
    }

    public void setNumCode(Integer numCode) {
        mNumCode = numCode;
    }

    public String getCharCode() {
        return mCharCode;
    }

    public void setCharCode(String charCode) {
        mCharCode = charCode;
    }

    public Integer getNominal() {
        return mNominal;
    }

    public void setNominal(Integer nominal) {
        mNominal = nominal;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Double getValue() {
        return mValue;
    }

    public void setValue(Double value) {
        mValue = value;
    }

    public Double getPrevious() {
        return mPrevious;
    }

    public void setPrevious(Double previous) {
        mPrevious = previous;
    }
}
