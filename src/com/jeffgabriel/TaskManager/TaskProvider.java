package com.jeffgabriel.TaskManager;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;

import com.jeffgabriel.TaskManager.Interfaces.ITaskProvider;

public class TaskProvider implements ITaskProvider {

	com.jeffgabriel.TaskManager.Interfaces.IDbHelper _dbHelper;
	private final Context _context;
	
	public TaskProvider(com.jeffgabriel.TaskManager.Interfaces.IDbHelper helper, Context context) {
		_context = context;
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

	public void update(Task task) {
		if(task.get_dueDate() == null)
			throw new IllegalStateException(_context.getResources().getString(R.string.noDueDateError));
		_dbHelper.update(task);
	}
	
	String whereClause = "_id = ?";
	
	public Task get(int taskId) {
		ArrayList<Task> tasks = _dbHelper.getTasks(whereClause,new String[]{Integer.toString(taskId)});
		if(tasks.isEmpty() == false)
			return tasks.get(0);
		return null;
	}

}
