package com.jeffgabriel.TaskManager;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.Calendar;
import java.util.Date;

import android.content.ContentValues;
import android.net.Uri;

public class Task implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 7527562295622776147L;
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

	public void set_id(int id) {
		this._id = id;
	}

	private String _name;
	private Date _dueDate;
	public String get_name() {
		return _name;
	}

	public void set_name(String name) {
		this._name = name;
	}

	public Date get_dueDate() {
		return _dueDate;
	}

	public void set_dueDate(Date dueDate) {
		this._dueDate = dueDate;
	}

	public boolean get_isComplete() {
		return _isComplete;
	}

	public void set_isComplete(boolean isComplete) {
		this._isComplete = isComplete;
		if(isComplete == true)
			this.set_completeDate(Calendar.getInstance().getTime());
	}

	private boolean _isComplete = false;
	
	private Date _completeDate;
	public Date get_completeDate() {
		return _completeDate;
	}

	public void set_completeDate(Date completeDate) {
		this._completeDate = completeDate;
	}
	
	private static final String HOST = "com.jeffgabriel.taskmanager";
	private static final String SCHEME = "content";
	private static final String PATH = "task";
	private static final String URI_FORMAT = "%s://%s/%s/%d";
	
	public Uri get_Uri()
	{
		Uri taskUri = Uri.parse(String.format(URI_FORMAT, SCHEME,HOST,PATH,this.get_id()));
		return taskUri;
	}
	
	static int getIdFromUri(Uri intentUri){
		validateUri(intentUri);
		String idString = intentUri.getLastPathSegment();
		return Integer.parseInt(idString);
	}
	
	static void validateUri(Uri uri){
		if(uri != null 
				&& uri.getScheme().equalsIgnoreCase(SCHEME)
				&& uri.getHost().equalsIgnoreCase(HOST)
				&& uri.getPathSegments().get(0).equalsIgnoreCase(PATH))
			return;
		throw new InvalidParameterException("The Uri provided is not a Task Uri.");
	}

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
		if(this._completeDate != null)
			values.put("CompleteDate", this._completeDate.getTime());
		return values;
	}
	
}
