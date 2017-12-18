package com.klix.app.utils;

import java.util.Stack;

public class MathUtils {

    private static char[] ALPHABET_62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();


    public static String decimalToBase(long number, int base) {
        Stack<Integer> stack = new Stack<>();
        int remainder;
        long quotient;
        do {
            quotient = number / base;
            remainder = (int) (number % base);
            stack.push(remainder);
            number = quotient;
        } while (quotient != 0);
        char[] result = new char[stack.size()];
        int i = 0;
        while (!stack.empty()) {
            result[i++] = ALPHABET_62[stack.pop()];
        }
        return new String(result);


    }


}


