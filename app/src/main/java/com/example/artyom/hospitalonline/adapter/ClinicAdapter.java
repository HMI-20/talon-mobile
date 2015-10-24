package com.example.artyom.hospitalonline.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.artyom.hospitalonline.R;
import com.example.artyom.hospitalonline.model.Clinic;

import java.util.List;

import static com.example.artyom.hospitalonline.MainActivity.*;

/**
 * Created by Artyom on 24.10.2015.
 */
public class ClinicAdapter extends ArrayAdapter<Clinic> {
    private  final List<Clinic> clinics;
    private final Activity context;

    public ClinicAdapter(Activity context, List<Clinic> clinics) {
        super(context, R.layout.clinic_item_layout, clinics);
        this.clinics = clinics;
        this.context = context;
    }

    /**
     * singleton
     * */
    static class ViewHolder {
        public int id;
        public TextView textView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View clinicView = convertView;
        if (clinicView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            clinicView = inflater.inflate(R.layout.clinic_item_layout, null, true);
            holder = new ViewHolder();
            holder.textView = (TextView) clinicView.findViewById(R.id.clinicTextView);
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setClinic(clinics.get(position));
                    context.setContentView(R.layout.input_personal_data_layout);
                }
            });
            clinicView.setTag(holder);
        } else {
            holder = (ViewHolder) clinicView.getTag();
        }
        holder.textView.setText(clinics.get(position).getTitle());
        return clinicView;
    }
}
