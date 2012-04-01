package com.jeffgabriel.TaskManager;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Options extends PreferenceActivity {
	final Activity currentActivity = this;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.preferences);
	}
}
