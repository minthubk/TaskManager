package com.jeffgabriel.TaskManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AlarmService {
	private Context context;

	public AlarmService() {
		super();
	}

	public AlarmService(Context context) {
		this.context = context;
	}

	public void startAlarm(Task taskToSchedule) {
		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent sender = new Intent(TaskIntent.DISPLAY_TASK_ACTION, taskToSchedule.get_Uri(),
				context, AlarmReceiver.class);
		sender.putExtra(TaskIntent.TASK_DATA_KEY, taskToSchedule);
		PendingIntent alarmSender = PendingIntent.getBroadcast(context, 0,
				sender, 0);
		am.set(AlarmManager.RTC_WAKEUP, taskToSchedule.get_dueDate().getTime(),
				alarmSender);
	}
}
