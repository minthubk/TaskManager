package com.jeffgabriel.TaskManager.Interfaces;

import java.util.ArrayList;

import com.jeffgabriel.TaskManager.Task;

public interface ITaskProvider {

	public abstract void delete(com.jeffgabriel.TaskManager.Task task);
	public abstract ArrayList<com.jeffgabriel.TaskManager.Task> getAll();
	public abstract void update(Task task);
	public abstract Task get(int id);

}