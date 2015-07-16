package com.example.complains.utils;

import java.io.Serializable;

public class PlaceHolder implements Serializable {
    private int order;
    private FormType formType;
    private String question;
    private String answer;
    private String initialMarking;

    public PlaceHolder(int order, FormType formType, String question, String answer, String initialMarking) {
        this.order = order;
        this.formType = formType;
        this.question = question;
        this.answer = answer;
        this.initialMarking = initialMarking;
    }

    public int getOrder() {
        return order;
    }

    public FormType getFormType() {
        return formType;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getInitialMarking() {
        return initialMarking;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
