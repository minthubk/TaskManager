package com.jeffgabriel.TaskManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class TaskManagerActivity extends Activity {
	
	NewTaskWidget newTaskForm;
	CurrentTasksWidget taskTable;
	final Activity currentActivity = this;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setupViews();
    }
    
    private void setupViews(){
    	newTaskForm = (NewTaskWidget)findViewById(R.id.newTaskForm);
    	taskTable = (CurrentTasksWidget)findViewById(R.id.currentTasks);
    	newTaskForm.setTaskList(taskTable);
    	ImageView closeButton = (ImageView)findViewById(R.id.closeButton);
    	closeButton.setOnClickListener(
    			new View.OnClickListener() {
    				public void onClick(View v) {
    					currentActivity.moveTaskToBack(true);
    				}
    			}
    			);
    }
}