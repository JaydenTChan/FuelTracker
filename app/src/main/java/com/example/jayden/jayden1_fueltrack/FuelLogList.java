package com.example.jayden.jayden1_fueltrack;

import java.util.ArrayList;

/**
 * Created by Jayden on 2016-01-29.
 */
public class FuelLogList{

    // === ArrayList
    private ArrayList<FuelLogEntry> logEntries = new ArrayList<>();

    public ArrayList<FuelLogEntry> getList(){
        return logEntries;
    }

    public void setList(ArrayList<FuelLogEntry> entries){
        logEntries = entries;
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

    public void editLog(int index, FuelLogEntry entry){
        //Replaces an old entry with editted version
        logEntries.set(index, entry);
    }

    public float getTotal(){
        //Gets the total cost of all the objects in the array.
        float runningTotal = 0;

        for (int i = 0; i < logEntries.size() ; i++){
            runningTotal += logEntries.get(i).getFuelCost();
        }

        return runningTotal;
    }

}
