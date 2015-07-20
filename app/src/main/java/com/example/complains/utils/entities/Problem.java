package com.example.complains.utils.entities;

import java.io.Serializable;
import java.util.List;

public class Problem implements Serializable {
    private String name;
    private String link;
    private List<Action> actionList;

    public Problem(String name, String link, List<Action> actionList) {
        this.name = name;
        this.link = link;
        this.actionList = actionList;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public List<Action> getActionList() {
        return actionList;
    }
}
