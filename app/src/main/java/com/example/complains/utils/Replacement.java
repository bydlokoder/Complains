package com.example.complains.utils;

public class Replacement {

    private String oldValue;
    private String newValue;

    public Replacement(String oldValue, String newValue) {
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public String getOldValue() {
        return oldValue;
    }

    public String getNewValue() {
        return newValue;
    }
}
