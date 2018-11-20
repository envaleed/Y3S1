package com.example.admin.myapplication;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;

import static java.util.Calendar.AM_PM;

public class AddStudy extends AppCompatActivity {

    private CheckBox cancelCheckbox;
    private Switch progressSwitch;
    private TextView studyGoal;
    private EditText studyDetail;
    private Button start_date;
    private TextView date_text;

    private TextView studyGoal1;
    private EditText studyDetail1;
    private Button end_date;
    private TextView date_text1;
    private TextView goalText1;

    final static int RQSALARM = 0;
    final static int RQSALARM1 = 1;
    final static int RQSALARM2 = 2;
    final static int RQSALARM3 = 3;


    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID = 1;

    private TextView activeDateDisplay;
    private Calendar activeDate;

    private TextView activeTimeDisplay;
    private Calendar activeTime;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_study);
        getSupportActionBar().setTitle("Create Study Schedule");

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


        date_text = (TextView) findViewById(R.id.date_text);
        start_date = (Button) findViewById(R.id.study_date);
        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddStudy.this, ScheduleDate.class);
                String alarm = date_text.getText().toString();
                intent.putExtra("RQSALARM",alarm);
                startActivityForResult(intent, RQSALARM);

            }
        });

        date_text1 = (TextView) findViewById(R.id.date_text1);
        end_date = (Button) findViewById(R.id.study_date1);
        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AddStudy.this, ScheduleDate.class);
                String alarm1 = date_text1.getText().toString();
                intent1.putExtra("RQSALARM1",alarm1);
                startActivityForResult(intent1, RQSALARM1);

                cancelCheckbox = (CheckBox) findViewById(R.id.cancelCheckBox);
                cancelCheckbox.setVisibility(View.VISIBLE);

            }
        });

        cancelCheckbox = (CheckBox) findViewById(R.id.cancelCheckBox);

        cancelCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cancelAlarm();
            }
        });

       /* updateAlarm(date_text,date);*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RQSALARM) {
            if (resultCode == RESULT_OK) {
                String message = data.getStringExtra("alarmtime");
                date_text.setText(message);
            }
        } else if (requestCode == RQSALARM1) {
            if (resultCode == RESULT_OK) {
                String message = data.getStringExtra("alarmtime");
                date_text1.setText(message);
            }
        }/*else if(requestCode == RQSALARM2) {
            if (resultCode == RESULT_OK) {
                String message = data.getStringExtra("alarmtime");
                date_text2.setText(message);
            }

        }else if(requestCode == RQSALARM3) {
            if (resultCode == RESULT_OK) {
                String message = data.getStringExtra("alarmtime");
                date_text3.setText(message);
            }*/
        }


        private void cancelAlarm () {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.cancel(pendingIntent);

        }
    }
        /*  private void updateAlarm(TextView date_text, Calendar date) {
        date_text = (TextView) findViewById(R.id.date_text);
        date_text.setText(getIntent().getExtras().getString("alarmtime"));
    }*/


      /*  date_text1 = (TextView) findViewById(R.id.date_text1);
        study_date1 = (Button) findViewById(R.id.study_date1);

        date1 = Calendar.getInstance();

        study_date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(date_text1, date1);
            }
        });

        date_text2 = (TextView) findViewById(R.id.date_text2);
        study_date2 = (Button) findViewById(R.id.study_date2);

        date2 = Calendar.getInstance();

        study_date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(date_text2, date2);
            }
        });

        date_text3 = (TextView) findViewById(R.id.date_text3);
        study_date3 = (Button) findViewById(R.id.study_date3);

        date3 = Calendar.getInstance();

        study_date3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(date_text3, date3);
            }
        });

        date_text4 = (TextView) findViewById(R.id.date_text4);
        study_date4 = (Button) findViewById(R.id.study_date4);

        date4 = Calendar.getInstance();

        study_date4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(date_text4, date4);
            }
        });
        updateDisplay(date_text, date);
        updateDisplay(date_text1, date1);
        updateDisplay(date_text2, date2);
        updateDisplay(date_text3, date3);
        updateDisplay(date_text4, date4);

        time_text = (TextView) findViewById(R.id.time_text);
        study_time = (Button) findViewById(R.id.study_time);

        time = Calendar.getInstance();

        study_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(time_text, time);
            }
        });

        time_text1 = (TextView) findViewById(R.id.text_time1);
        study_time1 = (Button) findViewById(R.id.study_time1);

        time1 = Calendar.getInstance();

        study_time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(time_text1, time1);
            }
        });

        time_text2 = (TextView) findViewById(R.id.time_text2);
        study_time2 = (Button) findViewById(R.id.study_time2);

        time2 = Calendar.getInstance();

        study_time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(time_text2, time2);
            }
        });

        time_text3 = (TextView) findViewById(R.id.time_text3);
        study_time3 = (Button) findViewById(R.id.study_time3);

        time3 = Calendar.getInstance();

        study_time3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(time_text3, time3);
            }
        });

        time_text4 = (TextView) findViewById(R.id.time_text4);
        study_time4 = (Button) findViewById(R.id.study_time4);

        time4 = Calendar.getInstance();

        study_time4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(time_text4, time4);
            }
        });
        updateTimeDisplay(time_text, time);
        updateTimeDisplay(time_text1, time1);
        updateTimeDisplay(time_text2, time2);
        updateTimeDisplay(time_text3, time3);
        updateTimeDisplay(time_text4, time4);


    }

   private void updateTimeDisplay(TextView timeDisplay, Calendar time) {
        timeDisplay.setText(
                new StringBuilder()
                .append(time.get(Calendar.HOUR_OF_DAY)).append(":")
                .append(time.get(Calendar.MINUTE)).append(""));
    }

  private void showTimeDialog(TextView timeDisplay, Calendar time) {
        activeTimeDisplay = timeDisplay;
        activeTime = time;
        showDialog(TIME_DIALOG_ID);
    }

    private void updateDisplay(TextView dateDisplay, Calendar date) {

        dateDisplay.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(date.get(Calendar.MONTH) + 1).append("-")
                        .append(date.get(Calendar.DAY_OF_MONTH)).append("-")
                        .append(date.get(Calendar.YEAR)).append(" "));

    }


    public void showDateDialog(TextView dateDisplay, Calendar date) {
        activeDateDisplay = dateDisplay;
        activeDate = date;
        showDialog(DATE_DIALOG_ID);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            activeDate.set(Calendar.YEAR, year);
            activeDate.set(Calendar.MONTH, monthOfYear);
            activeDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDisplay(activeDateDisplay, activeDate);
            unregisterDateDisplay();
        }
    };

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            activeTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            activeTime.set(Calendar.MINUTE, minute);

            updateTimeDisplay(activeTimeDisplay, activeTime);
            unregisterTimeDisplay();
            startAlarm();
        }
    };

    private void startAlarm(Calendar calendar) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    }

    private void unregisterDateDisplay() {
        activeDateDisplay = null;
        activeDate = null;
    }

    private void unregisterTimeDisplay() {
        activeTimeDisplay = null;
        activeTime = null;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, dateSetListener, activeDate.get(Calendar.YEAR), activeDate.get(Calendar.MONTH), activeDate.get(Calendar.DAY_OF_MONTH));

            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,timeSetListener, activeTime.get(Calendar.HOUR_OF_DAY), activeTime.get(Calendar.MINUTE), true);

        }
        return null;
    }


    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        switch (id) {
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(activeDate.get(Calendar.YEAR), activeDate.get(Calendar.MONTH), activeDate.get(Calendar.DAY_OF_MONTH));
                break;

            case TIME_DIALOG_ID:
                ((TimePickerDialog) dialog).updateTime(activeTime.get(Calendar.HOUR_OF_DAY), activeTime.get(Calendar.MINUTE));
                break;
        }
     }

*/

