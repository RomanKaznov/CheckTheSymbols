package com.example.checkthesymbols.symbol_functional;

import com.example.checkthesymbols.controller.Validating;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class CounterTest {

    private final String testString = "Test. text...";
    private final Counter counter = new Counter();


    @Test
    public void whenGetNumberEx() {


        ArrayList<Character> testSymbol = new ArrayList<>();
        testSymbol.add('t');


        int result = counter.getNumberExSymbol(testString, testSymbol);
        assertThat(result, is(3));
    }

    @Test
    public void whenGetNumberPoint() {

        Validating.checkPoint = true;

        ArrayList<Character> testSymbol = new ArrayList<>();
        testSymbol.add('.');


        int result = counter.getNumberExSymbol(testString, testSymbol);
        assertThat(result, is(1));
    }


    @Test
    public void whenGetNumberEllipsis() {

        Validating.checkEllipsis = true;

        ArrayList<Character> testSymbol = new ArrayList<>();
        testSymbol.add('.');
        testSymbol.add('.');
        testSymbol.add('.');


        int result = counter.getNumberExSymbol(testString, testSymbol);
        assertThat(result, is(3));
    }


    @Test
    public void whenGetNumberWords() {

        int result = counter.getNumberWords(testString);
        assertThat(result, is(2));

    }


}