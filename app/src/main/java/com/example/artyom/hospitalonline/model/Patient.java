package com.example.artyom.hospitalonline.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Artyom on 24.10.2015.
 */
public class Patient {
    private String name, lastName, patronymic;
    private Date dateOfBirthday;
    private String town, address;
    private List<Action> history;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(Date dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return name + " " + lastName + " " + patronymic + " " + town + " " + address + " " + dateOfBirthday.toString();
    }

    public List<Action> getHistory() {
        return history;
    }

    public void setHistory(List<Action> history) {
        this.history = history;
    }
}
