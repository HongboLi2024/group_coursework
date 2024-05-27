package com.cx.entity;

import java.io.Serializable;

public class Apply_For implements Serializable {
    private String Apply_for_Name;//请求人
    private String type;//请求干啥
    private double money;//请求金额
    private boolean is;//是否处理
    private boolean is_G;//家长是否同意
    private String Description;//描述

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public boolean isIs() {
        return is;
    }

    public boolean isIs_G() {
        return is_G;
    }

    public void setIs_G(boolean is_G) {
        this.is_G = is_G;
    }

    public String getApply_for_Name() {
        return Apply_for_Name;
    }

    public void setApply_for_Name(String apply_for_Name) {
        Apply_for_Name = apply_for_Name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public boolean GetIs() {
        return is;
    }

    public void setIs(boolean is) {
        this.is = is;
    }
}
