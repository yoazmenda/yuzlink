package com.yuzlink.app.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilsTests {
    @Test
    public void testConvertBases(){
        assertEquals("110", Utils.decimalToBase(6l, 2));
        assertEquals("6", Utils.decimalToBase(6l, 16));
        assertEquals("c0d1d", Utils.decimalToBase(789789l, 16));
        assertEquals("1001", Utils.decimalToBase(9l, 2));
        assertEquals("8qIm7", Utils.decimalToBase(124577723l, 62));
    }

    @Test
    public void testBase62(){
        assertEquals("08qIm7", Utils.keyToBase62(124577723l));
    }
}
