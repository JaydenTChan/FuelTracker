package com.example.jayden.jayden1_fueltrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jayden on 2016-01-30.
 */
public class FuelTrackPopUp extends Activity{
    //Initialize
    private EditText date;
    private EditText station;
    private EditText fuelGrade;
    private EditText fuelAmount;
    private EditText fuelUnitCost;
    private EditText odometer;
    private Activity activity = this;

    //Universal date format
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    private Boolean isNew;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fuelpopup);

        // === Construction of Buttons/EditText/Other view elements.
        Button saveButton = (Button) findViewById(R.id.buttonSave);
        Button cancelButton = (Button) findViewById(R.id.buttonCancel);

        date = (EditText) findViewById(R.id.date);
        station = (EditText) findViewById(R.id.Station);
        fuelGrade = (EditText) findViewById(R.id.fuelGrade);
        fuelAmount = (EditText) findViewById(R.id.fuelAmount);
        fuelUnitCost = (EditText) findViewById(R.id.fuelUnitPrice);
        odometer = (EditText) findViewById(R.id.odometer);

        // === These are decimal filters to limit the user in how they enter in digits
        fuelUnitCost.addTextChangedListener(new DecimalFilter(fuelUnitCost, activity,1));
        fuelAmount.addTextChangedListener(new DecimalFilter(fuelAmount, activity,3));
        odometer.addTextChangedListener(new DecimalFilter(odometer, activity,1));

        // === Initialize Display Metrics
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.8));

        Intent startingIntent = getIntent();

        // === Get Message from Main Activity
        isNew = startingIntent.getExtras().getBoolean("new");
        if (isNew == Boolean.FALSE) {

            FuelLogEntry editThis = (FuelLogEntry)startingIntent.getExtras().getSerializable("edit");
            // Get FuelLogEntry to be editted

            date.setText(df.format(editThis.getDate()));

            station.setText(editThis.getStation());
            fuelGrade.setText(editThis.getFuelGrade());

            fuelAmount.setText(Float.toString(editThis.getFuelAmount()));
            fuelUnitCost.setText(Float.toString(editThis.getUnitCost()));
            odometer.setText(Float.toString(editThis.getOdometer()));

            index = startingIntent.getExtras().getInt("index");
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            //Listens to save button actions

            public void onClick(View v) {
                //Save the values when save is clicked

                try {
                    //Try to parse all the data properly
                    Date a = df.parse(date.getText().toString());

                    String b = station.getText().toString();
                    String c = fuelGrade.getText().toString();
                    float d = Float.parseFloat(fuelAmount.getText().toString());
                    float e = Float.parseFloat(fuelUnitCost.getText().toString());
                    float f = Float.parseFloat(odometer.getText().toString());

                    FuelLogEntry entry = new FuelLogEntry(a, b, c, f, d, e);
                    Intent intent = new Intent();
                    intent.putExtra("entry", entry);


                    if(isNew == Boolean.FALSE){
                        //Return the index number if it is an edit
                        intent.putExtra("index", index);
                    }

                    setResult(RESULT_OK, intent);
                    finish();
                } catch (java.text.ParseException e) {
                    //Error parsing date
                    Toast.makeText(activity, "The date is not in the correct format.", Toast.LENGTH_SHORT).show();
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
