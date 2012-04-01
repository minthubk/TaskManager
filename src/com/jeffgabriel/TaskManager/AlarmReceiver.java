package com.jeffgabriel.TaskManager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		NotificationManager mNM;
		mNM = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		TaskIntent intendedTask = new TaskIntent(intent);
		Task taskDue = intendedTask.getTask();
		if (taskDue == null || taskDue.get_isComplete())
			return;
		Notification notification = new Notification(R.drawable.icon,
				"Task Alarm", System.currentTimeMillis());
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.defaults |= Notification.DEFAULT_SOUND;
		Intent activityOpener = new Intent(context, TaskManagerActivity.class);
		activityOpener.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_ANIMATION);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				activityOpener, PendingIntent.FLAG_UPDATE_CURRENT);
		notification.setLatestEventInfo(context,
				context.getText(R.string.alarmServiceLabel), taskDue.get_name()
						+ " is now due", contentIntent);
		mNM.notify(R.string.alarmServiceLabel, notification);

	}
}
