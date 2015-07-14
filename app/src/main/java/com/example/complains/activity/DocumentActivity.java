package com.example.complains.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.complains.R;
import com.example.complains.utils.PlaceHolder;
import com.example.complains.utils.WordDocument;

import java.io.IOException;
import java.util.List;

public class DocumentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_activity);
        try {
            WordDocument wordDocument = new WordDocument(getAssets().open("1.doc"));
            List<PlaceHolder> placeHolderList = wordDocument.getPlaceHolderList();
            placeHolderList.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
