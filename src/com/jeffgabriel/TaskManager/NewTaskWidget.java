package com.jeffgabriel.TaskManager;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.sql.Time;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;

public class NewTaskWidget extends LinearLayout {

	EditText newTaskName;
	DatePicker newTaskDate;
	TimePicker newTime;

	public NewTaskWidget(Context context) {
		super(context);
	}

	public NewTaskWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		((Activity) getContext()).getLayoutInflater().inflate(
				R.layout.new_task, this);
		newTaskName = (EditText) findViewById(R.id.newtaskName);
		newTaskDate = (DatePicker) findViewById(R.id.newTaskDate);
		newTime = (TimePicker)findViewById(R.id.timePicker);
		clearForm();
	}

	void clearForm() {
		newTaskName.setText("");
		Calendar cal = Calendar.getInstance(
				TimeZone.getTimeZone("America/New_York"), Locale.US);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		newTaskDate.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH));
		newTime.setCurrentHour(8);
		newTime.setCurrentMinute(0);
	}
	
	Time getTime(){
		Time pickedTime = new Time(newTime.getCurrentHour(),newTime.getCurrentMinute(),0);
		return pickedTime;
	}
	
	Date getDate(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, newTaskDate.getDayOfMonth());
		cal.set(Calendar.MONTH, newTaskDate.getMonth());
		cal.set(Calendar.YEAR,  newTaskDate.getYear());
		return cal.getTime();
	}
	
	Date getDateAndTime(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(getDate());
		cal.set(Calendar.HOUR_OF_DAY, newTime.getCurrentHour());
		cal.set(Calendar.MINUTE, newTime.getCurrentMinute());
		return cal.getTime();
	}
	
	String getTaskName(){
		return newTaskName.getText().toString();
	}
}
