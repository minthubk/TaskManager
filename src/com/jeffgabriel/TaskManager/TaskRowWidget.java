package com.jeffgabriel.TaskManager;

import java.util.Date;
import java.text.SimpleDateFormat;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TaskRowWidget extends LinearLayout {

	CheckBox taskCheck;
	TextView dateView;
	
	public TaskRowWidget(Context context) {
		super(context);
		initializeComponent();
	}

	public TaskRowWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		initializeComponent();
	}
	
	protected void initializeComponent(){
			((Activity)getContext()).getLayoutInflater().inflate(R.layout.task_view,this);
			taskCheck = (CheckBox)findViewById(R.id.taskIsComplete);
			dateView = (TextView)findViewById(R.id.taskDueDate);
	}
	
	public void setTaskData(Task task){
		if(task != null && taskCheck != null && dateView != null){
			taskCheck.setChecked(task.get_isComplete());
			taskCheck.setText(task.get_name());
			dateView.setText(getFormattedDate(task.get_dueDate()));
		}
	}
	
	private final String simpleDateFormat = "MM/dd/yyyy";
	private String getFormattedDate(Date date){
		SimpleDateFormat format = new SimpleDateFormat(simpleDateFormat);
		return format.format(date);
	}
}
