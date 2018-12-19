package com.example.floggy.testappzaritskyi.models;

import java.util.List;

public class TextWorker {

    public static List<String> capitalizeResponseText(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String string = list.get(i);
            string = string.substring(0, 1).toUpperCase() + string.substring(1);
            string = string.replaceAll("_", " ");
            list.set(i, string);
        }
        return list;
    }

    public static String decapitalizeText(String text) {
        text = text.toLowerCase();
        text = text.replaceAll(" ", "_");
        return text;
    }
}
