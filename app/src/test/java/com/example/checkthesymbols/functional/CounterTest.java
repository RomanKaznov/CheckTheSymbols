package com.example.checkthesymbols.functional;

import android.widget.TableRow;

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

        Verification.checkPoint = true;

        ArrayList<Character> testSymbol = new ArrayList<>();
        testSymbol.add('.');


        int result = counter.getNumberExSymbol(testString, testSymbol);
        assertThat(result, is(1));
    }


    @Test
    public void whenGetNumberEllipsis() {

        Verification.checkEllipsis = true;

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