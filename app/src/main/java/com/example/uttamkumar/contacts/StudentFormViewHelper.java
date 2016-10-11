package com.example.uttamkumar.contacts;

import android.app.Activity;
import android.widget.EditText;
import android.widget.*;

public class StudentFormViewHelper {

    private final Activity activity;

    public StudentFormViewHelper(Activity activity) {
        this.activity = activity;
    }

    public String getName() {
        return getTextFieldValue(R.id.student_form_name);
    }
    public String getAddress() {
        return getTextFieldValue(R.id.student_form_address);
    }
    public String getEmail() {
        return getTextFieldValue(R.id.student_form_email);
    }
    public String getWebsite() {
        return getTextFieldValue(R.id.student_form_website);
    }
    public String getPhoneNumber() {
        return getTextFieldValue(R.id.student_form_address);
    }

    private String getTextFieldValue(int fieldId) {
        EditText nameField = (EditText) activity.findViewById(fieldId);
        String name = nameField.getText().toString();
        return name;
    }

    public Student createStudent() {
        return new Student(getName(),getAddress(),getEmail(),getWebsite(),getPhoneNumber(),getGrading());
    }

    public double getGrading() {
        RatingBar rating = (RatingBar) activity.findViewById(R.id.student_form_grading);
        return rating.getRating();
    }

    public void fillInTheForm(Student student) {
        fill(R.id.student_form_name,student.getName());
        fill(R.id.student_form_address,student.getAddress());
        fill(R.id.student_form_phonenumber,student.getphoneNumber());
        fill(R.id.student_form_email,student.getEmail());
        fill(R.id.student_form_website,student.getWebsite());
        RatingBar rating = (RatingBar) activity.findViewById(R.id.student_form_grading);
        rating.setRating((float)student.getGrading());
    }

    private void fill(int id, String value) {

        EditText field = (EditText) activity.findViewById(id);
        field.setText(value);
    }
}

