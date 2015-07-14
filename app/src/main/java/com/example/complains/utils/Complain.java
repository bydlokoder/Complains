package com.example.complains.utils;

public class Complain {
    private String name;
    private String type;

    public Complain(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
