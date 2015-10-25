package com.example.artyom.hospitalonline.util;

import com.example.artyom.hospitalonline.model.Clinic;
import com.example.artyom.hospitalonline.model.Doctor;
import com.example.artyom.hospitalonline.model.Patient;
import com.example.artyom.hospitalonline.model.Speciality;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artyom on 25.10.2015.
 */
public class ExtraCalculation {
    public static boolean isClient(Clinic clinic, Patient person){
        if("Имя".equals(person.getName())) {
            return false;
        }
        return true;
    }

    public static String[] specialityOfStaff(Clinic clinic){
        List<Speciality> specialityList = new ArrayList<>();
        for (Doctor doctor : clinic.getStaff()) {
            if(!specialityList.contains(doctor.getSpeciality())){
                specialityList.add(doctor.getSpeciality());
            }
        }
        int size = specialityList.size();
        String items[] = new String[size];
        for(int i = 0; i < size; i++){
            items[i] = specialityList.get(i).toString();
        }
        return items;
    }

}
