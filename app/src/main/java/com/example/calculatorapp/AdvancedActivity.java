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

public class AdvancedActivity extends AppCompatActivity {

    TextView actualText;
    TextView historyText;

    String actualValue = "";
    String actualPrintValue = "";

    boolean specialMode = false;
    boolean powType = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_advanced);

        actualText = (TextView) findViewById(R.id.actualText);
        historyText = (TextView) findViewById(R.id.historyText);

        if(savedInstanceState != null) {
            actualValue = savedInstanceState.getString("actualValue");
            actualPrintValue = savedInstanceState.getString("actualPrintValue");
            specialMode = savedInstanceState.getBoolean("specialMode");
            powType = savedInstanceState.getBoolean("powType");

            setActualValue(actualPrintValue);
            setHistoryValue(savedInstanceState.getString("historyPrintValue"));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("actualValue", actualValue);
        outState.putString("actualPrintValue", actualPrintValue);
        outState.putString("historyPrintValue", historyText.getText().toString());
        outState.putBoolean("specialMode", specialMode);
        outState.putBoolean("powType", powType);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        savedInstanceState.getString("actualValue");
        savedInstanceState.getString("actualPrintValue");
        savedInstanceState.getString("historyPrintValue");
        savedInstanceState.getBoolean("specialMode");
        savedInstanceState.getBoolean("powType");
    }

    public void bskpClick(View view) {
        if(actualValue.length() != 0) {
            actualValue = actualValue.substring(0, actualValue.length() - 1);
            actualPrintValue = actualPrintValue.substring(0, actualPrintValue.length() - 1);
            setActualValue(actualPrintValue);
        }
    }

    public void cClick(View view) {
        if(actualValue.equals("")) {
            setHistoryValue(actualValue);
        }
        else {
            actualValue = "";
            actualPrintValue = "";
            setActualValue(actualPrintValue);
        }
    }

    public void plusMinusClick(View view) {
        if(actualValue.length() != 0) {
            if (actualValue.charAt(0) == '-') {
                actualValue = actualValue.substring(1);
                actualPrintValue = actualValue;
            } else {
                actualValue = "-" + actualValue;
                actualPrintValue = actualValue;
            }
            setActualValue(actualPrintValue);
        }
    }

    public void sinClick(View view) {
        if(specialMode == false) {
            if(actualValue.length() == 0) {
                specialMode = true;
                actualValue += "Math.sin(";
                actualPrintValue += "sin(";
            }
            else if(        actualValue.charAt(actualValue.length() - 1) == '+'
                    || actualValue.charAt(actualValue.length() - 1) == '-'
                    || actualValue.charAt(actualValue.length() - 1) == '*'
                    || actualValue.charAt(actualValue.length() - 1) == '/') {
                specialMode = true;
                actualValue += "Math.sin(";
                actualPrintValue += "sin(";
            }
            else {
                int helpValue = 0;
                specialMode = true;
                System.out.println(actualPrintValue);
                for(int i = actualPrintValue.length() - 1; i > 0; i--) {
                    if(        actualPrintValue.charAt(i) == '+'
                            || actualPrintValue.charAt(i) == '-'
                            || actualPrintValue.charAt(i) == '*'
                            || actualPrintValue.charAt(i) == '/') {
                        helpValue = i;
                        break;
                    }
                }
                String helpString = "";
                helpString = actualPrintValue.substring(helpValue + 1);
                actualPrintValue = actualPrintValue.substring(0, helpValue + 1) + "sin(" + helpString;
                setActualValue(actualPrintValue);
                for(int i = actualValue.length() - 1; i > 0; i--) {
                    if(        actualValue.charAt(i) == '+'
                            || actualValue.charAt(i) == '-'
                            || actualValue.charAt(i) == '*'
                            || actualValue.charAt(i) == '/') {
                        helpValue = i;
                        break;
                    }
                }
                helpString = actualValue.substring(helpValue + 1);
                actualValue = actualValue.substring(0, helpValue + 1) + "Math.sin(" + helpString;
            }
        }
        else {
            specialMode = false;
            actualValue += ")";
            actualPrintValue += ")";
        }
        setActualValue(actualPrintValue);
    }

    public void cosClick(View view) {
        if(specialMode == false) {
            if(actualValue.length() == 0) {
                specialMode = true;
                actualValue += "Math.cos(";
                actualPrintValue += "cos(";
            }
            else if(        actualValue.charAt(actualValue.length() - 1) == '+'
                    || actualValue.charAt(actualValue.length() - 1) == '-'
                    || actualValue.charAt(actualValue.length() - 1) == '*'
                    || actualValue.charAt(actualValue.length() - 1) == '/') {
                specialMode = true;
                actualValue += "Math.cos(";
                actualPrintValue += "cos(";
            }
            else {
                int helpValue = 0;
                specialMode = true;
                System.out.println(actualPrintValue);
                for(int i = actualPrintValue.length() - 1; i > 0; i--) {
                    if(        actualPrintValue.charAt(i) == '+'
                            || actualPrintValue.charAt(i) == '-'
                            || actualPrintValue.charAt(i) == '*'
                            || actualPrintValue.charAt(i) == '/') {
                        helpValue = i;
                        break;
                    }
                }
                String helpString = "";
                helpString = actualPrintValue.substring(helpValue + 1);
                actualPrintValue = actualPrintValue.substring(0, helpValue + 1) + "cos(" + helpString;
                setActualValue(actualPrintValue);
                for(int i = actualValue.length() - 1; i > 0; i--) {
                    if(        actualValue.charAt(i) == '+'
                            || actualValue.charAt(i) == '-'
                            || actualValue.charAt(i) == '*'
                            || actualValue.charAt(i) == '/') {
                        helpValue = i;
                        break;
                    }
                }
                helpString = actualValue.substring(helpValue + 1);
                actualValue = actualValue.substring(0, helpValue + 1) + "Math.cos(" + helpString;
            }
        }
        else {
            specialMode = false;
            actualValue += ")";
            actualPrintValue += ")";
        }
        setActualValue(actualPrintValue);
    }

    public void tanClick(View view) {
        if(specialMode == false) {
            if(actualValue.length() == 0) {
                specialMode = true;
                actualValue += "Math.tan(";
                actualPrintValue += "tan(";
            }
            else if(        actualValue.charAt(actualValue.length() - 1) == '+'
                    || actualValue.charAt(actualValue.length() - 1) == '-'
                    || actualValue.charAt(actualValue.length() - 1) == '*'
                    || actualValue.charAt(actualValue.length() - 1) == '/') {
                specialMode = true;
                actualValue += "Math.tan(";
                actualPrintValue += "tan(";
            }
            else {
                int helpValue = 0;
                specialMode = true;
                System.out.println(actualPrintValue);
                for(int i = actualPrintValue.length() - 1; i > 0; i--) {
                    if(        actualPrintValue.charAt(i) == '+'
                            || actualPrintValue.charAt(i) == '-'
                            || actualPrintValue.charAt(i) == '*'
                            || actualPrintValue.charAt(i) == '/') {
                        helpValue = i;
                        break;
                    }
                }
                String helpString = "";
                helpString = actualPrintValue.substring(helpValue + 1);
                actualPrintValue = actualPrintValue.substring(0, helpValue + 1) + "tan(" + helpString;
                setActualValue(actualPrintValue);
                for(int i = actualValue.length() - 1; i > 0; i--) {
                    if(        actualValue.charAt(i) == '+'
                            || actualValue.charAt(i) == '-'
                            || actualValue.charAt(i) == '*'
                            || actualValue.charAt(i) == '/') {
                        helpValue = i;
                        break;
                    }
                }
                helpString = actualValue.substring(helpValue + 1);
                actualValue = actualValue.substring(0, helpValue + 1) + "Math.tan(" + helpString;
            }
        }
        else {
            specialMode = false;
            actualValue += ")";
            actualPrintValue += ")";
        }
        setActualValue(actualPrintValue);
    }

    public void lnClick(View view) {
        if(specialMode == false) {
            if(actualValue.length() == 0) {
                specialMode = true;
                actualValue += "Math.log10(";
                actualPrintValue += "ln(";
            }
            else if(       actualValue.charAt(actualValue.length() - 1) == '+'
                        || actualValue.charAt(actualValue.length() - 1) == '-'
                        || actualValue.charAt(actualValue.length() - 1) == '*'
                        || actualValue.charAt(actualValue.length() - 1) == '/') {
                specialMode = true;
                actualValue += "Math.log10(";
                actualPrintValue += "ln(";
            }
            else {
                int helpValue = 0;
                specialMode = true;
                System.out.println(actualPrintValue);
                for(int i = actualPrintValue.length() - 1; i > 0; i--) {
                    if(        actualPrintValue.charAt(i) == '+'
                            || actualPrintValue.charAt(i) == '-'
                            || actualPrintValue.charAt(i) == '*'
                            || actualPrintValue.charAt(i) == '/') {
                        helpValue = i;
                        break;
                    }
                }
                String helpString = "";
                helpString = actualPrintValue.substring(helpValue + 1);
                actualPrintValue = actualPrintValue.substring(0, helpValue + 1) + "ln(" + helpString;
                setActualValue(actualPrintValue);
                for(int i = actualValue.length() - 1; i > 0; i--) {
                    if(        actualValue.charAt(i) == '+'
                            || actualValue.charAt(i) == '-'
                            || actualValue.charAt(i) == '*'
                            || actualValue.charAt(i) == '/') {
                        helpValue = i;
                        break;
                    }
                }
                helpString = actualValue.substring(helpValue + 1);
                actualValue = actualValue.substring(0, helpValue + 1) + "Math.log10(" + helpString;
            }
        }
        else {
            specialMode = false;
            actualValue += ")";
            actualPrintValue += ")";
        }
        setActualValue(actualPrintValue);
    }

    public void sqrtClick(View view) {
        if(specialMode == false) {
            if(actualValue.length() == 0) {
                specialMode = true;
                actualValue += "Math.sqrt(";
                actualPrintValue += "sqrt(";
            }
            else if(       actualValue.charAt(actualValue.length() - 1) == '+'
                        || actualValue.charAt(actualValue.length() - 1) == '-'
                        || actualValue.charAt(actualValue.length() - 1) == '*'
                        || actualValue.charAt(actualValue.length() - 1) == '/') {
                specialMode = true;
                actualValue += "Math.sqrt(";
                actualPrintValue += "sqrt(";
            }
            else {
                int helpValue = 0;
                specialMode = true;
                System.out.println(actualPrintValue);
                for(int i = actualPrintValue.length() - 1; i > 0; i--) {
                    if(        actualPrintValue.charAt(i) == '+'
                            || actualPrintValue.charAt(i) == '-'
                            || actualPrintValue.charAt(i) == '*'
                            || actualPrintValue.charAt(i) == '/') {
                        helpValue = i;
                        break;
                    }
                }
                String helpString = "";
                helpString = actualPrintValue.substring(helpValue + 1);
                actualPrintValue = actualPrintValue.substring(0, helpValue + 1) + "sqrt(" + helpString;
                setActualValue(actualPrintValue);
                for(int i = actualValue.length() - 1; i > 0; i--) {
                    if(        actualValue.charAt(i) == '+'
                            || actualValue.charAt(i) == '-'
                            || actualValue.charAt(i) == '*'
                            || actualValue.charAt(i) == '/') {
                        helpValue = i;
                        break;
                    }
                }
                helpString = actualValue.substring(helpValue + 1);
                actualValue = actualValue.substring(0, helpValue + 1) + "Math.sqrt(" + helpString;
            }
        }
        else {
            specialMode = false;
            actualValue += ")";
            actualPrintValue += ")";
        }
        setActualValue(actualPrintValue);
    }

    public void powClick(View view) {
        if(specialMode == false) {
            if(actualValue.length() == 0) {
                specialMode = true;
                actualValue += "Math.pow(";
                actualPrintValue += "pow(";
            }
            else if(        actualValue.charAt(actualValue.length() - 1) == '+'
                    || actualValue.charAt(actualValue.length() - 1) == '-'
                    || actualValue.charAt(actualValue.length() - 1) == '*'
                    || actualValue.charAt(actualValue.length() - 1) == '/') {
                specialMode = true;
                actualValue += "Math.pow(";
                actualPrintValue += "pow(";
            }
            else {
                int helpValue = 0;
                specialMode = true;
                System.out.println(actualPrintValue);
                for(int i = actualPrintValue.length() - 1; i > 0; i--) {
                    if(        actualPrintValue.charAt(i) == '+'
                            || actualPrintValue.charAt(i) == '-'
                            || actualPrintValue.charAt(i) == '*'
                            || actualPrintValue.charAt(i) == '/') {
                        helpValue = i;
                        break;
                    }
                }
                String helpString = "";
                helpString = actualPrintValue.substring(helpValue + 1);
                actualPrintValue = actualPrintValue.substring(0, helpValue + 1) + "pow(" + helpString;
                setActualValue(actualPrintValue);
                for(int i = actualValue.length() - 1; i > 0; i--) {
                    if(        actualValue.charAt(i) == '+'
                            || actualValue.charAt(i) == '-'
                            || actualValue.charAt(i) == '*'
                            || actualValue.charAt(i) == '/') {
                        helpValue = i;
                        break;
                    }
                }
                helpString = actualValue.substring(helpValue + 1);
                actualValue = actualValue.substring(0, helpValue + 1) + "Math.pow(" + helpString;
            }
        }
        else {
            specialMode = false;
            actualValue += ", 2)";
            actualPrintValue += ")";
        }
        setActualValue(actualPrintValue);
    }

    public void pow2Click(View view) {
        if(powType == true) {
            powType = false;
            actualValue += ")";
            actualPrintValue += ")";
        }
        else if(specialMode == false) {
            if(actualValue.length() == 0) {
                specialMode = true;
                actualValue += "Math.pow(";
                actualPrintValue += "pow(";
            }
            else if(        actualValue.charAt(actualValue.length() - 1) == '+'
                    || actualValue.charAt(actualValue.length() - 1) == '-'
                    || actualValue.charAt(actualValue.length() - 1) == '*'
                    || actualValue.charAt(actualValue.length() - 1) == '/') {
                specialMode = true;
                actualValue += "Math.pow(";
                actualPrintValue += "pow(";
            }
            else {
                int helpValue = 0;
                specialMode = true;
                System.out.println(actualPrintValue);
                for(int i = actualPrintValue.length() - 1; i > 0; i--) {
                    if(        actualPrintValue.charAt(i) == '+'
                            || actualPrintValue.charAt(i) == '-'
                            || actualPrintValue.charAt(i) == '*'
                            || actualPrintValue.charAt(i) == '/') {
                        helpValue = i;
                        break;
                    }
                }
                String helpString = "";
                helpString = actualPrintValue.substring(helpValue + 1);
                actualPrintValue = actualPrintValue.substring(0, helpValue + 1) + "pow(" + helpString;
                setActualValue(actualPrintValue);
                for(int i = actualValue.length() - 1; i > 0; i--) {
                    if(        actualValue.charAt(i) == '+'
                            || actualValue.charAt(i) == '-'
                            || actualValue.charAt(i) == '*'
                            || actualValue.charAt(i) == '/') {
                        helpValue = i;
                        break;
                    }
                }
                helpString = actualValue.substring(helpValue + 1);
                actualValue = actualValue.substring(0, helpValue + 1) + "Math.pow(" + helpString;
            }
        }
        else {
            specialMode = false;
            powType = true;
            actualValue += ", ";
            actualPrintValue += ", ";
        }
        setActualValue(actualPrintValue);
    }

    public void logClick(View view) {
        if(specialMode == false) {
            if(actualValue.length() == 0) {
                specialMode = true;
                actualValue += "Math.log(";
                actualPrintValue += "log(";
            }
            else if(        actualValue.charAt(actualValue.length() - 1) == '+'
                    || actualValue.charAt(actualValue.length() - 1) == '-'
                    || actualValue.charAt(actualValue.length() - 1) == '*'
                    || actualValue.charAt(actualValue.length() - 1) == '/') {
                specialMode = true;
                actualValue += "Math.log(";
                actualPrintValue += "log(";
            }
            else {
                int helpValue = 0;
                specialMode = true;
                for(int i = actualPrintValue.length() - 1; i > 0; i--) {
                    if(        actualPrintValue.charAt(i) == '+'
                            || actualPrintValue.charAt(i) == '-'
                            || actualPrintValue.charAt(i) == '*'
                            || actualPrintValue.charAt(i) == '/') {
                        helpValue = i;
                        break;
                    }
                }
                String helpString = "";
                helpString = actualPrintValue.substring(helpValue + 1);
                actualPrintValue = actualPrintValue.substring(0, helpValue + 1) + "log(" + helpString;
                setActualValue(actualPrintValue);
                for(int i = actualValue.length() - 1; i > 0; i--) {
                    if(        actualValue.charAt(i) == '+'
                            || actualValue.charAt(i) == '-'
                            || actualValue.charAt(i) == '*'
                            || actualValue.charAt(i) == '/') {
                        helpValue = i;
                        break;
                    }
                }
                helpString = actualValue.substring(helpValue + 1);
                actualValue = actualValue.substring(0, helpValue + 1) + "Math.log(" + helpString;
            }
        }
        else {
            specialMode = false;
            actualValue += ")";
            actualPrintValue += ")";
        }
        setActualValue(actualPrintValue);
    }

    public void nineClick(View view) {
        actualValue += "9";
        actualPrintValue += "9";
        setActualValue(actualPrintValue);
    }

    public void eightClick(View view) {
        actualValue += "8";
        actualPrintValue += "8";
        setActualValue(actualPrintValue);
    }

    public void sevenClick(View view) {
        actualValue += "7";
        actualPrintValue += "7";
        setActualValue(actualPrintValue);
    }

    public void divisionClick(View view) {
        if(        actualValue.charAt(actualValue.length() - 1) != '-'
                && actualValue.charAt(actualValue.length() - 1) != '+'
                && actualValue.charAt(actualValue.length() - 1) != '*'
                && actualValue.charAt(actualValue.length() - 1) != '/') {
            actualValue += "/";
            actualPrintValue += "/";
        }
        else {
            actualValue = actualValue.substring(0, actualValue.length() - 1) + "/";
            actualPrintValue = actualPrintValue.substring(0, actualPrintValue.length() - 1) + "/";
        }
        setActualValue(actualPrintValue);
    }

    public void fourClick(View view) {
        actualValue += "4";
        actualPrintValue += "4";
        setActualValue(actualPrintValue);
    }

    public void fiveClick(View view) {
        actualValue += "5";
        actualPrintValue += "5";
        setActualValue(actualPrintValue);
    }

    public void sixClick(View view) {
        actualValue += "6";
        actualPrintValue += "6";
        setActualValue(actualPrintValue);
    }

    public void multiplicationClick(View view) {
        if(        actualValue.charAt(actualValue.length() - 1) != '-'
                && actualValue.charAt(actualValue.length() - 1) != '+'
                && actualValue.charAt(actualValue.length() - 1) != '*'
                && actualValue.charAt(actualValue.length() - 1) != '/') {
            actualValue += "*";
            actualPrintValue += "*";
        }
        else {
            actualValue = actualValue.substring(0, actualValue.length() - 1) + "*";
            actualPrintValue = actualPrintValue.substring(0, actualPrintValue.length() - 1) + "*";
        }
        setActualValue(actualPrintValue);
    }

    public void oneClick(View view) {
        actualValue += "1";
        actualPrintValue += "1";
        setActualValue(actualPrintValue);
    }

    public void twoClick(View view) {
        actualValue += "2";
        actualPrintValue += "2";
        setActualValue(actualPrintValue);
    }

    public void threeClick(View view) {
        actualValue += "3";
        actualPrintValue += "3";
        setActualValue(actualPrintValue);
    }

    public void subtractionClick(View view) {
        if(actualValue.length() != 0) {
            if(        actualValue.charAt(actualValue.length() - 1) != '-'
                    && actualValue.charAt(actualValue.length() - 1) != '+'
                    && actualValue.charAt(actualValue.length() - 1) != '*'
                    && actualValue.charAt(actualValue.length() - 1) != '/') {
                actualValue += "-";
                actualPrintValue += "-";
            }
            else {
                actualValue = actualValue.substring(0, actualValue.length() - 1) + "-";
                actualPrintValue = actualPrintValue.substring(0, actualPrintValue.length() - 1) + "-";
            }
        }
        else {
            actualValue += "-";
            actualPrintValue += "-";
        }
        setActualValue(actualPrintValue);
    }

    public void zeroClick(View view) {
        if(actualValue.length() != 0) {
            actualValue += "0";
            actualPrintValue += "0";
        }
        setActualValue(actualPrintValue);
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
                actualPrintValue += ".";
                setActualValue(actualPrintValue);
                return;
            }
            else if(actualValue.charAt(i) == '.') return;
        }
        actualValue += ".";
        actualPrintValue += ".";
        setActualValue(actualPrintValue);
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
            if(actualValue.charAt(i) == '-' && i != 0) {
                if(actualValue.charAt(i) == '-' && actualValue.charAt(i - 1) == '('
                        && actualValue.charAt(i - 2) == 't'
                        && actualValue.charAt(i - 3) == 'r'
                        && actualValue.charAt(i - 4) == 'q'
                        && actualValue.charAt(i - 5) == 's') {
                    Context context = getApplicationContext();
                    CharSequence text = "You cannot square a negative number";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }
                if(actualValue.charAt(i) == '-' && actualValue.charAt(i - 1) == '('
                        && actualValue.charAt(i - 2) == 'g'
                        && actualValue.charAt(i - 3) == 'o'
                        && actualValue.charAt(i - 4) == 'l') {
                    Context context = getApplicationContext();
                    CharSequence text = "You cannot use logarithm on negative numbers";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }
                if(actualValue.charAt(i) == '-' && actualValue.charAt(i - 1) == '('
                        && actualValue.charAt(i - 2) == 'n'
                        && actualValue.charAt(i - 3) == 'l') {
                    Context context = getApplicationContext();
                    CharSequence text = "You cannot use logarithm on negative numbers";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }
            }
        }


        System.out.println("4");
        if(        actualValue.charAt(actualValue.length() - 1) == '.'
                || actualValue.charAt(actualValue.length() - 1) == '+'
                || actualValue.charAt(actualValue.length() - 1) == '-'
                || actualValue.charAt(actualValue.length() - 1) == '*'
                || actualValue.charAt(actualValue.length() - 1) == '/') {
            actualValue = actualValue.substring(0, actualValue.length() - 1);
        }
        setHistoryValue(actualPrintValue);

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

        actualPrintValue = actualValue;
        setActualValue(actualPrintValue);
    }

    public void additionClick(View view) {
        if(        actualValue.charAt(actualValue.length() - 1) != '-'
                && actualValue.charAt(actualValue.length() - 1) != '+'
                && actualValue.charAt(actualValue.length() - 1) != '*'
                && actualValue.charAt(actualValue.length() - 1) != '/') {
            actualValue += "+";
            actualPrintValue += "+";
        }
        else {
            actualValue = actualValue.substring(0, actualValue.length() - 1) + "+";
            actualPrintValue = actualPrintValue.substring(0, actualPrintValue.length() - 1) + "+";
        }
        setActualValue(actualPrintValue);
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