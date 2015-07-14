package com.example.complains.utils;

import android.widget.Filter;

import java.util.List;

//TODO
public class ComplainFilter extends Filter {

    private List<Complain> complainList;

    public ComplainFilter(List<Complain> complainList) {
        this.complainList = complainList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        return null;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

    }
}
