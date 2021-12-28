package com.zavaly.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ConverterUtils {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String localDateToString(LocalDate localDate) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = localDate.format(pattern);
        return date;
    }

    public static List<String> removeDoubleString(List<String> arrayInput) {
        List<String> list = new ArrayList<>();
        for (String string : arrayInput) {
            if (!list.contains(string)) {
                list.add(string);
            }
        }
        return list;
    }
}
