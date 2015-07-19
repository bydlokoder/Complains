package com.example.complains.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.complains.R;
import com.example.complains.activity.CategoryActivity;
import com.example.complains.utils.adapters.ProblemAdapter;
import com.example.complains.utils.categories.Problem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProblemFragment extends Fragment {
    private static final String PROBLEMS_KEY = "PROBLEMS";
    private List<Problem> problemList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_agreement_type, container, false);
        Bundle args = getArguments();
        problemList = (List<Problem>) args.getSerializable(PROBLEMS_KEY);
        RecyclerView recList = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        recList.setAdapter(new ProblemAdapter(problemList, getActivity(), getFragmentManager()));
        return view;
    }

    public static ProblemFragment newInstance(List<Problem> problemList, String title) {
        ProblemFragment fragment = new ProblemFragment();
        Bundle args = new Bundle();
        args.putSerializable(PROBLEMS_KEY, (Serializable) problemList);
        args.putSerializable(CategoryActivity.KEY_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }
}
