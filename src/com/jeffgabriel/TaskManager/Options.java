package com.jeffgabriel.TaskManager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Options extends Activity {
	final Activity currentActivity = this;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkbox);
		CheckBox taskCheck = (CheckBox) findViewById(R.id.skip);
		if (taskCheck == null)
			return;
		taskCheck.setText(R.string.deleteWarningNoShowOptionLabel);
		taskCheck.setChecked(PreferenceService
				.getHideDeleteWarningPreference(this));
		taskCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				PreferenceService.setHideDeleteWarningPreference(currentActivity, isChecked);
			}
		});
	}
}
