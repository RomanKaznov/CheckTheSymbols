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
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.checkthesymbols.symbol_functional.Counter;
import com.example.checkthesymbols.controller.SymbolDeletion;
import com.example.checkthesymbols.controller.Validating;
import com.example.checkthesymbols.symbol_functional.Symbol;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button addSym;

    LinearLayout navBarSymbols, navigationTitle, outputResult, navBarLayout;

    EditText inputText;

    TextView numberChars, numberWord, numberExSym, defaultSymSpace;
    ImageView checkSym;

    Symbol symbol;

    SymbolDeletion symbolDeletion;

    Animation animationDown;
    Animation animationUp;

    HorizontalScrollView items;

    Counter counter;
    Validating validating;

    ArrayList<String> repeatingCharacters;
    ArrayList<Character> selectedSymbol;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        checkSym = findViewById(R.id.search);
        addSym = findViewById(R.id.addSym);

        numberWord = findViewById(R.id.numberWords);
        numberExSym = findViewById(R.id.numberExSym);
        defaultSymSpace = findViewById(R.id.Space);
        numberChars = findViewById(R.id.numberChars);


        inputText = findViewById(R.id.input);

        navBarLayout = findViewById(R.id.navBarLayout);
        navigationTitle = findViewById(R.id.navigationTitle);
        navBarSymbols = findViewById(R.id.symbolsLayout);
        outputResult = findViewById(R.id.outputResLayout);

        items = findViewById(R.id.items);

        selectedSymbol = new ArrayList<>(); //повторяющиеся символы
        repeatingCharacters = new ArrayList<>();//выбранные символы
        //По умолчанию добавляется пробел
        selectedSymbol.add(' ');
        //

        symbol = new Symbol();
        symbolDeletion = new SymbolDeletion();
        NavBarAnim navBarAnim = new NavBarAnim(navBarLayout, navigationTitle);//класс отвечающий за работу анимации
        navBarAnim.setAnimationStart(animationDown, animationUp);
        //Класс отвечает за подсчет символов
        counter = new Counter();
        //Класс отвечает за проверку состояний
        validating = new Validating();
        //

        navigationTitle.setOnClickListener(this);
        checkSym.setOnClickListener(this);
        addSym.setOnClickListener(this);

        animationDown = AnimationUtils.loadAnimation(this, R.anim.down_navigation);
        animationUp = AnimationUtils.loadAnimation(this, R.anim.up_navigation);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search:
                //проверка состояния
                if (!Validating.navigation_down) {
                    navBarLayout.startAnimation(animationDown);
                }
                //метод вернет количество исключенных символов
                int numberSymInText = counter.getNumberExSymbol(String.valueOf(inputText.getText()), selectedSymbol);
                //Вычетаем из общего количества символов исключенные
                numberChars.setText(getString(R.string.symbol_in_text) + " " + String.valueOf(inputText.length() - numberSymInText));
                numberWord.setText(getString(R.string.word_in_text) + " " + String.valueOf(counter.getNumberWords(String.valueOf(inputText.getText()))));
                numberExSym.setText(getString(R.string.ex_in_symbol) + " " + String.valueOf(numberSymInText));
                break;
            case R.id.addSym:
                //запуск активити, которое вернет символ, выбранный пользователем
                Intent intent = new Intent(this, Symbol.class);
                startActivityForResult(intent, 1);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }

        String symbol = data.getStringExtra("text");
        boolean checkRepeat = validating.RepeatSymbol(repeatingCharacters, symbol);  //проверка на повторяющиеся символы

        if (checkRepeat && symbol != null) {
            repeatingCharacters.add(symbol);
            selectedSymbol.addAll(validating.getArrSymbol(symbol));
            ContextThemeWrapper newContext = new ContextThemeWrapper(this, R.style.item_navbar);
            TextView textView1 = new TextView(newContext);
            //вывод первоначальных значений
            navBarSymbols.addView(this.symbol.setParamsItem(textView1, symbol));
            symbolDeletion.setDeletion(navBarSymbols, this.symbol.setParamsItem(textView1, symbol), selectedSymbol, repeatingCharacters);
        }
    }

}


