package com.example.iante.iantemotors;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Fuel {

    public String odometer;
    public String litres;
    public String amount;


    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Fuel() {
    }

    public Fuel(String odometer, String litres,String amount) {
        this.odometer = odometer;
        this.litres = litres;
        this.amount = amount;

    }
    public String getOdometer() {
        return odometer;
    }

    public void setOdometer(String odometer) {
        this.odometer = odometer;
    }
    public String getLitres() {
        return litres;
    }

    public void setLitres(String litres) {
        this.litres = litres;
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}