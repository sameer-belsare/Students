package com.students;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;

/**
 * Created by sameer.belsare on 8/2/17.
 */

public class AddStudentActivity extends AppCompatActivity implements View.OnClickListener {
    private CircleImageView profilePic;
    private EditText firstName;
    private EditText lastName;
    private EditText age;
    private EditText address;
    private ImageView ivTakePhoto;
    private Button btnSave;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        profilePic = (CircleImageView) findViewById(R.id.ivProfilePic);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        age = (EditText) findViewById(R.id.age);
        address = (EditText) findViewById(R.id.address);
        ivTakePhoto = (ImageView) findViewById(R.id.ivTakePhoto);
        ivTakePhoto.setOnClickListener(this);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave:
                addStudentToDB();
                break;
        }
    }

    private void addStudentToDB() {
        Realm realm = ApplicationController.getInstance().getRealmInstance();
        Student student = new Student((int) System.currentTimeMillis(), firstName.getText().toString().trim(), lastName.getText().toString().trim(), Integer.valueOf(age.getText().toString().trim()), address.getText().toString().trim(), "");
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(student);
        realm.commitTransaction();
        Toast.makeText(this, "Student Added Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}
