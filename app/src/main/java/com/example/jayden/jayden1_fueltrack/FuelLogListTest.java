package com.example.jayden.jayden1_fueltrack;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;

/**
 * Created by Jayden on 2016-01-29.
 */
public class FuelLogListTest extends ActivityInstrumentationTestCase2 {
    public FuelLogListTest() {super(MainActivity.class);}

    public void testAddLog(){
        FuelLogList entries = new FuelLogList();
        FuelLogEntry entry = new FuelLogEntry("Esso", "Normal", 10.02f, 100.500f, 1.20f);

        entries.addLog(entry);

        assertTrue(entries.getLog(0)==entry);
    }

    public void testGetList(){
        FuelLogList entries = new FuelLogList();
        FuelLogEntry entry = new FuelLogEntry("Esso", "Normal", 10.02f, 100.500f, 1.20f);

        entries.addLog(entry);

        assertTrue(entries.getList().get(0) == entry);
    }

    public void testDeleteLog(){
        FuelLogList entries = new FuelLogList();
        FuelLogEntry entry = new FuelLogEntry("Esso", "Normal", 10.02f, 100.500f, 1.20f);

        entries.addLog(entry);
        entries.deleteLog(entries.getLog(0));

        assertTrue(entries.getList().size() == 0);
    }

    public void testSetList(){
        ArrayList<FuelLogEntry> i = new ArrayList<>();

        FuelLogList entries = new FuelLogList();
        FuelLogEntry entry = new FuelLogEntry("Esso", "Normal", 10.02f, 100.500f, 1.20f);

        i.add(entry);
        entries.setList(i);

        assertTrue(entries.getList() == i);

    }
}
