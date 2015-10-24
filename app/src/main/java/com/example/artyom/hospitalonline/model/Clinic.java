package com.example.artyom.hospitalonline.model;

/**
 * Created by Artyom on 24.10.2015.
 */
public class Clinic {
    private String title;
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
}
