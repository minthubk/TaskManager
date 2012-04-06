package com.jeffgabriel.TaskManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.jeffgabriel.TaskManager.Interfaces.ITaskProvider;

public class TaskProvider implements ITaskProvider {

	com.jeffgabriel.TaskManager.Interfaces.IDbHelper _dbHelper;
	private final Context _context;

	public TaskProvider(
			com.jeffgabriel.TaskManager.Interfaces.IDbHelper helper,
			Context context) {
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

	public List<Task> getAll() {
		return getSome(PreferenceService.getDefaultListPageSize(_context), 0);
	}

	String categoryWhere = " CategoryId = ? ";

	public List<Task> getSome(int pageSize, int pageIndex) {
		boolean showComplete = PreferenceService
				.getShowCompletedPreference(_context);
		String[] whereParams = null;
		String statusWhere = null;
		if (showComplete == false) {
			whereParams = new String[] { "0" };
			statusWhere = " IsComplete = ? ";
		}
		List<Task> fullResults = _dbHelper.getTasks(statusWhere, whereParams);
		if (pageSize > 0 && fullResults.isEmpty() == false) {
			int resultEndIndex = pageSize * (pageIndex + 1);
			resultEndIndex = resultEndIndex > fullResults.size() ? fullResults
					.size() : resultEndIndex;
			int startIndex = resultEndIndex > pageSize ? resultEndIndex
					- pageSize : 0;
			return fullResults.subList(startIndex, resultEndIndex);
		}
		return fullResults;
	}

	public void update(Task task) {
		if (task.get_dueDate() == null)
			throw new IllegalStateException(_context.getResources().getString(
					R.string.noDueDateError));
		_dbHelper.update(task);
	}

	String whereClause = "_id = ?";

	public Task get(int taskId) {
		ArrayList<Task> tasks = _dbHelper.getTasks(whereClause,
				new String[] { Integer.toString(taskId) });
		if (tasks.isEmpty() == false)
			return tasks.get(0);
		return null;
	}
}
