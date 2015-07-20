package com.example.complains.utils.entities;

import java.io.Serializable;

public class Action implements Serializable{

    private String name;
    private String link;
    private String docFileName;

    public Action(String name, String link, String docFileName) {
        this.name = name;
        this.link = link;
        this.docFileName = docFileName;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String getDocFileName() {
        return docFileName;
    }
}
