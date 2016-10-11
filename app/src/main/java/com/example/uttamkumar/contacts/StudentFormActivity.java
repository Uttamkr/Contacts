package com.example.uttamkumar.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class StudentFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);

        if(hasIntentionToUpdate()){ //if student has been passed to edit its values
            Student student = getOriginaStudentToUpdate();
            StudentFormViewHelper helper = new StudentFormViewHelper(this);
            helper.fillInTheForm(student);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_student_form,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== R.id.student_form_confirm) {
            StudentFormViewHelper helper = new StudentFormViewHelper(this);
            Student student = helper.createStudent();

            //Save student into the database
            StudentDataAccessObject dao = new StudentDataAccessObject(this);
            if(hasIntentionToUpdate()){
                dao.update(student,getOriginaStudentToUpdate().getId());
            }else{
                dao.insert(student);
            }
            dao.close();

            String message =  student.getName()+" was saved with grading "+student.getGrading();
            Toast.makeText(StudentFormActivity.this,message, Toast.LENGTH_SHORT).show();
            finish();

        }else{
            Toast.makeText(StudentFormActivity.this,"Invalid option",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean hasIntentionToUpdate() {
        return getIntent().hasExtra("student");
    }

    public Student getOriginaStudentToUpdate() {
        Intent intention = getIntent();
        Student student = (Student) intention.getSerializableExtra("student");
        return student;
    }
}
