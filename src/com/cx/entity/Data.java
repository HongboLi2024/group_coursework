package com.cx.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {
    private ArrayList<Children> childrenArrayList=new ArrayList<>();//儿童账号集合
    private ArrayList<Patriarch> patriarchArrayList=new ArrayList<>();//家长账号集合


    public ArrayList<Children> getChildrenArrayList() {
        return childrenArrayList;
    }

    public void setChildrenArrayList(ArrayList<Children> childrenArrayList) {
        this.childrenArrayList = childrenArrayList;
    }

    public ArrayList<Patriarch> getPatriarchArrayList() {
        return patriarchArrayList;
    }

    public void setPatriarchArrayList(ArrayList<Patriarch> patriarchArrayList) {
        this.patriarchArrayList = patriarchArrayList;
    }

}
