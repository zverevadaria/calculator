package ru.startandroid.develop.calculator;

import static com.google.android.material.internal.ViewUtils.hideKeyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String oldNumber;
    String operator = "";
    EditText editText;
    Boolean isNew = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.numberField);

    }

    public void onNumberClick(View view) {
        if (isNew) {
            editText.setText("");
            isNew = false;
        }

        String number = editText.getText().toString();
        if (view.getId() == R.id.button7) {
            number = number + "7";
        } else if (view.getId() == R.id.button8) {
            number = number + "8";
        } else if (view.getId() == R.id.button9) {
            number = number + "9";
        } else if (view.getId() == R.id.button4) {
            number = number + "4";
        } else if (view.getId() == R.id.button5) {
            number = number + "5";
        } else if (view.getId() == R.id.button6) {
            number = number + "6";
        } else if (view.getId() == R.id.button1) {
            number = number + "1";
        } else if (view.getId() == R.id.button2) {
            number = number + "2";
        } else if (view.getId() == R.id.button3) {
            number = number + "3";
        } else if (view.getId() == R.id.button0) {
            number = number + "0";
        } else if (view.getId() == R.id.buttonDot) {
            if (dotIsPresent(number)) {

            } else {
                number = number + ".";
            }
        } else if (view.getId() == R.id.buttonPlusMinus) {
            if (numberIsZero(number)){
                    number = "0";
               }else {
                   if (minusIsPresent(number)) {
                       number = number.substring(1);
                   } else {
                       number = "-" + number;
                   }
                }
             }
        editText.setText(number);
    }


    private boolean numberIsZero(String number) {
        if(number.equals("0") || number.equals("")){
            return true;
        } else {
            return false;
        }
    }

    public boolean minusIsPresent(String number) {
        if(number.charAt(0) == '-'){
            return true;
        }else {
            return false;
        }
    }

    public void onOperationClick(View view) {
        isNew = true;
        oldNumber = editText.getText().toString();
        if (view.getId() == R.id.buttonDivide){
            operator="/";
        }else if (view.getId() == R.id.buttonMinus) {
            operator="-";
        } else if (view.getId() == R.id.buttonPlus) {
            operator="+";
        } else if (view.getId() == R.id.buttonMultiply) {
            operator = "*";
        }
   }

    public void onClickResult(View view) {
      String newNumber =  editText.getText().toString();
      Double result = 0.0;

      if (operator == "-"){
          result = Double.parseDouble(oldNumber) - Double.parseDouble(newNumber);
      } else if (operator == "+"){
          result = Double.parseDouble(oldNumber) + Double.parseDouble(newNumber);
      } else if (operator == "*") {
          result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber);
      } else if (operator == "/") {
          result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber);
      }
        editText.setText(result+"");
    }

    public void onAcClick(View view) {
        editText.setText("0");
        isNew = true;
    }

    public boolean dotIsPresent (String number){
        if(number.indexOf(".") == -1) {
            return false;
        } else{
            return true;
        }
    }


    public void clickPercent(View view) {

        if (operator == ""){
            String number = editText.getText().toString();
            double temp = Double.parseDouble(number) / 100;
            number = temp + "";
            editText.setText(number);
        }else {
            Double result = 0.0;
            String newNumber = editText.getText().toString();
            switch (operator){
                case "-": result = Double.parseDouble(oldNumber) - Double.parseDouble(oldNumber) * Double.parseDouble(newNumber) / 100;
                    break;
                case "+": result = Double.parseDouble(oldNumber) + Double.parseDouble(oldNumber) * Double.parseDouble(newNumber) / 100;
                    break;
                case "*": result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber) / 100;
                    break;
                case "/": result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber) * 100;
                    break;
            }
           editText.setText(result + "");
            operator = "";
        }

    }
}