package com.example.calculatorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class BasicActivity extends AppCompatActivity {

    TextView actualText;
    TextView historyText;

    String actualValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_basic);

        actualText = (TextView) findViewById(R.id.actualText);
        historyText = (TextView) findViewById(R.id.historyText);

        if(savedInstanceState != null) {
            actualValue = savedInstanceState.getString("actualValue");

            setActualValue(actualValue);
            setHistoryValue(savedInstanceState.getString("historyValue"));
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("actualValue", actualValue);
        outState.putString("historyValue", historyText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        savedInstanceState.getString("actualValue");
        savedInstanceState.getString("historyValue");
    }

    public void bskpClick(View view) {
        if(actualValue.length() != 0) {
            actualValue = actualValue.substring(0, actualValue.length() - 1);
            setActualValue(actualValue);
        }
    }

    public void cClick(View view) {
        if(actualValue.equals("")) {
            setHistoryValue(actualValue);
        }
        else {
            actualValue = "";
            setActualValue(actualValue);
        }
    }

    public void plusMinusClick(View view) {
        if(actualValue.length() != 0) {
            if (actualValue.charAt(0) == '-') {
                actualValue = actualValue.substring(1);
            } else {
                actualValue = "-" + actualValue;
            }
            setActualValue(actualValue);
        }
    }

    public void nineClick(View view) {
        actualValue += "9";
        setActualValue(actualValue);
    }

    public void eightClick(View view) {
        actualValue += "8";
        setActualValue(actualValue);
    }

    public void sevenClick(View view) {
        actualValue += "7";
        setActualValue(actualValue);
    }

    public void divisionClick(View view) {
        if(        actualValue.charAt(actualValue.length() - 1) != '-'
                && actualValue.charAt(actualValue.length() - 1) != '+'
                && actualValue.charAt(actualValue.length() - 1) != '*'
                && actualValue.charAt(actualValue.length() - 1) != '/') {
            actualValue += "/";
        }
        else actualValue = actualValue.substring(0, actualValue.length() - 1) + "/";
        setActualValue(actualValue);
    }

    public void fourClick(View view) {
        actualValue += "4";
        setActualValue(actualValue);
    }

    public void fiveClick(View view) {
        actualValue += "5";
        setActualValue(actualValue);
    }

    public void sixClick(View view) {
        actualValue += "6";
        setActualValue(actualValue);
    }

    public void multiplicationClick(View view) {
        if(        actualValue.charAt(actualValue.length() - 1) != '-'
                && actualValue.charAt(actualValue.length() - 1) != '+'
                && actualValue.charAt(actualValue.length() - 1) != '*'
                && actualValue.charAt(actualValue.length() - 1) != '/') {
            actualValue += "*";
        }
        else actualValue = actualValue.substring(0, actualValue.length() - 1) + "*";
        setActualValue(actualValue);
    }

    public void oneClick(View view) {
        actualValue += "1";
        setActualValue(actualValue);
    }

    public void twoClick(View view) {
        actualValue += "2";
        setActualValue(actualValue);
    }

    public void threeClick(View view) {
        actualValue += "3";
        setActualValue(actualValue);
    }

    public void subtractionClick(View view) {
        if(        actualValue.charAt(actualValue.length() - 1) != '-'
                && actualValue.charAt(actualValue.length() - 1) != '+'
                && actualValue.charAt(actualValue.length() - 1) != '*'
                && actualValue.charAt(actualValue.length() - 1) != '/') {
            actualValue += "-";
        }
        else actualValue = actualValue.substring(0, actualValue.length() - 1) + "-";
        setActualValue(actualValue);
    }

    public void zeroClick(View view) {
        if(actualValue.length() != 0) actualValue += "0";
        setActualValue(actualValue);
    }

    public void pointClick(View view) {
        if(        actualValue.charAt(actualValue.length() - 1) != '.'
                && actualValue.charAt(actualValue.length() - 1) != '+'
                && actualValue.charAt(actualValue.length() - 1) != '-'
                && actualValue.charAt(actualValue.length() - 1) != '*'
                && actualValue.charAt(actualValue.length() - 1) != '/') {
        }
        else return;
        for(int i = actualValue.length() - 1; i > 0; i--) {
            if(        actualValue.charAt(i) == '+'
                    || actualValue.charAt(i) == '-'
                    || actualValue.charAt(i) == '*'
                    || actualValue.charAt(i) == '/') {
                actualValue += ".";
                return;
            }
            else if(actualValue.charAt(i) == '.') return;
        }
        actualValue += ".";
        setActualValue(actualValue);
    }

    public void equalClick(View view) throws ScriptException {
        if(actualValue.length() == 0) return;

        for(int i = actualValue.length() - 1; i >= 0; i--) {
            if(actualValue.charAt(i) == '0' && actualValue.charAt(i - 1) == '/') {
                Context context = getApplicationContext();
                CharSequence text = "You cannot divide by 0";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return;
            }
        }

        if(        actualValue.charAt(actualValue.length() - 1) == '.'
                || actualValue.charAt(actualValue.length() - 1) == '+'
                || actualValue.charAt(actualValue.length() - 1) == '-'
                || actualValue.charAt(actualValue.length() - 1) == '*'
                || actualValue.charAt(actualValue.length() - 1) == '/') {
            actualValue = actualValue.substring(0, actualValue.length() - 1);
        }
        setHistoryValue(actualValue);

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("rhino");

        Object result = engine.eval(actualValue);
        actualValue = result.toString();

        int helpValue = 0;
        boolean helpBool = false;
        for(int i = 0; i < actualValue.length() - 1; i++) {
            if(helpBool == true) helpValue++;
            if(actualValue.charAt(i) == '.') helpBool = true;
        }

        System.out.println(helpValue);
        if(helpValue > 6) actualValue = actualValue.substring(0, actualValue.length() - 2);

        setActualValue(actualValue);

    }

    public void additionClick(View view) {
        if(        actualValue.charAt(actualValue.length() - 1) != '-'
                && actualValue.charAt(actualValue.length() - 1) != '+'
                && actualValue.charAt(actualValue.length() - 1) != '*'
                && actualValue.charAt(actualValue.length() - 1) != '/') {
            actualValue += "+";
        }
        else actualValue = actualValue.substring(0, actualValue.length() - 1) + "+";
        setActualValue(actualValue);
    }

    public void setActualValue(String actualValue) {
        actualText.clearComposingText();
        actualText.setText(actualValue);
    }

    public void setHistoryValue(String historyValue) {
        historyText.clearComposingText();
        historyText.setText(historyValue);
    }
}