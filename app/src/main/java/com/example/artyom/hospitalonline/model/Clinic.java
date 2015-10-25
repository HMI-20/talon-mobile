package com.example.artyom.hospitalonline.model;

import java.util.List;

/**
 * Created by Artyom on 24.10.2015.
 */
public class Clinic {
    private String title;
    private List<Doctor> staff;
    public Clinic(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

    public List<Doctor> getStaff() {
        return staff;
    }

    public void setStaff(List<Doctor> staff) {
        this.staff = staff;
    }
}
