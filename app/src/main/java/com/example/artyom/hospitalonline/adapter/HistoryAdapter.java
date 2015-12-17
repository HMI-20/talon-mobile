package com.example.artyom.hospitalonline.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.provider.Settings;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artyom.hospitalonline.R;
import com.example.artyom.hospitalonline.dataProvider.DataProvider;
import com.example.artyom.hospitalonline.model.Action;
import com.example.artyom.hospitalonline.model.ActionType;
import com.example.artyom.hospitalonline.model.Clinic;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.artyom.hospitalonline.MainActivity.setClinic;
import static com.example.artyom.hospitalonline.dataProvider.DataProvider.getHistory;

/**
 * Created by Artyom on 10.12.2015.
 */
public class HistoryAdapter extends ArrayAdapter{
    private  final List<Action> history;
    private final Activity context;

    public HistoryAdapter(Activity context, List<Action> history) {
        super(context, R.layout.history_item_layout, history);
        this.history = history;
        this.context = context;
    }

    /**
     * singleton
     * */
    static class ViewHolder {
        public int id;
        public LinearLayout itemBackground;
        public ImageView actionTypeImageView;
        public TextView specialityTextView;
        public TextView doctorNameTextView;
        public TextView sessionDateTextView;
        public ImageButton deleteButton;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Action action = history.get(position);
        View historyView = convertView;
        if (historyView == null) {
            //initialize elements
            LayoutInflater inflater = context.getLayoutInflater();
            historyView = inflater.inflate(R.layout.history_item_layout, null, true);
            holder = new ViewHolder();
            holder.itemBackground = (LinearLayout)historyView.findViewById(R.id.itemBackground);
            holder.actionTypeImageView = (ImageView) historyView.findViewById(R.id.actionTypeImageView);
            holder.specialityTextView = (TextView) historyView.findViewById(R.id.specialityTextView);
            holder.doctorNameTextView = (TextView) historyView.findViewById(R.id.doctorNameTextView);
            holder.sessionDateTextView = (TextView) historyView.findViewById(R.id.sessionDateTextView);
            holder.deleteButton = (ImageButton) historyView.findViewById(R.id.deleteButton);
            context.registerForContextMenu(holder.deleteButton);
            holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    history.remove(position);
                    DataProvider.setHistory(history);
                    context.setContentView(R.layout.history_layout);
                    ListView historyListView = (ListView) context.findViewById(R.id.historyListView);
                    ArrayAdapter<Action> historyArrayAdapter = new HistoryAdapter(context, history);
                    historyListView.setAdapter(historyArrayAdapter);
                    Toast.makeText(context, "Заказ отменён!", Toast.LENGTH_LONG).show();
                }
            });
            historyView.setTag(holder);
        } else {
            holder = (ViewHolder) historyView.getTag();
        }
        holder.itemBackground.setBackgroundColor(
                (action.getDate().compareTo(Calendar.getInstance()) > 0) ? Color.parseColor("#F0D587") : Color.LTGRAY);
        holder.actionTypeImageView.setBackgroundResource(
                (action.getActionType() == ActionType.CALL_DOCTOR) ? R.drawable.doctor : R.drawable.ticket);
        holder.specialityTextView.setText(action.getDoctor().getSpeciality().toString());
        holder.doctorNameTextView.setText(action.getDoctor().getFullName());
        holder.sessionDateTextView.setText(new Date(action.getDate().getTimeInMillis())
                .toString().replace(" GMT ", " "));
        holder.deleteButton.setBackgroundResource(R.drawable.trash_bucket);
        return historyView;
    }

}
