package com.example.complains.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.complains.R;
import com.example.complains.utils.PlaceHolder;
import com.example.complains.utils.WordDocument;
import com.example.complains.utils.categories.Action;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

// TODO save input of the EditText for restoring after screen orientation changed
public class DocumentActivity extends AppCompatActivity {
    public static final String DOCUMENT_KEY = "DOCUMENT";
    private static final String PLACEHOLDERS_KEY = "PLACEHOLDERS";
    private Action action;
    private List<PlaceHolder> placeHolderList;
    private List<EditText> editTextList = new ArrayList<>();
    private WordDocument wordDocument;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);
        ButterKnife.bind(this);
        ViewGroup layout = (ViewGroup) findViewById(R.id.formLayout);
        if (savedInstanceState != null) {
            action = (Action) savedInstanceState.getSerializable(DOCUMENT_KEY);
            placeHolderList = (List<PlaceHolder>) savedInstanceState.getSerializable(PLACEHOLDERS_KEY);
        } else {
            Bundle b = getIntent().getExtras();
            if (b != null) {
                action = (Action) b.getSerializable(DOCUMENT_KEY);
            } else {
                finish(); // nothing to show
            }
            try {
                InputStream inputStream = getAssets().open(action.getDocFileName());
                wordDocument = new WordDocument(inputStream);
                inputStream.close();
                placeHolderList = wordDocument.getPlaceHolderList();
            } catch (IOException e) {
                Snackbar.make(findViewById(android.R.id.content), getString(R.string.error_loading_doc)
                        , Snackbar.LENGTH_LONG).show();
            }
        }
        buildForms(layout, placeHolderList, this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(DOCUMENT_KEY, action);
        outState.putSerializable(PLACEHOLDERS_KEY, (Serializable) placeHolderList);
    }

    private void buildForms(ViewGroup layout, List<PlaceHolder> placeHolderList, Context context) {
        for (PlaceHolder placeHolder : placeHolderList) {
            View view = getView(placeHolder, context);
            if (view != null) {
                layout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                        , ViewGroup.LayoutParams.WRAP_CONTENT));
            }
        }

    }

    private View getView(final PlaceHolder placeHolder, final Context context) {
        View view = View.inflate(context, R.layout.placheloder_text, null);
        final EditText editText = (EditText) view.findViewById(R.id.answer);
        editTextList.add(editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                placeHolder.setAnswer(s.toString());
            }
        });
        TextView textView = (TextView) view.findViewById(R.id.question);
        textView.setText(placeHolder.getQuestion());
        switch (placeHolder.getFormType()) {
            case DATE:
                editText.setFocusable(false);
                editText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar myCalendar = Calendar.getInstance();
                        DatePickerDialog dialog = buildDatePickerDialog(context, myCalendar, editText);
                        dialog.show();
                    }
                });
                return view;
            case TEXT:
                return view;
        }
        return null;
    }

    private DatePickerDialog buildDatePickerDialog(Context context, final Calendar myCalendar
            , final EditText editText) {
        return new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd.MM.yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                editText.setError(null);// clear error field
                editText.setText(sdf.format(myCalendar.getTime()));
            }
        }, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @OnClick(R.id.createComplain)
    public void createComplain() {
        if (isDataValid()) {
            try {
                InputStream inputStream = getAssets().open(action.getDocFileName());
                wordDocument = new WordDocument(inputStream);
                wordDocument.replaceAllStrings(placeHolderList);
                wordDocument.saveWord("test2.doc");
                inputStream.close();
            } catch (IOException e) {
                Snackbar.make(findViewById(android.R.id.content), getString(R.string.error_loading_doc)
                        , Snackbar.LENGTH_LONG).show();
            }
        }
    }

    private boolean isDataValid() {
        boolean isValid = true;
        for (EditText editText : editTextList) {
            if (editText.getText().toString().isEmpty()) {
                editText.setError(getString(R.string.error_field_cannot_be_empty));
                isValid = false;
            }
        }
        return isValid;
    }

}
