package com.klix.app.utils;

import java.util.Arrays;

public class Utils {

    public static String keyToBase62(long decimal) {
        String key = MathUtils.decimalToBase(decimal, 62);
        int leftToFill = 6 - key.length();
        if (leftToFill == 0) {
            return key;
        }
        char[] zeros = new char[leftToFill];
        Arrays.fill(zeros, '0');
        return new String(zeros) + key;
    }
}
