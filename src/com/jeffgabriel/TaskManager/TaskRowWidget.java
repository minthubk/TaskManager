package com.jeffgabriel.TaskManager;

import java.util.Date;
import java.text.SimpleDateFormat;

import com.jeffgabriel.TaskManager.Interfaces.ITaskProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TaskRowWidget extends LinearLayout {

	CurrentTasksWidget _parentList;
	private static ITaskProvider _taskProvider = null;

	private static synchronized ITaskProvider getProvider(Context context) {
		if (_taskProvider == null)
			_taskProvider = new TaskProvider(new DatabaseHelper(context), context);
		return _taskProvider;
	}

	CheckBox taskCheck;
	TextView dateView;
	Task _task;

	public TaskRowWidget(CurrentTasksWidget taskWidget) {
		super(taskWidget.getContext());
		_parentList = taskWidget;
		initializeComponent();
	}

	public TaskRowWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		initializeComponent();
	}

	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:
				getProvider(getContext()).delete(_task);
				//TODO:throw deleted event to force refresh;
				_parentList.refreshTaskList();
				break;
			case DialogInterface.BUTTON_NEGATIVE:
				break;
			}

		}
	};
	
	protected void initializeComponent() {
		((Activity) getContext()).getLayoutInflater().inflate(
				R.layout.task_view, this);
		taskCheck = (CheckBox) findViewById(R.id.taskIsComplete);
		taskCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				_task.set_isComplete(isChecked);				
				getProvider(getContext()).update(_task);
				if(_task.get_isComplete())
					taskCheck.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
				else
					taskCheck.setPaintFlags(Paint.LINEAR_TEXT_FLAG);
			}
		});
		dateView = (TextView) findViewById(R.id.taskDueDate);
		Button button = (Button) findViewById(R.id.btnDeleteTask);
		if (button != null)
			button.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							getContext());
					builder.setMessage(R.string.deleteWarning)
							.setPositiveButton("Yes", dialogClickListener)
							.setNegativeButton("No", dialogClickListener)
							.show();
				}
			});
	}

	public void setTaskData(Task task) {
		if (task != null && taskCheck != null && dateView != null) {
			_task = task;
			taskCheck.setChecked(task.get_isComplete());
			taskCheck.setText(task.get_name());
			dateView.setText(getFormattedDate(task.get_dueDate()));
		}
	}

	private final String simpleDateFormat = "MM/dd/yyyy";

	private String getFormattedDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(simpleDateFormat);
		return format.format(date);
	}
}
