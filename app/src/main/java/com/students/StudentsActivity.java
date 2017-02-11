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
import java.util.List;

import io.realm.Realm;

/**
 * Created by sameer.belsare on 8/2/17.
 */
public class StudentsActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView listView;
    private ProgressBar progressBar;
    private List<Student> students;
    private TextView noStudentsText;
    private Realm realm;
    private StudentsAdapter studentsAdapter;

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
        studentsAdapter.notifyDataSetChanged();
    }

    private void loadData() {
        if(students == null || students.size() <= 0){
            showProgress();
            for (int i=0; i<5; i++){
                Student student = new Student(i+1, (getResources().getStringArray(R.array.firstNameArray))[i], (getResources().getStringArray(R.array.lastNameArray))[i],
                        (getResources().getIntArray(R.array.ageArray))[i], "Address", "");
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(student);
                realm.commitTransaction();
            }
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
        Snackbar snackbar = Snackbar.make(findViewById(R.id.rlMain), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void showStudents() {
        students = realm.where(Student.class).findAll();
        if(students != null && students.size() > 0) {
            noStudentsText.setVisibility(View.GONE);
            studentsAdapter = new StudentsAdapter(students, this, this);
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
