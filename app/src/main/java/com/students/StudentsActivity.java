package com.students;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class StudentsActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView listView;
    private ProgressBar progressBar;
    private List<Student> students;
    private TextView noStudentsText;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        listView = (RecyclerView) findViewById(R.id.list);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        noStudentsText = (TextView) findViewById(R.id.noStudentsText);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);
        realm = ApplicationController.getInstance().getRealmInstance();
        loadData();
        showStudents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showStudents();
    }

    private void loadData() {
        if(students == null || students.size() <= 0){
            showProgress();
            List<Student> studentsList = new ArrayList<>();
            Student student = new Student(1, "aaa", "bbb", 10, "Address", "");
            studentsList.add(student);
            student = new Student(1, "ccc", "ddd", 10, "Address", "");
            studentsList.add(student);
            student = new Student(2, "eee", "fff", 10, "Address", "");
            studentsList.add(student);
            student = new Student(3, "ggg", "hhh", 10, "Address", "");
            studentsList.add(student);
            student = new Student(4, "iii", "jjj", 10, "Address", "");
            studentsList.add(student);
            for (Student stud : studentsList) {
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(stud);
                realm.commitTransaction();
            }
            students = realm.where(Student.class).findAll();
        }
    }

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.INVISIBLE);
        noStudentsText.setVisibility(View.GONE);
    }

    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
    }

    public void showErrorMessage(String message) {
        //noStudentsText.setVisibility(View.VISIBLE);
        Snackbar snackbar = Snackbar.make(findViewById(R.id.rlMain), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void showStudents() {
        if(students != null && students.size() > 0) {
            noStudentsText.setVisibility(View.GONE);
            StudentsAdapter studentsAdapter = new StudentsAdapter(students, this, this);
            listView.setAdapter(studentsAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            listView.setLayoutManager(linearLayoutManager);
            listView.setHasFixedSize(true);
        } else {
            showErrorMessage("No data found");
        }
        hideProgress();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_main:

                break;
            case R.id.fab:
                Intent intent = new Intent(this, AddStudentActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ApplicationController.getInstance().closeRealmInstance();
    }
}
