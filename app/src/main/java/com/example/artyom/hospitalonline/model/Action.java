package com.example.artyom.hospitalonline.model;

import java.util.Calendar;

/**
 * Created by Artyom on 25.10.2015.
 */
public class Action {
    private ActionType actionType;
    private Calendar date;
    private Doctor doctor;

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
