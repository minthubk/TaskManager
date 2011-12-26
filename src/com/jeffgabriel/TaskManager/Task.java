package com.jeffgabriel.TaskManager;

import java.util.Date;
import java.util.Calendar;

import android.content.ContentValues;

public class Task {
	
//CTORS
	public Task(){
		this(-1,"",Calendar.getInstance().getTime(),false);
	}
	
	public Task(int id, String name, Date dueDate, boolean isComplete) {
		_id = id;
		_name = name;
		_dueDate = dueDate;
		_isComplete = isComplete;
	}
//END CTORS
	
//PROPERTIES
	private int _id = -1;
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	private String _name;
	private Date _dueDate;
	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public Date get_dueDate() {
		return _dueDate;
	}

	public void set_dueDate(Date _dueDate) {
		this._dueDate = _dueDate;
	}

	public boolean get_isComplete() {
		return _isComplete;
	}

	public void set_isComplete(boolean _isComplete) {
		this._isComplete = _isComplete;
	}

	private boolean _isComplete = false;
//END PROPERTIES	

	public ContentValues getValuesMap(){
		ContentValues values = new ContentValues();
		values.put("Name",this._name);
		if(this._dueDate != null)
			values.put("DueDate", this._dueDate.getTime());
		//TODO: match with proper categories
		values.put("CategoryId",1);
		values.put("CreateDate", Calendar.getInstance().getTimeInMillis());
		values.put("IsComplete", this._isComplete ? 1 : 0 );
		return values;
	}
	
}
