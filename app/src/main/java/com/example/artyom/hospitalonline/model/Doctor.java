package com.example.artyom.hospitalonline.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Artyom on 25.10.2015.
 */
public class Doctor {
    private  String fullName;
    private Speciality speciality;
    private List<List<Calendar>> freeSession;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public List<List<Calendar>> getFreeSession() {
        return freeSession;
    }

    public void setFreeSession(List<List<Calendar>> freeSession) {
        this.freeSession = freeSession;
    }
}
