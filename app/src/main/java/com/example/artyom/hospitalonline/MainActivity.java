package com.example.artyom.hospitalonline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artyom.hospitalonline.adapter.ClinicAdapter;
import com.example.artyom.hospitalonline.dataProvider.DataProvider;
import com.example.artyom.hospitalonline.model.Action;
import com.example.artyom.hospitalonline.model.ActionType;
import com.example.artyom.hospitalonline.model.Clinic;
import com.example.artyom.hospitalonline.model.Patient;
import com.example.artyom.hospitalonline.util.ExtraCalculation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int region;
    private int day = 0;
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
        setContentView(R.layout.ordet_ticket_and_call_doctor_layout);
        loadOrderTicketAndCallDoctorForm();
    }

    public void logout(View v){
        setContentView(R.layout.activity_main);
    }

//---------ordet_ticket_and_call_doctor_layout
    public void backToMenu(View v){
        setContentView(R.layout.main_menu_layout);
    }

    public void loadOrderTicketAndCallDoctorForm(){
        clinic.setStaff(DataProvider.getDoctors(clinic));
        final Spinner specialitySpinner = (Spinner)findViewById(R.id.specialitySpinner),
                doctorSpinner = (Spinner) findViewById(R.id.doctorSpinner),
                availableDaysSpinner = (Spinner) findViewById(R.id.availableDaysSpinner),
                availableTimeSpinner = (Spinner) findViewById(R.id.availableTimeSpinner);
        final Button finishButton = (Button) findViewById(R.id.finishButton);
        specialitySpinner.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, ExtraCalculation.specialityOfStaff(clinic)));
        specialitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doctorSpinner.setAdapter(new ArrayAdapter<>(parent.getContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        ExtraCalculation.getDoctorsFromSpeciality(clinic, parent.getItemAtPosition(position).toString())));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        doctorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                action.setDoctor(ExtraCalculation.getDoctorFromName(clinic, parent.getItemAtPosition(position).toString()));
                String[] items = ExtraCalculation.getAvailableDaysFromDoctor(action.getDoctor());
                if(items.length == 0){
                    availableDaysSpinner.setEnabled(false);
                    availableTimeSpinner.setEnabled(false);
                    finishButton.setEnabled(false);
                } else{
                    availableDaysSpinner.setEnabled(true);
                    availableTimeSpinner.setEnabled(true);
                    finishButton.setEnabled(true);
                    availableDaysSpinner.setAdapter(new ArrayAdapter<>(parent.getContext(),
                            android.R.layout.simple_spinner_dropdown_item, items));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        availableDaysSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                availableTimeSpinner.setAdapter(new ArrayAdapter<>(parent.getContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        ExtraCalculation.getAvailableSessionsFromDoctor(action.getDoctor(), position)));
                setDay(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        availableTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                action.setDate(action.getDoctor().getFreeSession().get(day).get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void finishOfAction(View v){
        person.getHistory().add(action);
        backToMenu(v);
        final TextView statusTextView = (TextView) findViewById(R.id.statusTextView);
        statusTextView.setText("Выполнено!Просмотреть или отменить заказ можно в разделе \"История\"");
    }

    public void setDay(int day) {
        this.day = day;
    }

}
