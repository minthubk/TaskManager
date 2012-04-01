package com.jeffgabriel.TaskManager;

import java.io.Serializable;

import android.content.Intent;

public class TaskIntent {

	static final String CREATE_TASK_ACTION = "com.jeffgabriel.TaskManager.CreateTask";
	static final String DELETE_TASK_ACTION = "com.jeffgabriel.TaskManager.DeleteTask";
	static final String UPDATE_TASK_ACTION = "com.jeffgabriel.TaskManager.UpdateTask";
	static final String DISPLAY_TASK_ACTION = "com.jeffgabriel.TaskManager.ShowTask";
	static final String TASK_DATA_KEY = "TaskData";

	Task _task;
	TaskProvider _provider;
	Intent _intent;

	public TaskIntent(Intent intent) {
		_intent = intent;
		Serializable payload = intent.getSerializableExtra(TASK_DATA_KEY);
		if (payload != null)
			setTask((Task) payload);
	}

	Task getTask() {
		if (_task == null && _intent.getData() != null) {
			int taskId = Task.getIdFromUri(_intent.getData());
			if (_provider != null)
				setTask(_provider.get(taskId));
		}
		return _task;
	}

	void setTask(Task task) {
		_task = task;
		_intent.setData(task.get_Uri());
		_intent.putExtra(TASK_DATA_KEY, task);
	}

}
