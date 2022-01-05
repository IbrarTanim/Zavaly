package com.zavaly.validator;

public class AppValidator {

    public static boolean isNumeric(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkLength(String value, int min) {
        if (value.length() == min) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(String value) {
        if (value.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValidDropDownSelected(String value, String tag) {
        if (value.equals(tag)) {
            return false;
        } else {
            return true;
        }
    }
}
