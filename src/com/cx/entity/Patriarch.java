package com.cx.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Patriarch implements Serializable {
    private ArrayList<Children> children=new ArrayList<>();//孩子集合
    private ArrayList<Task> tasks =new ArrayList<>();//任务集合
    private String username;//用户名
    private String password;//密码
    private ArrayList<Apply_For> apply_fors=new ArrayList<>();//请求集合
    private ArrayList<Product> patriarchArrayList=new ArrayList<>();//商品集合

    private double regular_intervals;//定期利率
    private double current;//活期利率


    public ArrayList<Product> getPatriarchArrayList() {
        return patriarchArrayList;
    }

    public void setPatriarchArrayList(ArrayList<Product> patriarchArrayList) {
        this.patriarchArrayList = patriarchArrayList;
    }


    public double getRegular_intervals() {
        return regular_intervals;
    }

    public void setRegular_intervals(double regular_intervals) {
        this.regular_intervals = regular_intervals;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public void setRegular_intervals(int regular_intervals) {
        this.regular_intervals = regular_intervals;
    }

    public ArrayList<Apply_For> getApply_fors() {
        return apply_fors;
    }

    public void setApply_fors(ArrayList<Apply_For> apply_fors) {
        this.apply_fors = apply_fors;
    }

    public ArrayList<Children> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Children> children) {
        this.children = children;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
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
}
