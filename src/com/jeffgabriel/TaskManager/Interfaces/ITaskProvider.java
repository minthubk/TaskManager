package com.jeffgabriel.TaskManager.Interfaces;

import java.util.ArrayList;

public interface ITaskProvider {

	public abstract void delete(com.jeffgabriel.TaskManager.Task task);
	public abstract ArrayList<com.jeffgabriel.TaskManager.Task> getAll();

}