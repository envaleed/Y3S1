package com.example.admin.myapplication;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ScheduleDate extends Activity {
    DatePicker pickerDate;
    TimePicker pickerTime;
    Button buttonSetAlarm;
    Button save_exit;
    TextView info;
    private Switch progressSwitch;
    Spinner alarmRepeat;
    private int STRTDATE_CODE = 100;
    private int ENDATE_CODE = 101;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_date);

        info = (TextView)findViewById(R.id.info);
        pickerDate = (DatePicker)findViewById(R.id.pickerdate);
        pickerTime = (TimePicker)findViewById(R.id.pickertime);

        progressSwitch = (Switch) findViewById(R.id.progressSwitch);
        progressSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AlertDialog.Builder spinbuilder = new AlertDialog.Builder(ScheduleDate.this);
                    View dview = getLayoutInflater().inflate(R.layout.intervalspinner, null);
                    spinbuilder.setTitle("Reminder Checker");
                    final Spinner repeatSpinner = (Spinner) dview.findViewById(R.id.repeatSpinner);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ScheduleDate.this,
                            android.R.layout.simple_spinner_item,
                            getResources().getStringArray(R.array.RepeatInterval));
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    repeatSpinner.setAdapter(adapter);

                    spinbuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (repeatSpinner.getSelectedItem().toString().equalsIgnoreCase("every minute")) {
                                // Toast.makeText(AddStudy.this,progSpinner.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
                                //if (progSpinner.getSelectedItem().toString()== "1 time"){
                                Calendar current = Calendar.getInstance();

                                Calendar cal = Calendar.getInstance();
                                cal.set(pickerDate.getYear(),
                                        pickerDate.getMonth(),
                                        pickerDate.getDayOfMonth(),
                                        pickerTime.getCurrentHour(),
                                        pickerTime.getCurrentMinute(),
                                        00);
                                setAlarmMinute(cal);

                            } else if (repeatSpinner.getSelectedItem().toString().equalsIgnoreCase("every thirty minute")){
                                Calendar current = Calendar.getInstance();

                                Calendar cal = Calendar.getInstance();
                                cal.set(pickerDate.getYear(),
                                        pickerDate.getMonth(),
                                        pickerDate.getDayOfMonth(),
                                        pickerTime.getCurrentHour(),
                                        pickerTime.getCurrentMinute(),
                                        00);
                                setAlarmThirtyMinute(cal);

                            } else if (repeatSpinner.getSelectedItem().toString().equalsIgnoreCase("every hour")){
                                Calendar current = Calendar.getInstance();

                                Calendar cal = Calendar.getInstance();
                                cal.set(pickerDate.getYear(),
                                        pickerDate.getMonth(),
                                        pickerDate.getDayOfMonth(),
                                        pickerTime.getCurrentHour(),
                                        pickerTime.getCurrentMinute(),
                                        00);
                                setAlarmHour(cal);

                            } else if (repeatSpinner.getSelectedItem().toString().equalsIgnoreCase("every day")){
                                Calendar current = Calendar.getInstance();

                                Calendar cal = Calendar.getInstance();
                                cal.set(pickerDate.getYear(),
                                        pickerDate.getMonth(),
                                        pickerDate.getDayOfMonth(),
                                        pickerTime.getCurrentHour(),
                                        pickerTime.getCurrentMinute(),
                                        00);
                                setAlarmDay(cal);

                            } else if (repeatSpinner.getSelectedItem().toString().equalsIgnoreCase("every week")){
                                Calendar current = Calendar.getInstance();

                                Calendar cal = Calendar.getInstance();
                                cal.set(pickerDate.getYear(),
                                        pickerDate.getMonth(),
                                        pickerDate.getDayOfMonth(),
                                        pickerTime.getCurrentHour(),
                                        pickerTime.getCurrentMinute(),
                                        00);
                                setAlarmWeek(cal);

                            }
                        }
                    });
                    spinbuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    spinbuilder.setView(dview);
                    AlertDialog dialog = spinbuilder.create();
                    dialog.show();
                }
            }
        });




        Calendar now = Calendar.getInstance();

        pickerDate.init(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH),
                null);

        pickerTime.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
        pickerTime.setCurrentMinute(now.get(Calendar.MINUTE));

        //use to set deadline date
        buttonSetAlarm = (Button)findViewById(R.id.setalarm);
        buttonSetAlarm.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                Calendar current = Calendar.getInstance();

                Calendar cal = Calendar.getInstance();
                cal.set(pickerDate.getYear(),
                        pickerDate.getMonth(),
                        pickerDate.getDayOfMonth(),
                        pickerTime.getCurrentHour(),
                        pickerTime.getCurrentMinute(),
                        00);

                if(cal.compareTo(current) <= 0){
                    //The set Date/Time already passed
                    Toast.makeText(getApplicationContext(),
                            "Invalid Date/Time",
                            Toast.LENGTH_LONG).show();
                }else{
                    setEndDateAlarm(cal);
                }

            }});




        save_exit = (Button) findViewById(R.id.save_exitButton);
        save_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intentmessage = getIntent();
                    intentmessage.putExtra("alarmtime", info.getText().toString());
                    setResult(RESULT_OK, intentmessage);
                    finish();
            }
        });
    }



    private void setEndDateAlarm(Calendar targetCal) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        info.setText("Remainder stops at" + targetCal.getTime());

        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), ENDATE_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
    }

    private void setAlarmMinute(Calendar targetCal) {

        /*long when = System.currentTimeMillis() + 60000L;*/

        int minute =  1*60*1000;


        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        /*  ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();*/

        info.setText("Alarm set for" + targetCal.getTime());

        /* final int intent_id = (int) System.currentTimeMillis();*/
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        //if statement
        //add spinner

        /* intent.putExtra("myAction", "notify");*/
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), STRTDATE_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
               minute, pendingIntent);
        /*intentArray.add(pendingIntent);*/
    }

    private void setAlarmTwoMinute(Calendar targetCal) {

        /*long when = System.currentTimeMillis() + 60000L;*/

        int twoMinute =  2*60*1000;


        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        /*  ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();*/

        info.setText("Alarm set for" + targetCal.getTime());

        /* final int intent_id = (int) System.currentTimeMillis();*/
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        //if statement
        //add spinner

        /* intent.putExtra("myAction", "notify");*/
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), STRTDATE_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                twoMinute, pendingIntent);
        /*intentArray.add(pendingIntent);*/
    }

    private void setAlarmThirtyMinute(Calendar targetCal) {

        /*long when = System.currentTimeMillis() + 60000L;*/

        int thirtyMinute =  30*60*1000;


        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        /*  ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();*/

        info.setText("Alarm set for" + targetCal.getTime());

        /* final int intent_id = (int) System.currentTimeMillis();*/
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        //if statement
        //add spinner

        /* intent.putExtra("myAction", "notify");*/
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), STRTDATE_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                thirtyMinute, pendingIntent);
        /*intentArray.add(pendingIntent);*/
    }
    private void setAlarmHour(Calendar targetCal) {

        /*long when = System.currentTimeMillis() + 60000L;*/

        int hour =  60*60*1000;


        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        /*  ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();*/

        info.setText("Alarm set for" + targetCal.getTime());

        /* final int intent_id = (int) System.currentTimeMillis();*/
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        //if statement
        //add spinner

        /* intent.putExtra("myAction", "notify");*/
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), STRTDATE_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                hour, pendingIntent);
        /*intentArray.add(pendingIntent);*/
    }
    private void setAlarmDay(Calendar targetCal) {

        /*long when = System.currentTimeMillis() + 60000L;*/

        int day =  24*60*60*1000;


        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        /*  ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();*/

        info.setText("Alarm set for" + targetCal.getTime());

        /* final int intent_id = (int) System.currentTimeMillis();*/
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        //if statement
        //add spinner

        /* intent.putExtra("myAction", "notify");*/
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), STRTDATE_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                day, pendingIntent);
        /*intentArray.add(pendingIntent);*/
    }
    private void setAlarmWeek(Calendar targetCal) {

        /*long when = System.currentTimeMillis() + 60000L;*/

        int week =  7*24*60*60*1000;


        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        /*  ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();*/

        info.setText("Alarm set for" + targetCal.getTime());

        /* final int intent_id = (int) System.currentTimeMillis();*/
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        //if statement
        //add spinner

        /* intent.putExtra("myAction", "notify");*/
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), STRTDATE_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                week, pendingIntent);
        /*intentArray.add(pendingIntent);*/
    }
    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), STRTDATE_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
        Toast.makeText(getBaseContext(), "Alarm received!", Toast.LENGTH_LONG).show();

    }
}

