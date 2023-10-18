package com.uk.ncl.entity;

import com.uk.ncl.Tools;
import com.uk.ncl.constant.MyConstants;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Client {
    private String firstName;
    private String lastName;
    private String name;
    private String birth;
    private int age;

    private License license;
    private boolean isRenting;
    private Motor motor;

    public Client() {
    }

    public Client(String firstName, String lastName, Date birth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = Tools.getFormatDate(birth);
        this.name = firstName.substring(0, 1).toUpperCase() + firstName.substring(1) + " " +
                lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
        this.age = Tools.getAgeByBirth(Tools.getFormatDate(birth));
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = Tools.getFormatDate(birth);
    }

    public boolean isRenting() {
        return isRenting;
    }

    public void setRenting(boolean renting) {
        isRenting = renting;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }


    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", birth='" + birth + '\'' +
                ", age=" + age +
                ", license=" + license +
                ", isRenting=" + isRenting +
                ", motor=" + motor +
                '}';
    }
}