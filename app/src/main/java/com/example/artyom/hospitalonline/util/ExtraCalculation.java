package com.example.artyom.hospitalonline.util;

import com.example.artyom.hospitalonline.model.Clinic;
import com.example.artyom.hospitalonline.model.Doctor;
import com.example.artyom.hospitalonline.model.Patient;
import com.example.artyom.hospitalonline.model.Speciality;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    public static Doctor getDoctorFromName(Clinic clinic, String name){
        for (Doctor doctor : clinic.getStaff()) {
            if(name.equals(doctor.getFullName())){
                return doctor;
            }
        }
        return null;
    }

    public static String[] getDoctorsFromSpeciality(Clinic clinic, String spec){
        List<Doctor> doctors = new ArrayList<>();
        Speciality speciality = Speciality.valueOf(spec);
        for (Doctor doc : clinic.getStaff()) {
            if(doc.getSpeciality()==speciality){
                doctors.add(doc);
            }
        }
        int size = doctors.size();
        String items[] = new String[size];
        for(int i = 0; i < size; i++){
            items[i] = doctors.get(i).getFullName();
        }
        return items;
    }

    public static String[] getAvailableDaysFromDoctor(Doctor doctor){
        List<Calendar> days = new ArrayList<>();
        for (List<Calendar> day : doctor.getFreeSession()) {
            days.add(day.get(0));
        }
        int size = days.size();
        String items[] = new String[size];
        for(int i = 0; i < size; i++){
            items[i] = days.get(i).get(Calendar.DAY_OF_MONTH)+ "." + (days.get(i).get(Calendar.MONTH)+1);
        }
        return items;
    }

    public static String[] getAvailableSessionsFromDoctor(Doctor doctor, int dayNumber){
        List<Calendar> day = doctor.getFreeSession().get(dayNumber);
        int size = day.size();
        String items[] = new String[size];
        for(int i = 0; i < size; i++){
            items[i] = day.get(i).get(Calendar.HOUR_OF_DAY)+ ":00";
        }
        return items;
    }

}
