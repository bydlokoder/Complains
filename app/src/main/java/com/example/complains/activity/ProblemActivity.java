package com.example.complains.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.complains.R;
import com.example.complains.utils.adapters.ProblemAdapter;
import com.example.complains.utils.categories.Problem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProblemActivity extends AppCompatActivity {
    public static final String PROBLEMS_KEY = "PROBLEMS";
    private List<Problem> problemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement_type);
        if (savedInstanceState != null) {
            problemList = (List<Problem>) savedInstanceState.getSerializable(PROBLEMS_KEY);
        } else {
            Bundle b = getIntent().getExtras();

            if (b != null) {
                problemList.addAll((List<Problem>) (b.getSerializable(PROBLEMS_KEY)));
            }
        }
        RecyclerView recList = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        recList.setAdapter(new ProblemAdapter(problemList, this));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(PROBLEMS_KEY, (Serializable) problemList);
    }
}
