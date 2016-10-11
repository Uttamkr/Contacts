package com.example.uttamkumar.contacts;

import android.content.ContentValues;

import java.io.Serializable;

public class Student implements Serializable {

    private int id;
    private final String name;
    private final String address;
    private final String email;
    private final String website;
    private final String phoneNumber;
    private final double grading;

    public Student(String name, String address, String email, String website, String phoneNumber,double grading) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.website = website;
        this.phoneNumber = phoneNumber;
        this.grading = grading;
    }

    public Student(int id, String name, String address, String email, String website, String phoneNumber, double grading) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.website = website;
        this.phoneNumber = phoneNumber;
        this.grading = grading;
    }


    public String getName(){
        return this.name;
    }
    public double getGrading(){
        return this.grading;
    }

    @Override
    public String toString() {
        return id+ " "+name;
    }

    public ContentValues toContentValues() {
        ContentValues data = new ContentValues();
        data.put("name",name);
        data.put("grading",grading);
        data.put("address",address);
        data.put("phoneNumber",phoneNumber);
        data.put("email",email);
        data.put("website",website);
        return data;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getphoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }


}
