package com.example.jayden.jayden1_fueltrack;

import java.util.ArrayList;

/**
 * Created by Jayden on 2016-01-29.
 */
public class FuelLogList {

    private ArrayList<FuelLogEntry> logEntries = new ArrayList<FuelLogEntry>();

    public ArrayList<FuelLogEntry> getList(){
        return logEntries;
    }

    public void addLog(FuelLogEntry entry){
        //Adds a log entry to the list of entries
        logEntries.add(entry);
    }

    public void deleteLog(FuelLogEntry entry){
        //Deletes a log entry
        logEntries.remove(entry);
    }

    public FuelLogEntry getLog(int index){
        //Gets a log entry from the FuelLogEntries
        return logEntries.get(index);
    }

    public void editLog(FuelLogEntry entry){

    }

    public void getTotal(){
        //Gets the total cost of all the objects in the array.

    }

}
