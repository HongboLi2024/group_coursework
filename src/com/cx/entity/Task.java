package com.cx.entity;

import java.io.Serializable;

public class Task implements Serializable {
    private String time;//时间
    private double money;//报酬
    private String Description;//描述
    private String name;//孩子名
    private boolean is=false;//是否完成


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIs() {
        return is;
    }

    public void setIs(boolean is) {
        this.is = is;
    }
}
