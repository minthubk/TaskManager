package com.jeffgabriel.TaskManager;

import java.io.IOException;
import java.util.ArrayList;

import com.jeffgabriel.TaskManager.Interfaces.ITaskProvider;

public class TaskProvider implements ITaskProvider {

	com.jeffgabriel.TaskManager.Interfaces.IDbHelper _dbHelper;
	
	public TaskProvider(com.jeffgabriel.TaskManager.Interfaces.IDbHelper helper) {
		_dbHelper = helper;
		try {
			_dbHelper.createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(Task task) {
		_dbHelper.delete(task.get_id());
	}

	public ArrayList<Task> getAll(){
		return _dbHelper.getTasks(null, null);
	}

}
