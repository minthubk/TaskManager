package com.jeffgabriel.TaskManager;

import java.util.ArrayList;

import com.jeffgabriel.TaskManager.Interfaces.ITaskProvider;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

public class CurrentTasksWidget extends LinearLayout {
	//TODO: this should probably be a data-bound list.
	TableLayout taskTable;
	private static ITaskProvider _taskProvider = null;
	private static synchronized ITaskProvider getProvider(Context context){
		if(_taskProvider == null)
			_taskProvider = new TaskProvider(new DatabaseHelper(context),context);
		return _taskProvider;
	}
	
	public CurrentTasksWidget(Context context) {
		super(context);
		
	}

	public CurrentTasksWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onFinishInflate(){
			super.onFinishInflate();
			((Activity)getContext()).getLayoutInflater().inflate(R.layout.task_table,this);
			refreshTaskList();
	}
	
	private View createRowDivider()
	{
		View v = new View(getContext());
		v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
		v.setBackgroundColor(Color.rgb(51, 51, 51));
		return v;
	}
	
	void refreshTaskList(){
		taskTable = (TableLayout)findViewById(R.id.taskTable);
		if(taskTable != null){
			taskTable.removeAllViews();
			ArrayList<Task> tasks = getProvider(getContext()).getAll();
			for(Task task : tasks){
				TaskRowWidget row = new TaskRowWidget(this);
				row.setTaskData(task);
				taskTable.addView(row);
				taskTable.addView(createRowDivider());
			}
		}
	}
}
