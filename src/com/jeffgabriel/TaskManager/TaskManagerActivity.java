package com.jeffgabriel.TaskManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class TaskManagerActivity extends Activity {

	final Activity currentActivity = this;
	CurrentTasksWidget currentTasks;

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
		currentTasks = (CurrentTasksWidget)findViewById(R.id.currentTasks);
		ImageView closeButton = (ImageView) findViewById(R.id.closeButton);
		closeButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				currentActivity.moveTaskToBack(true);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.settings) {
			startActivity(new Intent(this, Options.class));
			return true;
		} else if (item.getItemId() == R.id.addNewTask) {
			startActivityForResult(new Intent(this, CreateTaskActivity.class), 0);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onActivityResult (int requestCode, int resultCode, Intent data)
	{
		if(currentTasks != null)
			currentTasks.refreshTaskList();
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		if(currentTasks != null)
			currentTasks.refreshTaskList();
	}
}