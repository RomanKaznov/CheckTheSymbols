package com.example.checkthesymbols.Controler;


import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.checkthesymbols.functional.Verification;

import java.util.ArrayList;


public class controlItem {

    //присвоение дополнительных параметров для TextView
    public TextView setParamsItem(TextView textView, String text) {

        if (text.equals(" ")) {
            text = "Space";
        }

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = 15;
        textView.setLayoutParams(layoutParams);
        textView.setText(text);
        return textView;
    }
    //


    public void setDeleter(final LinearLayout ll, final TextView textView, final ArrayList<Character> selectedSymbol, final ArrayList<String> RepeatSymbol) {

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = String.valueOf(textView.getText());

                deleter(selectedSymbol, RepeatSymbol, str);
                ll.removeView(textView);
            }
        });
    }


    private void deleter(ArrayList<Character> selectedSymbol, ArrayList<String> repeatSymbol, String str) {

        //удаление символа из массива "выбранные символы"
        for (int i = 0; i < selectedSymbol.size(); i++) {

            if (String.valueOf(selectedSymbol.get(i)).equals(str) && selectedSymbol.get(i) != '.') {

                selectedSymbol.remove(i);
            }
        }
        //

        //удаление символа из массива "Повторяющие символы"
        for (int i = 0; i < repeatSymbol.size(); i++) {

            if (String.valueOf(repeatSymbol.get(i)).equals(str)) {

                repeatSymbol.remove(i);
            }


        }
       //


        if (Verification.checkPoint) {
            for (int i = 0; i < selectedSymbol.size(); i++) {
                if (selectedSymbol.get(i) == '.') {

                    selectedSymbol.remove(i);
                }
            }

            Verification.checkPoint = false;
        }

        if (Verification.checkEllipsis) {
            for (int i = 0; i < selectedSymbol.size() - 2; i++) {
                if (selectedSymbol.get(i) == '.' && selectedSymbol.get(i + 1) == '.' && selectedSymbol.get(i + 2) == '.') {
                    selectedSymbol.remove(i);
                    selectedSymbol.remove(i + 1);
                    selectedSymbol.remove(i + 2);
                }
            }

            Verification.checkEllipsis = false;
        }


    }

}




