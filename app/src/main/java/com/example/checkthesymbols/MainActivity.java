package com.example.checkthesymbols;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.checkthesymbols.functional.Counter;
import com.example.checkthesymbols.Controler.controlItem;
import com.example.checkthesymbols.Controler.OnSwipeListener;
import com.example.checkthesymbols.functional.Verification;
import com.example.checkthesymbols.functional.addSymbol;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button addSym;


    LinearLayout collectionSym, navigationTitle, output, navigation;

    EditText inputText;

    TextView numberChars, countWord, countExSym, defaultSymSpace;
    ImageView checkSym;

    addSymbol addSymbol;

    controlItem elements;

    Animation animationDown;
    Animation animationUp;

    HorizontalScrollView items;

    Counter counter;
    Verification verification;

    //попадают повторяющие символы
    ArrayList<String> RepeatSymbol;
    //

    //попадают выбранные символы
    ArrayList<Character> SelectedSymbol;
    //

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        checkSym = findViewById(R.id.checkSym);
        addSym = findViewById(R.id.addSym);

        countWord = findViewById(R.id.countWord);
        countExSym = findViewById(R.id.countExSym);
        defaultSymSpace = findViewById(R.id.Space);
        numberChars = findViewById(R.id.numberChars);


        inputText = findViewById(R.id.input);

        navigation = findViewById(R.id.constraintLayout);
        navigationTitle = findViewById(R.id.navigationTitle);
        collectionSym = findViewById(R.id.colectionSyb);
        output = findViewById(R.id.linearLayout);

        items = findViewById(R.id.items);


        SelectedSymbol = new ArrayList<>();
        RepeatSymbol = new ArrayList<>();
        //По умолчаанию добавляется пробел
        SelectedSymbol.add(' ');
        //

        addSymbol = new addSymbol();
        elements = new controlItem();
        OnSwipeListener swipeTouchListener = new OnSwipeListener();
        //Класс который отвечает за подсчет символов
        counter = new Counter();
        //
        //Класс отвечает за проверку состояний
        verification = new Verification();
        //

        navigationTitle.setOnClickListener(this);
        checkSym.setOnClickListener(this);
        addSym.setOnClickListener(this);


        animationDown = AnimationUtils.loadAnimation(this, R.anim.down_navigation);
        animationUp = AnimationUtils.loadAnimation(this, R.anim.up_navigation);


        swipeTouchListener.setSwipeListener(navigation, navigationTitle, animationDown, animationUp);


        animationDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //поля отвечают за проверку состояний navigation: up/down
                Verification.navigation_down = true;
                Verification.navigation_up = false;
                //

                items.setVisibility(View.GONE);
                addSym.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) navigation.getLayoutParams();

                lp.topMargin = output.getHeight() / 2;

                navigation.setLayoutParams(lp);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        animationUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Verification.navigation_down = false;
                Verification.navigation_up = true;

                items.setVisibility(View.VISIBLE);
                addSym.setVisibility(View.VISIBLE);

                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) navigation.getLayoutParams();

                lp.topMargin = 0;

                navigation.setLayoutParams(lp);

            }


            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.checkSym:

                //проверка состоя
                if (!Verification.navigation_down) {
                    navigation.startAnimation(animationDown);

                }
                //


                //метод вернет количество исключенных символов
                int numberSymInText = counter.getNumberExSymbol(String.valueOf(inputText.getText()), SelectedSymbol);
                //

                //Вычетаем из общего количества символов исключенные
                numberChars.setText(getString(R.string.symbol_in_text) + " " + String.valueOf(inputText.length() - numberSymInText));
                countWord.setText(getString(R.string.word_in_text) + " " + String.valueOf(counter.getNumberWords(String.valueOf(inputText.getText()))));
                countExSym.setText(getString(R.string.ex_in_symbol) + " " + String.valueOf(numberSymInText));
                //


                break;

            case R.id.navigationTitle:

                if (!Verification.navigation_down) {
                    navigation.startAnimation(animationDown);

                }

                break;
            case R.id.addSym:
                //запуск активити, которое вернет символ выбранный пользователем
                Intent intent = new Intent(this, addSymbol.class);
                startActivityForResult(intent, 1);
                //
                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }


        String symbol = data.getStringExtra("text");


        //проверка на повторяющие символы
        boolean checkRepeat = verification.RepeatSymbol(RepeatSymbol, symbol);
        //

        if (checkRepeat && symbol != null) {
            RepeatSymbol.add(symbol);
            SelectedSymbol.addAll(verification.getArrSymbol(symbol));
            ContextThemeWrapper newContext = new ContextThemeWrapper(this, R.style.item);
            TextView textView1 = new TextView(newContext);
            //вывод первоначальных значений
            collectionSym.addView(elements.setParamsItem(textView1, symbol));
            elements.setDeleter(collectionSym, elements.setParamsItem(textView1, symbol), SelectedSymbol, RepeatSymbol);

        }
    }

}


