package com.example.complains.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.complains.R;
import com.example.complains.fragments.AgreementFragment;
import com.example.complains.utils.entities.Action;
import com.example.complains.utils.entities.AgreementType;
import com.example.complains.utils.entities.Problem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity {
    public static final String KEY_TITLE = "TITLE";
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        setUpActionBar(toolbar);
        if (savedInstanceState == null) {
            Fragment f = AgreementFragment.newInstance(getSamples(),
                    getString(R.string.title_activity_agreement_type));
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, f).commit();
        }
    }

    private List<AgreementType> getSamples() {
        List<AgreementType> typeList = new ArrayList<>();
        Action action = new Action(getString(R.string.title_action_return),
                getString(R.string.link_return_unsuitable_good),
                "1_RETURN.doc");
        List<Action> actionList = new ArrayList<>();
        actionList.add(action);
        Problem problem = new Problem(getString(R.string.title_unsuitable_good),
                getString(R.string.link_unsuitable_good), actionList);
        List<Problem> problemList = new ArrayList<>();
        problemList.add(problem);
        typeList.add(new AgreementType(getString(R.string.title_purchase_agreement),
                getString(R.string.link_purchase_agreement), problemList));

        List<Action> actionList1 = new ArrayList<>();
        actionList1.add(new Action(getString(R.string.title_action_new_deadline), getString(R.string.link_new_deadline), "2.doc"));
        List<Problem> problemList1 = new ArrayList<>();
        problemList1.add(new Problem(getString(R.string.title_expired_services), getString(R.string.link_expired), actionList1));
        typeList.add(new AgreementType(getString(R.string.title_services), getString(R.string.link_services), problemList1));
        return typeList;
    }

    private void setUpActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            if (getFragmentManager().getBackStackEntryCount() != 0) {
                getFragmentManager().popBackStack();
            } else {
                onBackPressed();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
