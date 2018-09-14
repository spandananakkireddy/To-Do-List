package com.example.spandananakkireddy.hw2_group32;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.app.Dialog;
import android.text.format.DateFormat;
import android.support.v4.app.DialogFragment;

import java.text.ParseException;
import java.util.Calendar;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;


public class CreateTaskActivity extends FragmentActivity implements View.OnClickListener {

    static EditText ettitle;
    static EditText etdate;
    static EditText ettime;
    RadioGroup rgpriority;
    Button btnsave;
    Task task;
    static String Task_Key = "task";
    String title;
    String datee;
    String time;
    String priority;
    RadioButton rbhigh,rbmedium,rblow;
    int count = 0;
    static String Count_key;
    String datetime;
    Date dt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        ettitle = (EditText) findViewById(R.id.ettitle);

        etdate = (EditText) findViewById(R.id.etdate);
        etdate.setOnClickListener(this);
        etdate.setKeyListener(null);
        ettime = (EditText) findViewById(R.id.ettime);
        ettime.setOnClickListener(this);
        ettime.setKeyListener(null);
        rgpriority = (RadioGroup) findViewById(R.id.rgpriority);
        btnsave = (Button) findViewById(R.id.btnsave);
        rbhigh = (RadioButton)findViewById(R.id.rbhigh);
        rbmedium = (RadioButton)findViewById(R.id.rbmedium);
        rblow = (RadioButton)findViewById(R.id.rblow);
        setTitle("Create Task");

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (checkFields() ) {
                        datee = etdate.getText().toString();
                        time = ettime.getText().toString();
                        priority = ((RadioButton) findViewById(rgpriority.getCheckedRadioButtonId())).getText().toString();
                        title = ettitle.getText().toString();
                       /* datetime = datee + time;
                        SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                        try{
                            dt = sf.parse(datetime);
                        }catch (ParseException e){

                        }*/
                        task = new Task(title, datee, time, priority);
                        Intent intent = new Intent(CreateTaskActivity.this, MainActivity.class);
                        intent.putExtra(Task_Key, task);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Invalid data", Toast.LENGTH_LONG).show();
                    }
            }
        });
}

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.etdate) {
            dateSelect(v);
        }
        if(v.getId() == R.id.ettime) {
            timeSelect(v);
        }
    }

    public void timeSelect(View v)
    {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");

    }

    public void dateSelect(View v)
    {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
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
            etdate.setText( (month + 1 )+ "/" + dayOfMonth + "/" + year);
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
            String timeSet = "";
            if (hourOfDay > 12) {
                hourOfDay -= 12;
                timeSet = "PM";
            } else if (hourOfDay == 0) {
                hourOfDay += 12;
                timeSet = "AM";
            } else if (hourOfDay == 12){
                timeSet = "PM";
            }else{
                timeSet = "AM";
            }

            String min = "";
            if (minute < 10)
                min = "0" + minute ;
            else
                min = String.valueOf(minute);

            // Append in a StringBuilder
            String aTime = new StringBuilder().append(hourOfDay).append(':')
                    .append(min ).append(" ").append(timeSet).toString();
            ettime.setText(aTime);
        }
    };
            /*ettime.setText(hourOfDay + ":" + minute);*/


    public boolean checkFields(){
        if(ettitle.getText().toString().equals("") || !(checkTitle(ettitle))){
            ettitle.setError("Invalid Title");
            return false;
        }
          if(etdate.getText().toString().equals("")){
            etdate.setError("Field Cannot be Left Blank");
            return false;
        }
          if(ettime.getText().toString().equals("")){
            ettime.setError("Field Cannot be Left Blank");
            return false;
        }
          if(rgpriority.getCheckedRadioButtonId()== -1){
            Toast.makeText(this,"Please select a button",Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }

    }
    public static boolean checkTitle(EditText title)
    {
        String nameReg = "[a-zA-Z0-9]{1,20}";
        if (ettitle.getText().toString().matches(nameReg))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
