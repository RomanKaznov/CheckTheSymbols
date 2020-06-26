package com.example.checkthesymbols.functional;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.checkthesymbols.controller.Validating;
import com.example.checkthesymbols.R;

public class Symbol extends AppCompatActivity implements View.OnClickListener {
    String symbol;
    EditText input;
    Button add, symExcl, symQuestion, symPoint, symEllipsis, symQuotation, symSemicolon, symColon, symDog, symBrackets, symBrace,symDash,symSlash;
    InputFilter[] filterArray = new InputFilter[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_symbol);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        input = (EditText) findViewById(R.id.input);
        add = (Button) findViewById(R.id.add);
        symExcl = (Button) findViewById(R.id.symExcl);
        symQuestion = (Button) findViewById(R.id.symQuestion);
        symPoint = (Button) findViewById(R.id.symPoint);
        symEllipsis = (Button) findViewById(R.id.symEllipsis);
        symQuotation = (Button) findViewById(R.id.symQuotation);
        symSemicolon = (Button) findViewById(R.id.symSemicolon);
        symColon = (Button) findViewById(R.id.symColon);
        symDog = (Button) findViewById(R.id.symDog);
        symBrackets = (Button) findViewById(R.id.symBrackets);
        symBrace = (Button) findViewById(R.id.symBrace);
        symDash= (Button) findViewById(R.id.symDash);
        symSlash = (Button) findViewById(R.id.symSlash);


        add.setOnClickListener(this);
        symExcl.setOnClickListener(this);

        symQuestion.setOnClickListener(this);
        symPoint.setOnClickListener(this);

        symEllipsis.setOnClickListener(this);
        symQuotation.setOnClickListener(this);

        symSemicolon.setOnClickListener(this);
        symColon.setOnClickListener(this);

        symDog.setOnClickListener(this);
        symBrackets.setOnClickListener(this);

        symBrace.setOnClickListener(this);
        input.setOnClickListener(this);

        symDash.setOnClickListener(this);
        symSlash.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.symExcl:
                filterArray[0] = new InputFilter.LengthFilter(30);
                input.setFilters(filterArray);
                input.setText("Восклицательные знаки");
                symbol = "!";
                break;
            case R.id.symQuestion:
                filterArray[0] = new InputFilter.LengthFilter(30);
                input.setFilters(filterArray);
                input.setText("Знаки вопроса");
                symbol = "?";
                break;
            case R.id.symPoint:
                filterArray[0] = new InputFilter.LengthFilter(30);
                input.setFilters(filterArray);
                input.setText("Точки");
                symbol = ".";
                Validating.checkPoint = true;
                break;
            case R.id.symEllipsis:
                filterArray[0] = new InputFilter.LengthFilter(30);
                input.setFilters(filterArray);
                input.setText("Многоточия");
                symbol = "...";
                Validating.checkEllipsis = true;
                break;
            case R.id.symQuotation:
                filterArray[0] = new InputFilter.LengthFilter(30);
                input.setFilters(filterArray);
                input.setText("Кавычки");
                symbol = "''";
                break;
            case R.id.symSemicolon:
                filterArray[0] = new InputFilter.LengthFilter(30);
                input.setFilters(filterArray);
                input.setText("Точки с запятыми");
                symbol = ";";
                break;
            case R.id.symColon:
                filterArray[0] = new InputFilter.LengthFilter(30);
                input.setFilters(filterArray);
                input.setText("Двоеточия");
                symbol = ":";
                break;
            case R.id.symDog:
                filterArray[0] = new InputFilter.LengthFilter(30);
                input.setFilters(filterArray);
                input.setText("Собака");
                symbol = "@";
                break;
            case R.id.symBrace:
                filterArray[0] = new InputFilter.LengthFilter(30);
                input.setFilters(filterArray);
                input.setText("Фигурные скобки");
                symbol = "{}";
                break;
            case R.id.symBrackets:
                filterArray[0] = new InputFilter.LengthFilter(30);
                input.setFilters(filterArray);
                input.setText("Скобки");
                symbol = "()";
                break;

            case R.id.symDash:
                filterArray[0] = new InputFilter.LengthFilter(30);
                input.setFilters(filterArray);
                input.setText("Тире");
                symbol = "-";
                break;
            case R.id.symSlash:
                filterArray[0] = new InputFilter.LengthFilter(30);
                input.setFilters(filterArray);
                input.setText("Слэш");
                symbol = "/";
                break;
            case R.id.add:

                if (Validating.checkInput) {

                    symbol = String.valueOf(input.getText());
                    Validating.checkInput = false;

                }

                if (symbol != null) {

                    if (!symbol.equals("")) {
                        Intent intent = new Intent();
                        intent.putExtra("text", symbol);

                        setResult(RESULT_OK, intent);
                        finish();
                    }

                    break;
                } else {
                    filterArray[0] = new InputFilter.LengthFilter(15);
                    input.setFilters(filterArray);
                    input.setText("Выберите символ");
                    break;
                }
            case R.id.input:
                input.setText("");
                Validating.checkInput = true;
                filterArray[0] = new InputFilter.LengthFilter(1);
                input.setFilters(filterArray);
                break;
        }
    }




}
