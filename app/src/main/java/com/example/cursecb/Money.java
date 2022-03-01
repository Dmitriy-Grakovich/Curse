package com.example.cursecb;

import java.util.Objects;

//Создадим класс валюта
public class Money {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return id.equals(money.id) && mNumCode.equals(money.mNumCode) && mCharCode.equals(money.mCharCode) && mNominal.equals(money.mNominal) && mName.equals(money.mName) && mValue.equals(money.mValue) && mPrevious.equals(money.mPrevious);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mNumCode, mCharCode, mNominal, mName, mValue, mPrevious);
    }

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
