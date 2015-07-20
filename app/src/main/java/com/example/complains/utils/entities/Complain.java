package com.example.complains.utils.entities;

import java.io.Serializable;
import java.util.List;

public class Complain implements Serializable {
    private String name;
    private Action action;
    private List<PlaceHolder> placeHolderList;

    public Complain(String name, Action action, List<PlaceHolder> placeHolderList) {
        this.name = name;
        this.action = action;
        this.placeHolderList = placeHolderList;
    }

    public String getName() {
        return name;
    }

    public Action getAction() {
        return action;
    }

    public List<PlaceHolder> getPlaceHolderList() {
        return placeHolderList;
    }
}
