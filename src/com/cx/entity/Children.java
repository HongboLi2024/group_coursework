package com.cx.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Children implements Serializable {
    private String username;//用户名
    private String password;//密码
    private Parent parent;//家长

    private ArrayList<Records> records=new ArrayList<>();//流水记录
    private double money_D;//定期金额
    private double money_H;//活期金额
    private ArrayList<Apply_For> apply_fors=new ArrayList<>();//请求列表

    private double goal=0;//目标金额
    private double D_goal=0;//当前金额

    public double getGoal() {
        return goal;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }

    public double getD_goal() {
        return D_goal;
    }

    public void setD_goal(double d_goal) {
        D_goal = d_goal;
    }

    public ArrayList<Apply_For> getApply_fors() {
        return apply_fors;
    }

    public void setApply_fors(ArrayList<Apply_For> apply_fors) {
        this.apply_fors = apply_fors;
    }

    public ArrayList<Records> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<Records> records) {
        this.records = records;
    }

    public double getMoney_D() {
        return money_D;
    }

    public void setMoney_D(double money_D) {
        this.money_D = money_D;
    }

    public double getMoney_H() {
        return money_H;
    }

    public void setMoney_H(double money_H) {
        this.money_H = money_H;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Parent getPatriarch() {
        return parent;
    }

    public void setPatriarch(Parent parent) {
        this.parent = parent;
    }
}
