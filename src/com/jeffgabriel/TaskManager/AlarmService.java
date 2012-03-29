package com.jeffgabriel.TaskManager;

import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class AlarmService {
	 private Context context;
	    public AlarmService(Context context) {
	        this.context = context;
	    }

	    public void startAlarm(Task taskToSchedule){
	        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
	        Intent sender = new Intent("ShowTask",Uri.parse("content://taskmanager/task/" + taskToSchedule.get_id()),context,AlarmReceiver.class);
	        PendingIntent alarmSender = PendingIntent.getBroadcast(context, 0, sender, 0);
	        am.set(AlarmManager.RTC_WAKEUP, CreateAlarmDate(taskToSchedule.get_dueDate()), alarmSender);
	    }
	    
	    private long CreateAlarmDate(Date baseDate){
	    	return new Date(baseDate.getYear(),baseDate.getMonth(),baseDate.getDate(),8,0).getTime();
	    }
}
