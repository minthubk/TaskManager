package com.jeffgabriel.TaskManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class TaskManagerActivity extends Activity {

	NewTaskWidget newTaskForm;
	CurrentTasksWidget taskTable;
	final Activity currentActivity = this;

	public static final String MENU_OPTION_KEY = "MenuItem";
	public static final String MENU_ITEM_OPEN = "OpenItem";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupViews();
	}

	private void setupViews() {
		newTaskForm = (NewTaskWidget) findViewById(R.id.newTaskForm);
		taskTable = (CurrentTasksWidget) findViewById(R.id.currentTasks);
		newTaskForm.setTaskList(taskTable);
		ImageView closeButton = (ImageView) findViewById(R.id.closeButton);
		closeButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				currentActivity.moveTaskToBack(true);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, Menu.NONE, Menu.FIRST, R.string.settingsMenuItem)
				.setIcon(R.drawable.settings);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		startActivity(new Intent(this, Options.class));
		return true;
	}
}