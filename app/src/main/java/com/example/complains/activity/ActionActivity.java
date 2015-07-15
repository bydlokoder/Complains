package com.example.complains.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.complains.R;
import com.example.complains.utils.adapters.ActionAdapter;
import com.example.complains.utils.categories.Action;

import java.util.ArrayList;
import java.util.List;

public class ActionActivity extends AppCompatActivity {
    public static final String ACTION_KEY = "ACTIONS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement_type);
        RecyclerView recList = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        Bundle b = getIntent().getExtras();
        List<Action> actionList = new ArrayList<>();
        if (b != null) {
            actionList.addAll((List<Action>) b.getSerializable(ACTION_KEY));
        }
        recList.setAdapter(new ActionAdapter(actionList, this));
    }
}
