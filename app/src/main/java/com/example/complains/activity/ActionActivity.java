package com.example.complains.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.complains.R;
import com.example.complains.utils.adapters.ActionAdapter;
import com.example.complains.utils.categories.Action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActionActivity extends AppCompatActivity {
    public static final String ACTION_KEY = "ACTIONS";
    List<Action> actionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement_type);
        if (savedInstanceState != null) {
            actionList = (List<Action>) savedInstanceState.getSerializable(ACTION_KEY);
        } else {
            Bundle b = getIntent().getExtras();

            if (b != null) {
                actionList.addAll((List<Action>) b.getSerializable(ACTION_KEY));
            }
        }
        RecyclerView recList = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        recList.setAdapter(new ActionAdapter(actionList, this));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ACTION_KEY, (Serializable) actionList);
    }
}
