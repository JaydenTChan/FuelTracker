package com.example.jayden.jayden1_fueltrack;

/*
Class sourced from:
http://v4all123.blogspot.ca/2013/05/set-limit-for-fraction-in-decimal.html

Minor changes made.
- Changed to limit decimal point to 1
*/


import android.app.Activity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.Toast;

public class DecimalFilter implements TextWatcher {

    int count= -1 ;
    int type;
    EditText et;
    Activity activity;

    public DecimalFilter(EditText edittext, Activity activity, int type) {
        et = edittext;
        this.activity = activity;
        this.type = type;
    }

    public void afterTextChanged(Editable s) {

        if (s.length() > 0) {
            String str = et.getText().toString();
            et.setOnKeyListener(new OnKeyListener() {

                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        count--;
                        InputFilter[] fArray = new InputFilter[1];
                        fArray[0] = new InputFilter.LengthFilter(100);//Re sets the maxLength of edittext to 100.
                        et.setFilters(fArray);
                    }
                    if (count > type) {
                        Toast.makeText(activity, "Sorry! You cant enter more than " + type + "digit(s) after decimal point!", Toast.LENGTH_SHORT).show();
                    }
                    return false;
                }
            });

            char t = str.charAt(s.length() - 1);

            if (t == '.') {
                count = 0;
            }

            if (count >= 0) {
                if (count == type) {
                    InputFilter[] fArray = new InputFilter[1];
                    fArray[0] = new InputFilter.LengthFilter(s.length());
                    et.setFilters(fArray); // sets edittext's maxLength to number of digits now entered.

                }
                count++;
            }
        }

    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
// TODO Auto-generated method stub
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
// TODO Auto-generated method stub
    }

}
