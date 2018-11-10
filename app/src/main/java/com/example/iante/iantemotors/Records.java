package com.example.iante.iantemotors;

public class Records {
    private String make;
    private String model;
    private String Engine;
    private String Reg;
    private String odometer;
    private String litres;
    private String amount;

    public Records() {
    }

    public Records(String make, String model, String Engine,String Reg,String odometer,
                   String litres,String amount) {
        this.make = make;
        this.model = model;
        this.Engine = Engine;
        this.Reg = Reg;
        this.odometer = odometer;
        this.litres = litres;
        this.amount = amount;
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

    public String getEngine() {
        return Engine;
    }

    public void setEngine(String Engine) {
        this.Engine= Engine;
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

    public String getReg() {
        return Reg;
    }

    public void setReg(String Reg) {
        this.Reg = Reg;
    }
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

