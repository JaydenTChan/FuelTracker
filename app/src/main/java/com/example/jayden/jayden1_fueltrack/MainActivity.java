package com.example.jayden.jayden1_fueltrack;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // === Filename
    private static final String FILENAME = "file.sav";

    // === Initializing Variables
    private ListView logView;
    private FuelLogList entries = new FuelLogList();
    private TextView total;
    private ArrayAdapter<FuelLogEntry> adapter;
    private int indexOf;

    // === Give this activity a variable
    private Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadFromFile();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // === Initialize view elements
        Button newButton = (Button) findViewById(R.id.buttonNew);
        logView = (ListView) findViewById(R.id.listView);
        total = (TextView) findViewById(R.id.textView2);
        setTotal();

        newButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // When the "new" button is click create a new activity
                Intent pop = new Intent(MainActivity.this, FuelTrackPopUp.class);
                pop.putExtra("new", Boolean.TRUE);//Tell the window this is the result of the NEW button

                startActivityForResult(pop, 1);

                setResult(RESULT_OK);
                //When button is clicked do something
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        //Receive result from activity
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if(resultCode == RESULT_OK){
                FuelLogEntry i;
                i = (FuelLogEntry)data.getSerializableExtra("entry");
                entries.addLog(i);
                adapter.notifyDataSetChanged();
                saveInFile();
            }
        }

        if (requestCode == 2){
            if(resultCode == RESULT_OK){
                FuelLogEntry i;
                i = (FuelLogEntry)data.getSerializableExtra("entry");
                entries.editLog(data.getExtras().getInt("index"), i);
                adapter.notifyDataSetChanged();
                saveInFile();
            }
        }

        setTotal();
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        setTotal();

        adapter = new ArrayAdapter<>(this, R.layout.list_item, entries.getList());
        logView.setAdapter(adapter);


        // Contextual Action Mode (Sourced from Youtube link 1 [See Source/Resources.txt])
        logView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        logView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                //When there is an item selected run this
                mode.setTitle("Log Entry Selected");
                indexOf = position;
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                //When the context menu is created run this
                MenuInflater menuInflater = getMenuInflater();
                menuInflater.inflate(R.menu.submenu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                //When
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                //When context buttons are clicked run this.

                if (item.getItemId() == R.id.id_delete) {
                    entries.deleteLog(entries.getLog(indexOf));
                    adapter.notifyDataSetChanged();
                    setTotal();
                    mode.finish();
                    Toast.makeText(activity, "The log entry was deleted.", Toast.LENGTH_SHORT).show();
                    saveInFile();
                }

                if (item.getItemId() == R.id.id_edit) {
                    //Open the pop up when editing
                    Intent pop = new Intent(MainActivity.this, FuelTrackPopUp.class);
                    pop.putExtra("new", Boolean.FALSE);
                    pop.putExtra("edit", entries.getLog(indexOf));
                    pop.putExtra("index", indexOf);

                    startActivityForResult(pop, 2);

                    setResult(RESULT_OK);

                    setTotal();
                    mode.finish();
                }

                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                //When the context menu is closed run this.
                indexOf = 0; //Reset index
            }
        });
    }

    public void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            // Took from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html 01-19 2016
            Type listType = new TypeToken<ArrayList<FuelLogEntry>>() {}.getType();
            ArrayList<FuelLogEntry> i;
            i = gson.fromJson(in, listType);
            entries.setList(i);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block

        }
    }

    public void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(entries.getList(), out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    private void setTotal (){
        float totalCost = entries.getTotal();
        String returnString = "Total Fuel Cost: $" + String.format("%1$.2f", totalCost);
        total.setText(returnString);
    }
}
