package com.students;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sameer.belsare on 8/2/17.
 */

public class Student extends RealmObject{
    @PrimaryKey
    private int rollNumber;
    private String firstName;
    private String lastName;
    private int age;
    private String address;
    private String photoUrl;

    public Student(){

    }

    public Student(int rollNumber, String firstName, String lastName, int age, String address, String photoUrl) {
        this.rollNumber = rollNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.photoUrl = photoUrl;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}