package com.example.complains.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.complains.R;
import com.example.complains.utils.adapters.AgreementTypeAdapter;
import com.example.complains.utils.categories.Action;
import com.example.complains.utils.categories.AgreementType;
import com.example.complains.utils.categories.Problem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AgreementTypeActivity extends AppCompatActivity {
    public static final String AGREEMENT_KEY = "AGREEMENTS";
    private List<AgreementType> typelist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement_type);

        if (savedInstanceState != null) {
            typelist = (List<AgreementType>) savedInstanceState.getSerializable(AGREEMENT_KEY);
        }
        RecyclerView recList = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        Action action = new Action(getString(R.string.title_action_return), getString(R.string.link_return_unsuitable_good), "1.doc");
        List<Action> actionList = new ArrayList<>();
        actionList.add(action);
        Problem problem = new Problem(getString(R.string.title_unsuitable_good), getString(R.string.link_unsuitable_good), actionList);
        List<Problem> problemList = new ArrayList<>();
        problemList.add(problem);
        typelist.add(new AgreementType(getString(R.string.title_purchase_agreement), getString(R.string.link_purchase_agreement), problemList));
        AgreementTypeAdapter adapter = new AgreementTypeAdapter(typelist, this);
        recList.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(AGREEMENT_KEY, (Serializable) typelist);
    }
}
