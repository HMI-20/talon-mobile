package com.example.artyom.hospitalonline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.artyom.hospitalonline.adapter.ClinicAdapter;
import com.example.artyom.hospitalonline.dataProvider.DataProvider;
import com.example.artyom.hospitalonline.model.Action;
import com.example.artyom.hospitalonline.model.ActionType;
import com.example.artyom.hospitalonline.model.Clinic;
import com.example.artyom.hospitalonline.model.Patient;
import com.example.artyom.hospitalonline.util.ExtraCalculation;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private int region;
    private static Clinic clinic;
    private Patient person = new Patient();
    private Action action = new Action();


    public static void setClinic(Clinic _clinic) {
        clinic = _clinic;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//----------activity_main layout
    /**
     * Listener for "region"-buttons
     * */
    public void detectRegion(View v){
        switch (v.getId()){
            case R.id.brestButton:
                region = 1;
                break;
            case R.id.vitebskButton:
                region = 2;
                break;
            case R.id.gomelButton:
                region = 3;
                break;
            case R.id.grodnoButton:
                region = 4;
                break;
            case R.id.minskButton:
                region = 5;
                break;
            case R.id.mogilevButton:
                region = 6;
                break;
            case R.id.minskCityButton:
                region = 7;
                break;
            default:
                region = 0;
                break;
        }
        detectClinic(region);
    }

//----------clinic_layout
    public void backToRegion(View v){
        setContentView(R.layout.activity_main);
    }

    /**
     * Listener for "clinic"-buttons
     * */
    public void detectClinic(int region){
        setContentView(R.layout.clinic_layout);
        ListView clinics = (ListView) findViewById(R.id.clinicListView);
        ArrayAdapter<Clinic> clinicArrayAdapter = new ClinicAdapter(this, DataProvider.getClinicsList(region));
        clinics.setAdapter(clinicArrayAdapter);
    }

//----------input_personal_data_layout
    public void backToClinic(View v){
        detectClinic(region);
    }

    public void validatePersonAtClinic(View v){
        person.setName(((EditText) findViewById(R.id.nameEditText)).getText().toString());
        person.setLastName(((EditText) findViewById(R.id.lastNameEditText)).getText().toString());
        person.setPatronymic(((EditText) findViewById(R.id.patronymicEditText)).getText().toString());
        person.setTown(((EditText) findViewById(R.id.townEditText)).getText().toString());
        person.setAddress(((EditText) findViewById(R.id.adressEditText)).getText().toString());
        person.setDateOfBirthday(new Date(((CalendarView) findViewById(R.id.DOBCalendarView)).getDate()));
        if(ExtraCalculation.isClient(clinic, person)){
            setContentView(R.layout.main_menu_layout);
        }else{
            Toast.makeText(this, "В картотеке учреждения \"" + clinic.getTitle() +
                    "\" отсутствуют записи о пациенте с такими данными. Проверьте правильность введённых данных!", Toast.LENGTH_LONG).show();
        }
    }

//----------main_menu_layout
    public void selectSpeciality(View v){
        switch (v.getId()){
            case R.id.orderTicketButton:
                action.setActionType(ActionType.ORDER_TICKET);
                break;
            case R.id.callDoctorButton:
                action.setActionType(ActionType.CALL_DOCTOR);
                break;
        }
        setContentView(R.layout.select_speciality_layout);
        clinic.setStaff(DataProvider.getDoctors(clinic));
        Spinner specialitySpinner = (Spinner)findViewById(R.id.specialitySpinner);
        String[] items = ExtraCalculation.specialityOfStaff(clinic);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        specialitySpinner.setAdapter(adapter);
        specialitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void logout(View v){
        setContentView(R.layout.activity_main);
    }

//---------select_speciality_layout



}
