package com.example.complains.utils;

import com.example.complains.R;

public enum ComplainType {
    PoorQualityGoods(R.string.defective_goods, R.mipmap.ic_launcher, "1.doc");

    private int typeResId; //string, type of the complain
    private int iconResId; //drawable icon
    private String docFileName;

    ComplainType(int typeResId, int iconResId, String docFileName) {
        this.typeResId = typeResId;
        this.iconResId = iconResId;
        this.docFileName = docFileName;
    }

    public int getTypeResId() {
        return typeResId;
    }

    public int getIconResId() {
        return iconResId;
    }

    public String getDocFileName() {
        return docFileName;
    }
}
