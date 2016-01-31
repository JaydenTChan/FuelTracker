package com.example.jayden.jayden1_fueltrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jayden on 2016-01-30.
 */
public class FuelTrackPopUp extends Activity{

    private Button saveButton;
    private Button cancelButton;

    private EditText date;
    private EditText station;
    private EditText fuelGrade;
    private EditText fuelAmount;
    private EditText fuelUnitCost;
    private EditText odometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fuelpopup);

        saveButton = (Button) findViewById(R.id.buttonSave);
        cancelButton = (Button) findViewById(R.id.buttonCancel);

        date = (EditText) findViewById(R.id.date);
        station = (EditText) findViewById(R.id.Station);
        fuelGrade = (EditText) findViewById(R.id.fuelGrade);
        fuelAmount = (EditText) findViewById(R.id.fuelAmount);
        fuelUnitCost = (EditText) findViewById(R.id.fuelUnitPrice);
        odometer = (EditText) findViewById(R.id.odometer);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.6), (int) (height * 0.5));

        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Save the values

                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                try {
                    Date a = df.parse(date.getText().toString());

                    String b = station.getText().toString();
                    String c = fuelGrade.getText().toString();
                    float d = Float.parseFloat(fuelAmount.getText().toString());
                    float e = Float.parseFloat(fuelUnitCost.getText().toString());
                    float f = Float.parseFloat(odometer.getText().toString());

                    FuelLogEntry entry = new FuelLogEntry(a, b, c, d, e, f);
                    Intent intent = new Intent();
                    intent.putExtra("entry", entry);
                    setResult(RESULT_OK, intent);
                    finish();
                } catch (java.text.ParseException e) {
                    //Error parsing
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Cancel Edit/New
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });

    }
}
