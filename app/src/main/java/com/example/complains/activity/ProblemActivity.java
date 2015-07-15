package com.example.complains.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.complains.R;
import com.example.complains.utils.adapters.ProblemAdapter;
import com.example.complains.utils.categories.Problem;

import java.util.ArrayList;
import java.util.List;

public class ProblemActivity extends AppCompatActivity {
    public static final String PROBLEMS_KEY = "PROBLEMS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement_type);
        RecyclerView recList = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        Bundle b = getIntent().getExtras();
        List<Problem> problemList = new ArrayList<>();
        if (b != null) {
            problemList.addAll((List<Problem>) (b.getSerializable(PROBLEMS_KEY)));
        }
        recList.setAdapter(new ProblemAdapter(problemList, this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_problem, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
