package com.example.artyom.hospitalonline.dataProvider;

import com.example.artyom.hospitalonline.model.Clinic;

import java.util.ArrayList;
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


}
