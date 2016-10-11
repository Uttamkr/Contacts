package com.example.uttamkumar.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.util.List;

public class StudentsListActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        getStudentsList().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> studentsList, View item, int position, long id) {
                Student student = (Student)studentsList.getItemAtPosition(position);
                Intent intention = new Intent(StudentsListActivity.this,StudentFormActivity.class);
                intention.putExtra("student",student);
                startActivity(intention);
            }
        });
//        getStudentsList().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> studentsList, View item, int position, long id) {
//                Student student = (Student)studentsList.getItemAtPosition(position);
//                Toast.makeText(StudentsListActivity.this,"Student long click "+student.getName()+" selected",Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });

        registerForContextMenu(getStudentsList());

        Button newStudent = (Button)findViewById(R.id.students_list_new_student);
        assert newStudent != null;
        newStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intention = new Intent(StudentsListActivity.this, StudentFormActivity.class);
                startActivity(intention);
            }
        });
    }

    private ListView getStudentsList() {
        return (ListView)findViewById(R.id.students_list_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadStudents();
    }
    public void loadStudents(){
        StudentDataAccessObject dao = new StudentDataAccessObject(this);
        List<Student> students  = dao.listAll();
        dao.close();

        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1,students);
        getStudentsList().setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.equals(getStudentsList())) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            final Student student = (Student) getStudentsList().getItemAtPosition(info.position);

            showContextMenuForStudent(menu, student);
        }
    }

    private void showContextMenuForStudent(ContextMenu menu, final Student student) {
        MenuItem remove = menu.add("Remove");
        remove.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item){
                StudentDataAccessObject dao = new StudentDataAccessObject(StudentsListActivity.this);
                dao.remove(student);
                loadStudents();
                dao.close();
                Toast.makeText(StudentsListActivity.this, "Removing " + student.getName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
