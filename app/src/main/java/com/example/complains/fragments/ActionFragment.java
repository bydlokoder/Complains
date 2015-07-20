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
import com.example.complains.utils.adapters.ActionAdapter;
import com.example.complains.utils.entities.Action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActionFragment extends Fragment {
    private static final String ACTION_KEY = "ACTIONS";
    private List<Action> actionList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_agreement_type, container, false);
        Bundle args = getArguments();
        actionList = (List<Action>) args.getSerializable(ACTION_KEY);
        RecyclerView recList = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        recList.setAdapter(new ActionAdapter(actionList, getActivity()));
        setTitle();
        return view;
    }

    public static ActionFragment newInstance(List<Action> actionList, String title) {
        ActionFragment fragment = new ActionFragment();
        Bundle args = new Bundle();
        args.putSerializable(ACTION_KEY, (Serializable) actionList);
        args.putSerializable(CategoryActivity.KEY_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    private void setTitle() {
        String title = getArguments().getString(CategoryActivity.KEY_TITLE);
        if (title != null) {
            getActivity().setTitle(title);
        }
    }
}
