package com.cx.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {
    private ArrayList<Children> childrenArrayList=new ArrayList<>();//儿童账号集合
    private ArrayList<Parent> parentArrayList =new ArrayList<>();//家长账号集合


    public ArrayList<Children> getChildrenArrayList() {
        return childrenArrayList;
    }

    public void setChildrenArrayList(ArrayList<Children> childrenArrayList) {
        this.childrenArrayList = childrenArrayList;
    }

    public ArrayList<Parent> getPatriarchArrayList() {
        return parentArrayList;
    }

    public void setPatriarchArrayList(ArrayList<Parent> parentArrayList) {
        this.parentArrayList = parentArrayList;
    }

}
