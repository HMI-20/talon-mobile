package com.example.artyom.hospitalonline.model;

import java.util.Date;

/**
 * Created by Artyom on 25.10.2015.
 */
public class Action {
    private ActionType actionType;
    private Date date;
    private Doctor doctor;

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
