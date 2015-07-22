package com.example.complains.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.example.complains.R;
import com.example.complains.utils.PrimaryNavigationItem;
import com.example.complains.utils.SecondaryNavigationItem;
import com.example.complains.utils.adapters.ComplainAdapter;
import com.example.complains.utils.entities.Action;
import com.example.complains.utils.entities.Complain;
import com.example.complains.utils.entities.PlaceHolder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements Drawer.OnDrawerItemClickListener {
    private static final String COMPLAINS_KEY = "COMPLAINS";
    private List<Complain> complains = new ArrayList<>();
    private Drawer drawer = null;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setUpNavigationDrawer(savedInstanceState);
        if (savedInstanceState != null) {
            complains = (List<Complain>) savedInstanceState.getSerializable(COMPLAINS_KEY);
        } else {
            complains = buildSamples();
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

    /**
     * Method generates samples of created complains by user
     * In the future should be replaced by getting data from database
     *
     * @return List of user's complains
     */
    private List<Complain> buildSamples() {
        List<Complain> complainList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Action action = new Action(getString(R.string.title_unsuitable_good),
                    getString(R.string.link_return_unsuitable_good),
                    "1_Return_unsuitable_goods.doc");
            Complain complain = new Complain("ОАО РОМАШКА", action, new ArrayList<PlaceHolder>());
            complainList.add(complain);
        }
        return complainList;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(COMPLAINS_KEY, (Serializable) complains);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar
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

    private void setUpNavigationDrawer(Bundle savedInstanceState) {
        AccountHeader header = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .withSavedInstance(savedInstanceState)
                .withCloseDrawerOnProfileListClick(true)
                .build();
        this.drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(header)
                .withSavedInstance(savedInstanceState)
                .addDrawerItems(PrimaryNavigationItem.getPrimaryDrawerItemArray())
                .withOnDrawerItemClickListener(this)
                .addStickyDrawerItems(SecondaryNavigationItem.getSecondaryDrawerItemArray())
                .build();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l,
                               IDrawerItem iDrawerItem) {
        switch (iDrawerItem.getType()){
            case "PRIMARY_ITEM":
                switch (PrimaryNavigationItem.values()[iDrawerItem.getIdentifier()]){
                    case Complains:
                        break;
                    case Favourites:
                        break;
                    case Articles:
                        openLink(getString(R.string.link_azbuka_main));
                        break;
                }
                break;
            case "SECONDARY_ITEM":
                switch (SecondaryNavigationItem.values()[iDrawerItem.getIdentifier()]){
                    case LawLink:
                        openLink(getString(R.string.link_main_law));
                        break;
                    case Settings:
                        break;
                    case About:
                        break;
                }
                break;
        }
        return false;
    }

    private void openLink(String link){
        Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(link));
        startActivity(intent);
    }
}
