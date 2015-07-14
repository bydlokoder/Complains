package com.example.complains.utils;

import android.support.annotation.NonNull;

public class PlaceHolder implements Comparable<PlaceHolder> {
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

    @Override
    public int compareTo(@NonNull PlaceHolder placeHolder) {
        return Integer.valueOf(order).compareTo(placeHolder.order);
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
}
