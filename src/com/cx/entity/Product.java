package com.cx.entity;

import java.io.Serializable;

public class Product  implements Serializable {
    private String name;//商品名
    private double money;//价格

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
