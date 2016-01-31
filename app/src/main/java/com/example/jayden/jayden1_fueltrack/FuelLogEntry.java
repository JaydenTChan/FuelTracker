package com.example.jayden.jayden1_fueltrack;

import java.io.Serializable;
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

    public FuelLogEntry(Date date, String station, String fuelGrade, Float odometer, Float fuelAmount, Float unitCost) {
        // Constructor with specified date
        this.date = date;
        this.station = station;
        this.fuelGrade = fuelGrade;
        this.odometer = odometer;
        this.fuelAmount = fuelAmount;
        this.unitCost = unitCost;
    }

    public FuelLogEntry(String station, String fuelGrade, Float odometer, Float fuelAmount, Float unitCost) {
        // Constructor with automated date
        this.date = new Date();
        this.station = station;
        this.fuelGrade = fuelGrade;
        this.odometer = odometer;
        this.fuelAmount = fuelAmount;
        this.unitCost = unitCost;
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
        return date.toString() + " | " + station;
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
}
