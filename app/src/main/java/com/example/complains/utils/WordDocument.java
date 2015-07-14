package com.example.complains.utils;

import android.os.Environment;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordDocument {
    public static final String REGEX_PLACEHOLDER = "\\{\\$\\$.*?\\$\\$\\}";
    public static final String REGEX_DATA = "\\[.*?\\]";
    private HWPFDocument document;

    public WordDocument(InputStream inputStream) throws IOException {
        this.document = new HWPFDocument(new POIFSFileSystem(inputStream));
    }

    public void replaceText(String findText, String replaceText) {
        Range r1 = document.getRange();
        r1.replaceText(findText, replaceText);
    }

    public void saveWord(String fileName) throws IOException {
        if (isExternalStorageWritable()) {
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(new File(Environment.getExternalStorageDirectory(), fileName));
                document.write(out);
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        }
    }

    public void replaceAllStrings(List<Replacement> replacements) {
        for (Replacement replacement : replacements) {
            replaceText(replacement.getOldValue(), replacement.getNewValue());
        }
    }

    public List<String> getAllMatches(String regex) throws IOException {
        List<String> matches = new ArrayList<>();
        Range r1 = document.getRange();
        for (int i = 0; i < r1.numSections(); ++i) {
            Section s = r1.getSection(i);
            for (int x = 0; x < s.numParagraphs(); x++) {
                Paragraph p = s.getParagraph(x);
                String text = p.text();
                Matcher m = Pattern.compile("(?=(" + regex + "))").matcher(text);
                while (m.find()) {
                    matches.add(m.group(1));
                }
            }
        }
        return matches;
    }

    /* Checks if external storage is available for read and write */
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public List<PlaceHolder> getPlaceHolderList() throws IOException {
        List<String> markingList = getAllMatches(REGEX_PLACEHOLDER);
        List<PlaceHolder> placeHolderList = new ArrayList<>();
        for (int i = 0; i < markingList.size(); i++) {
            PlaceHolder placeHolder = getPlaceHolder(i, markingList.get(i));
            if (placeHolder.getFormType() != null) {
                placeHolderList.add(placeHolder);
            }
        }
        return placeHolderList;
    }

    private PlaceHolder getPlaceHolder(int order, String marking) {
        Matcher m = Pattern.compile("(?=(" + REGEX_DATA + "))").matcher(marking);
        FormType type = null;
        String question = null;
        for (int i = 0; i < 2; i++) {
            if (m.find()) {
                String row = m.group(1);
                if (i == 0) {
                    type = getFormType(row.trim());
                } else {
                    question = row.substring(1, row.length() - 1).trim();
                }
            }
        }
        return new PlaceHolder(order, type, question, null, marking);
    }

    private FormType getFormType(String type) {
        FormType formType;
        try {
            switch (FormType.valueOf(type.toUpperCase())) {
                case DATE:
                    formType = FormType.DATE;
                    break;
                default:
                    formType = FormType.TEXT;
                    break;
            }
        } catch (IllegalArgumentException e) {
            // formType not found
            formType = FormType.TEXT;
        }

        return formType;
    }
}