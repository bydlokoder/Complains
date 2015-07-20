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
import com.example.complains.utils.adapters.AgreementTypeAdapter;
import com.example.complains.utils.entities.AgreementType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AgreementFragment extends Fragment {

    public static final String AGREEMENT_KEY = "AGREEMENTS";
    private List<AgreementType> typeList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_agreement_type, container, false);
        Bundle args = getArguments();
        typeList = (List<AgreementType>) args.getSerializable(AGREEMENT_KEY);
        RecyclerView recList = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        AgreementTypeAdapter adapter = new AgreementTypeAdapter(typeList, getActivity(),
                getFragmentManager());
        recList.setAdapter(adapter);
        setTitle();
        return view;

    }

    public static AgreementFragment newInstance(List<AgreementType> typelist, String title) {
        AgreementFragment agreementFragment = new AgreementFragment();
        Bundle b = new Bundle();
        b.putSerializable(AGREEMENT_KEY, (Serializable) typelist);
        b.putSerializable(CategoryActivity.KEY_TITLE, title);
        agreementFragment.setArguments(b);
        return agreementFragment;
    }

    private void setTitle() {
        String title = getArguments().getString(CategoryActivity.KEY_TITLE);
        if (title != null) {
            getActivity().setTitle(title);
        }
    }
}
