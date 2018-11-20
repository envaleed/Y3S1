package com.example.admin.myapplication;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener)getActivity(), year, month, day);
    }
}

//    private DatePicker mdatePicker;
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        final Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//        View view = LayoutInflater.from(getActivity())
//                .inflate(R.layout.fragment_date_picker, null);
//
//        mdatePicker = (DatePicker) view.findViewById(R.id.date_picker);
//        mdatePicker.init(year, month, day, null);
//
//        Button mSetTime = (Button) view.findViewById(R.id.time_set);
//        mSetTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TimePickerDialog timepic = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                        calendar.set(Calendar.MINUTE, minute);
//                    }
//
//                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
//                timepic.show();
//            }
//        });
//
//        return new AlertDialog.Builder(getActivity())
//                .setView(view)
//                .setTitle(R.string.date_picker_title)
//                .setPositiveButton(android.R.string.ok,
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                int year = mdatePicker.getYear();
//                                int month = mdatePicker.getMonth();
//                                int day = mdatePicker.getDayOfMonth();
//                                int hour = calendar.get(Calendar.HOUR_OF_DAY);
//                                int minute = calendar.get(Calendar.MINUTE);
//                                Date date = new GregorianCalendar(year, month, day, hour, minute).getTime();
//                                sendResult(Activity.RESULT_OK, date);
//                            }
//                        })
//                .show();
//    }
//
//}
