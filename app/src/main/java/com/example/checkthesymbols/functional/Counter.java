package com.example.checkthesymbols.functional;


import com.example.checkthesymbols.controller.Validating;

import java.util.ArrayList;

public class Counter {




    //метод вернет общее количество исключенных символов
    public int getNumberExSymbol(String text, ArrayList<Character> symbol) {

        char[] textInChar = text.toCharArray();
        int res = 0;

        for (int count = 0; count < symbol.size(); count++) {

            for (char s : textInChar) {

                if (s == symbol.get(count) && s != '.') {//за посчет точек и многоточий отвечает метод getNumberPoint(text) и getNumberEllipsis(text)
                    res++;
                }
            }
        }

        int x = getNumberPoint(text) + getNumberEllipsis(text);
        return res + x;

    }
//

    //метод возвращает количество слов
    public int getNumberWords(String text) {
        char[] Text = text.toCharArray();
        int res = 0;

       for (int i = 0; i < Text.length-1; i++) {
            if (Text[i] == ' ' && Text[i + 1] != ' ' || i == 1 && Text[i] != ' ') {
                res++;
            }
        }


        return res;
    }
//
// метод возвращает количество многоточий
    private int getNumberEllipsis(String text) {
        char[] Text = text.toCharArray();
        int res = 0;
        boolean check = Validating.checkEllipsis;

        if (check) {
            for (int i = 0; i < Text.length - 1; i++) {

                try {
                    if (Text[i] == '.' && Text[i + 1] == '.' && Text[i + 2] == '.') {
                        res++;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }
            }
        }

        return res  * 3 ;
    }
//


    //метод возвращает количество точек
    private int getNumberPoint(String text) {
        char[] Text = text.toCharArray();
        int res = 0;
        boolean check = Validating.checkPoint;
        if (check) {
            for (int i = 0; i < Text.length; i++) {
                try {
                    if (Text[i - 1] != '.' && Text[i] == '.' && i == Text.length - 1 || Text[i] == '.' && Text[i - 1] != '.' && Text[i + 1] != '.') {

                        res++;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }

            }
        }

    return res;
    }
//

}
