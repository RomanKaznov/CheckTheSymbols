package com.example.checkthesymbols.functional;

import java.util.ArrayList;

public class Verification {

    public static boolean checkPoint = false;
    public static boolean checkEllipsis = false;
    static boolean checkInput = false;
    public static boolean navigation_down = false;
    public static boolean navigation_up = false;



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
