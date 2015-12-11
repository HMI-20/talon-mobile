package com.example.artyom.hospitalonline.dataProvider;

import com.example.artyom.hospitalonline.model.Action;
import com.example.artyom.hospitalonline.model.ActionType;
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

    private static List<Action> history;

    public static List<Clinic> getClinicsList(int region){
        List<Clinic> clinics = new ArrayList<Clinic>();
        for(int i = 0; i < 2*region; i++){
            clinics.add(new Clinic("Поликлиника № " + (i+1)));
        }
        return clinics;
    }

    public static List<Action> getHistory(Patient patient){
        if(history == null){
            history = new ArrayList<>();
            Action a1 = new Action();
            a1.setActionType(ActionType.ORDER_TICKET);
            Doctor doctor = new Doctor();
            doctor.setFullName("Sean Bean");
            doctor.setSpeciality(Speciality.DENTIST);
            a1.setDoctor(doctor);
            Calendar year1970 = Calendar.getInstance();
            year1970.setTimeInMillis(0l);
            a1.setDate(Calendar.getInstance());
            history.add(a1);
            Action a2 = new Action();
            a2.setActionType(ActionType.CALL_DOCTOR);
            Doctor doctor2 = new Doctor();
            doctor2.setFullName("John Snow");
            doctor2.setSpeciality(Speciality.GASTROENTEROLOGIST);
            a2.setDoctor(doctor2);
            a2.setDate(Calendar.getInstance());
            history.add(a2);
        }
        return history;
    }

    public static void setHistory(List<Action> newHistory){
        history = newHistory;
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

    private static List<List<Calendar>> getFreeSessions(Doctor doctor){
        List<List<Calendar>> freeSessions = new ArrayList<>();
        Calendar now = Calendar.getInstance();
        for(int i = 0; i < 7; i++){
            List<Calendar> newDay = new ArrayList<>();
            now.set(Calendar.DAY_OF_YEAR, now.get(Calendar.DAY_OF_YEAR)+1);
            for(int j = 8; j < 17; j++){
                if(j != 13){
                    now.set(Calendar.HOUR_OF_DAY, j);
                    newDay.add((Calendar) now.clone());
                }
            }
            freeSessions.add(newDay);
        }
        return freeSessions;
    }

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
