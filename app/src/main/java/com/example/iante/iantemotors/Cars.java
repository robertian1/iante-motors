package com.example.iante.iantemotors;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Cars {

    public String make;
    public String model;
    public String Engine;
    public String Reg;


    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Cars() {
    }

    public Cars(String make, String model,String Engine,String Reg) {
        this.make = make;
        this.model = model;
        this.Engine = Engine;
        this.Reg = Reg;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String  make) {
        this. make =  make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model= model;
    }

    public String getReg() {
        return Reg;
    }

    public void setReg(String Reg) {
        this.Reg = Reg;
    }

    public String getEngine() {
        return Engine;
    }

    public void setEngine(String Engine) {
        this.Engine= Engine;
    }
}