package com.yuzlink.app.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MathUtilsTests{
    @Test
    public void testConvertBases(){
        assertEquals("110", MathUtils.decimalToBase(6l, 2));
        assertEquals("6", MathUtils.decimalToBase(6l, 16));
        assertEquals("c0d1d", MathUtils.decimalToBase(789789l, 16));
        assertEquals("1001", MathUtils.decimalToBase(9l, 2));
        assertEquals("8qIm7", MathUtils.decimalToBase(124577723l, 62));
    }

    @Test
    public void testBase62(){
        assertEquals("08qIm7", MathUtils.keyToBase62(124577723l));
    }
}
