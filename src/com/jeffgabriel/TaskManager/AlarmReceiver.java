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
        mNM = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        
        //Task still due - not complete?
        Task taskDue = new TaskProvider(new DatabaseHelper(context),context).get(getTaskId(intent));
        if(taskDue == null || taskDue.get_isComplete())
        	return;
        // Set the icon, scrolling text and timestamp
        Notification notification = new Notification(R.drawable.icon, "Task Alarm", System.currentTimeMillis());
        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(context, TaskManagerActivity.class), 0);
        // Set the info for the views that show in the notification panel.
        notification.setLatestEventInfo(context, context.getText(R.string.alarmServiceLabel), taskDue.get_name() + " is now due", contentIntent);
        // Send the notification.
        // We use a layout id because it is a unique number. We use it later to cancel.
        mNM.notify(R.string.alarmServiceLabel, notification);
    }

	private int getTaskId(Intent intent){
		String idString = intent.getData().getLastPathSegment();
		return Integer.parseInt(idString);
	}
}
	