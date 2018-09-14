package com.example.spandananakkireddy.hw2_group32;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.LinkedList;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    static EditText ettitle;
    static EditText etdate;
    static EditText ettime;
    RadioGroup rgpriority;
    Button btnsave;
    Task task;
    RadioButton rbhigh,rbmedium,rblow;
    int count = 0;
    static String Edit_Key="edit";
    static String EditTask_Key = "Edittask";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ettitle = (EditText) findViewById(R.id.ettitle);
        etdate = (EditText) findViewById(R.id.etdate);
        etdate.setOnClickListener(this);
        ettime = (EditText) findViewById(R.id.ettime);
        ettime.setOnClickListener(this);
        rgpriority = (RadioGroup) findViewById(R.id.rgpriority);
        btnsave = (Button) findViewById(R.id.btnsave);
        rbhigh = (RadioButton) findViewById(R.id.rbhigh);
        rbmedium = (RadioButton) findViewById(R.id.rbmedium);
        rblow = (RadioButton) findViewById(R.id.rblow);
        setTitle("Edit Task");


        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getSerializable(MainActivity.Main_Key) != null) {
                task = (Task) getIntent().getExtras().getSerializable(MainActivity.Main_Key);
                ettitle.setText(task.getTitle());
                etdate.setText(task.getDate());
                ettime.setText(task.getTime());
                Log.d("tas",task.getPriority() +"");
                if(task.getPriority().equals("High Priority"))
                {
                    rgpriority.check(R.id.rbhigh);
                }
                if(task.getPriority().equals("Low Priority"));
                {
                    rgpriority.check(R.id.rblow);
                }
                if(task.getPriority().equals("Medium Priority"));
                {
                    rgpriority.check(R.id.rbmedium);
                }

            }

            btnsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                            task.setTitle(ettitle.getText().toString());
                            task.setDate(etdate.getText().toString());
                            task.setTime(ettime.getText().toString());
                            task.setPriority(((RadioButton) findViewById(rgpriority.getCheckedRadioButtonId())).getText().toString());
                            Intent intent = new Intent(EditActivity.this, MainActivity.class);
                            intent.putExtra(Edit_Key, task);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
            });

        }
    }
            @Override
            public void onClick (View v){

                if(v.getId() == R.id.etdate) {
                    dateSelect(v);
                }
                if(v.getId() == R.id.ettime) {
                    timeSelect(v);
                }
            }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            etdate.setText((month + 1)+ "/" + dayOfMonth   + "/" + year);
        }
    }
    public static class TimePickerFragment extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            ettime.setText(hourOfDay + ":" + minute);
        }
    }

    public void timeSelect(View v)
    {
        DialogFragment newFragment = new EditActivity.TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");

    }

    public void dateSelect(View v)
    {
        DialogFragment newFragment = new EditActivity.DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

}
