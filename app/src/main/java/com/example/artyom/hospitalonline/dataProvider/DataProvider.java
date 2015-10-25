package com.example.artyom.hospitalonline.dataProvider;

import com.example.artyom.hospitalonline.model.Action;
import com.example.artyom.hospitalonline.model.Clinic;
import com.example.artyom.hospitalonline.model.Doctor;
import com.example.artyom.hospitalonline.model.Patient;
import com.example.artyom.hospitalonline.model.Speciality;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Artyom on 24.10.2015.
 */
public class DataProvider {
    public static List<Clinic> getClinicsList(int region){
        List<Clinic> clinics = new ArrayList<Clinic>();
        for(int i = 0; i < 2*region; i++){
            clinics.add(new Clinic("Поликлиника № " + (i+1)));
        }
        return clinics;
    }

    public static List<Action> getHistory(Patient patient){
        List<Action> history = new ArrayList<>();
        history.add(new Action());
        return history;
    }

    public static List<Doctor> getDoctors(Clinic clinic){
        List<Doctor> staff = new ArrayList<>();
        for(Speciality speciality : Speciality.values()){
            Doctor doctor = new Doctor();
            doctor.setFullName(speciality.name() + " from " + clinic.getTitle());
            doctor.setSpeciality(speciality);
            doctor.setFreeSession(getFreeSessions(doctor));
            staff.add(doctor);
        }
        return staff;
    }

    private static List<Date> getFreeSessions(Doctor doctor){
        List<Date> freeSessions = new ArrayList<>();
        Calendar now = Calendar.getInstance();
        now.set(Calendar.DAY_OF_YEAR, now.get(Calendar.DAY_OF_YEAR)+1);
        for(int i = 0; i < 7; i++){
            now.set(Calendar.DAY_OF_YEAR, now.get(Calendar.DAY_OF_YEAR)+1);
            for(int j = 8; j < 17; j++){
                if(j != 13){
                    now.set(Calendar.HOUR_OF_DAY, j);
                    freeSessions.add(now.getTime());
                }
            }
        }
        return freeSessions;
    }

}
