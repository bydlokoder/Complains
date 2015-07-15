package com.example.complains.utils.categories;

import java.io.Serializable;
import java.util.List;

public class AgreementType implements Serializable {
    private String name;
    private String link;
    private List<Problem> problemList;

    public AgreementType(String name, String link, List<Problem> problemList) {
        this.name = name;
        this.link = link;
        this.problemList = problemList;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public List<Problem> getProblemList() {
        return problemList;
    }
}
