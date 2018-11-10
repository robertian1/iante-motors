package com.example.iante.iantemotors;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Book {

    public String model  ;
    public String make;
    public String email;
   public String Reg;
    public String telphone;
    public String Date;


    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Book() {
    }

    public Book(String model, String make, String email, String Reg,String telphone,String Date) {
        this.model = model;
        this.make = make;
        this.email = email;
        this.Reg = Reg;
        this.telphone = telphone;
        this.Date = Date;

    }
}