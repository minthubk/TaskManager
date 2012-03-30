package com.jeffgabriel.TaskManager;

import android.content.Context;
import android.preference.PreferenceManager;

public class PreferenceService {
	public static final String PREFS_NAME = "NoDeleteWarning";

	public static boolean getHideDeleteWarningPreference(Context context) {
		context = context.getApplicationContext();
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(PREFS_NAME, false);
	}

	public static void setHideDeleteWarningPreference(Context context,
			boolean shouldHideWarning) {
		context = context.getApplicationContext();
		PreferenceManager.getDefaultSharedPreferences(context).edit()
				.putBoolean(PREFS_NAME, shouldHideWarning).commit();
	}
}
