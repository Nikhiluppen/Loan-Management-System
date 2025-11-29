package com.example.Restdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestsamplesTests {

    @Autowired
    private Testsamples testsamples = new Testsamples();

    @Test
    public void testIsPalindrome_ValidPalindrome(){
        var result = testsamples.IsPalindrome("madam");
        Assertions.assertTrue(result);

    }

    @Test
    public void testIsPaliindrome_NotPalindrome(){
        boolean result = testsamples.IsPalindrome("nikhil");
        Assertions.assertFalse(result);
    }



}
