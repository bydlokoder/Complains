package com.example.complains.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.complains.R;
import com.example.complains.utils.Complain;
import com.example.complains.utils.PlaceHolder;
import com.example.complains.utils.adapters.ComplainAdapter;
import com.example.complains.utils.categories.Action;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
    private static final String COMPLAINS_KEY = "COMPLAINS";
    private List<Complain> complains = new ArrayList<>();
    private Drawer drawer = null;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setUpNavigationDrawer();
        if (savedInstanceState != null) {
            complains = (List<Complain>) savedInstanceState.getSerializable(COMPLAINS_KEY);
        } else {
            for (int i = 0; i < 10; i++) {
                Action action = new Action(getString(R.string.title_action_return),
                        getString(R.string.link_return_unsuitable_good),
                        "1_RETURN.doc");
                Complain complain = new Complain("ОАО КОМПАНИЯ", action, new ArrayList<PlaceHolder>());
                complains.add(complain);
            }
        }

        ComplainAdapter adapter = new ComplainAdapter(complains, this);
        RecyclerView recList = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        recList.setAdapter(adapter);
    }

    @OnClick(R.id.fab)
    public void addComplain() {
        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(COMPLAINS_KEY, (Serializable) complains);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setUpNavigationDrawer() {
        this.drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .build();
    }
}
