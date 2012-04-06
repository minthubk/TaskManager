package com.jeffgabriel.TaskManager.Interfaces;

import java.util.List;

import com.jeffgabriel.TaskManager.Task;

public interface ITaskProvider {

	public abstract void delete(com.jeffgabriel.TaskManager.Task task);
	public abstract List<com.jeffgabriel.TaskManager.Task> getAll();
	public abstract List<com.jeffgabriel.TaskManager.Task> getSome(int pageIndex, int pageSize);
	public abstract void update(Task task);
	public abstract Task get(int id);

}