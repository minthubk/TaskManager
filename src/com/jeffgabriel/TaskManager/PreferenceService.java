package com.jeffgabriel.TaskManager;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;

public class PreferenceService {
	public static final String PREFS_NAME = "NoDeleteWarning";

	public static boolean getHideDeleteWarningPreference(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(PREFS_NAME, false);
	}

	public static void setHideDeleteWarningPreference(Context context,
			boolean shouldHideWarning) {
		PreferenceManager.getDefaultSharedPreferences(context).edit()
				.putBoolean(PREFS_NAME, shouldHideWarning).commit();
	}

	public static Uri getAlertSoundPreference(Context context) {
		String uriString = PreferenceManager.getDefaultSharedPreferences(
				context).getString(
				"alertSound",
				RingtoneManager
						.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
						.toString());
		return Uri.parse(uriString);
	}

	public static boolean getAlertShouldSoundPreference(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean("alertSoundOnOff", true);
	}
	
	public static int getDefaultListPageSize(Context context){
		return Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getString("listDisplaySize", "10"));
	}
	
	public static boolean getShowCompletedPreference(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("displayCompleted", false);
	}
	
	static Map<Uri,String> installedNotificationSounds(Context context) {
		Map<Uri,String> results = new HashMap<Uri,String>();
		RingtoneManager mgr = new RingtoneManager(context);
		mgr.setType(RingtoneManager.TYPE_NOTIFICATION);
		Cursor crsr = mgr.getCursor();
		if (crsr.moveToFirst()) {
			while (crsr.isAfterLast() == false) {
					String title = crsr.getString(RingtoneManager.TITLE_COLUMN_INDEX);
					Uri soundUri = Uri.parse(crsr.getString(RingtoneManager.URI_COLUMN_INDEX));
					results.put(soundUri, title);
			}
		}
		return results;
	}
}
