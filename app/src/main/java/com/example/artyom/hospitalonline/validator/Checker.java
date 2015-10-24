package com.example.artyom.hospitalonline.validator;

import com.example.artyom.hospitalonline.model.Clinic;
import com.example.artyom.hospitalonline.model.Person;

/**
 * Created by Artyom on 25.10.2015.
 */
public class Checker {
    public static boolean isClient(Clinic clinic, Person person){
        if("Имя".equals(person.getName())) {
            return false;
        }
        return true;
    }
}
