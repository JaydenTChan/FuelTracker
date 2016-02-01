package com.example.jayden.jayden1_fueltrack;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jayden on 2016-01-29.
 */
@SuppressWarnings("serial")
public class FuelLogEntry implements Serializable {
    /*

    This class contains all the variables for each log entry for the fuel tracker.

    */

    // === Variables
    private Date date;
    private String station;
    private String fuelGrade;
    private Float odometer;
    private Float fuelAmount;
    private Float unitCost;
    private Float fuelCost;

    public FuelLogEntry(Date date, String station, String fuelGrade, Float odometer, Float fuelAmount, Float unitCost) {
        // Constructor with specified date
        this.date = date;
        this.station = station;
        this.fuelGrade = fuelGrade;
        this.odometer = odometer;
        this.fuelAmount = fuelAmount;
        this.unitCost = unitCost;
        this.fuelCost = unitCost * fuelAmount;
    }

    public FuelLogEntry(String station, String fuelGrade, Float odometer, Float fuelAmount, Float unitCost) {
        // Constructor with automated date
        this.date = new Date();
        this.station = station;
        this.fuelGrade = fuelGrade;
        this.odometer = odometer;
        this.fuelAmount = fuelAmount;
        this.unitCost = unitCost;
        this.fuelCost = unitCost * fuelAmount;
    }

    public FuelLogEntry() {
        // Constructor with nothing
        this.date = new Date();
        this.station = "";
        this.fuelGrade = "";
        this.odometer = 0.0f;
        this.fuelAmount = 0.0f;
        this.unitCost = 0.0f;
    }


    // === Override for string transfer
    @Override
    public String toString(){
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);

        String returnString =
                dateString + " | " + station +
                "\n Fuel Grade:         \t\t\t" +       fuelGrade +
                "\n Fuel Amount:        \t\t\t" +       String.format("%1$.3f", fuelAmount) + "L" +
                "\n Fuel Unit Price:    \t\t\t" +       "$" + String.format("%1$.1f", unitCost) + "/L" +
                "\n Fuel Total Cost:    \t\t\t" +       "$" + String.format("%1$.2f",fuelCost) +
                "\n Odometer Reading:   \t\t" +         String.format("%1$.1f",odometer) + "km" +
                "";

        return returnString;
    }

    // === Getters and Setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getFuelGrade() {
        return fuelGrade;
    }

    public void setFuelGrade(String fuelGrade) {
        this.fuelGrade = fuelGrade;
    }

    public Float getOdometer() {
        return odometer;
    }

    public void setOdometer(Float odometer) {
        this.odometer = odometer;
    }

    public Float getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(Float fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public Float getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Float unitCost) {
        this.unitCost = unitCost;
    }

    public Float getFuelCost() {
        return fuelCost;
    }

    public void setFuelCost(Float fuelCost) {
        this.fuelCost = fuelCost;
    }
}
