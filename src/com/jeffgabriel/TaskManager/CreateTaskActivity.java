package com.jeffgabriel.TaskManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jeffgabriel.TaskManager.Interfaces.IDbHelper;

public class CreateTaskActivity extends Activity implements
		View.OnClickListener {

	public CreateTaskActivity() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create);
		Button button = (Button) findViewById(R.id.saveTaskButton);
		if (button != null) {
			button.setOnClickListener(this);
		}
	}

	public void onClick(View v) {
		NewTaskWidget form = (NewTaskWidget) findViewById(R.id.newTaskForm);
		IDbHelper helper = new DatabaseHelper(this);
		Task task = new Task(-1, form.getTaskName(), form.getDateAndTime(),
				false);
		helper.add(task);
		form.clearForm();
		Intent createdIntent = new Intent(TaskIntent.CREATE_TASK_ACTION,
				task.get_Uri());
		createdIntent.putExtra(TaskIntent.TASK_DATA_KEY, task);
		setResult(RESULT_OK, createdIntent);
		sendBroadcast(createdIntent);
		AlarmService alarmSender = new AlarmService(this);
		alarmSender.startAlarm(task);
		this.finish();
	}
}
