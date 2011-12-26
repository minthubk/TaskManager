package com.jeffgabriel.TaskManager;

import java.util.Calendar;
import java.util.Date;

import com.jeffgabriel.TaskManager.Interfaces.IDbHelper;

import android.app.Activity;
import android.content.Context;
import android.graphics.YuvImage;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

public class NewTaskWidget extends LinearLayout {

	EditText newTaskName;
	DatePicker newTaskDate;
	
	public NewTaskWidget(Context context) {
		super(context);
	}

	public NewTaskWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onFinishInflate(){
			super.onFinishInflate();
			((Activity)getContext()).getLayoutInflater().inflate(R.layout.new_task,this);
			newTaskName = (EditText)findViewById(R.id.newtaskName);
			newTaskDate = (DatePicker)findViewById(R.id.newTaskDate);
			Button button = (Button)findViewById(R.id.saveTaskButton);
			if(button != null){
				button.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						String taskName = newTaskName.getText().toString();
						int day = newTaskDate.getDayOfMonth();
						int month = newTaskDate.getMonth();
						int year = newTaskDate.getYear();
						Date taskDate = new Date(year-1900,month,day);
						IDbHelper helper =  new DatabaseHelper(getContext());
						Task task = new Task(-1,taskName,taskDate,false);
						helper.add(task);
						clearForm();
						if(_currentTaskList != null)
							_currentTaskList.refreshTaskList();
					}
				});
			}
	}
	//HACK:need event
	//TODO:add event
	CurrentTasksWidget _currentTaskList;
	public void setTaskList(CurrentTasksWidget widget){
		_currentTaskList = widget;
	}
	
	private void clearForm(){
		newTaskName.setText("");
		Calendar cal = Calendar.getInstance();
		newTaskDate.updateDate(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
	}
}
