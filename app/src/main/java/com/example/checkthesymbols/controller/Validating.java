package com.example.checkthesymbols.controller;

import java.util.ArrayList;

public class Validating {

    public static boolean checkPoint = false;
    public static boolean checkEllipsis = false;
    public static boolean checkInput = false;
    public static boolean navigation_down = false;
    public static boolean navigation_up = false;

    //проверка на повторяющие символы
    public boolean RepeatSymbol(ArrayList<String> arr, String symbol) {
        boolean check = true;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).equals(symbol)) {
                check = false;
            }
        }
        return check;
    }

    //примет символ,поместит или конвертирует символ  в массив и вернет его
    public ArrayList<Character> getArrSymbol(String symbol) {
        ArrayList<Character> arr = new ArrayList<>();


        switch (symbol) {
            case "...":
                arr.add('.');
                arr.add('.');
                arr.add('.');
                break;
            case "()":
                arr.add('(');
                arr.add(')');
                break;
            case "{}":
                arr.add('{');
                arr.add('}');
                break;
            case "''":
                arr.add('"');
                break;
            default:
                arr.add(symbol.charAt(0));
                break;
        }


        return arr;


    }
//

}
