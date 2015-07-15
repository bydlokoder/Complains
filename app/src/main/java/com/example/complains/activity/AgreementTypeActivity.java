package com.example.complains.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.complains.R;
import com.example.complains.utils.adapters.AgreementTypeAdapter;
import com.example.complains.utils.categories.Action;
import com.example.complains.utils.categories.AgreementType;
import com.example.complains.utils.categories.Problem;

import java.util.ArrayList;
import java.util.List;

public class AgreementTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement_type);
        RecyclerView recList = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        List<AgreementType> typelist = new ArrayList<>();

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_agreement_type, menu);
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
