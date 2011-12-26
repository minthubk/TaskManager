package com.jeffgabriel.TaskManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DatabaseHelper extends SQLiteOpenHelper implements com.jeffgabriel.TaskManager.Interfaces.IDbHelper{

	private static String DB_PATH = "/data/data/com.jeffgabriel.TaskManager/databases/";
	private static final String DatabaseName = "taskDb";
	private static final int DatabaseVersion = 2;
	private SQLiteDatabase _dataBase;
	private final Context _context;
	
	
	public DatabaseHelper(Context context){
		this(context,DatabaseName,null,DatabaseVersion);
	}
	
	public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		_context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	}
	
	public void createDataBase() throws IOException{
		 
    	boolean dbExist = checkDataBase();
 
    	if(dbExist){
    		//do nothing - database already exist
    	}
    	else{
 
    		//By calling this method and empty database will be created into the default system path
               //of your application so we are gonna be able to overwrite that database with our database.
        	SQLiteDatabase empty = this.getReadableDatabase();
        	String emptyPath = empty.getPath();
 
        	try {
 
    			copyDataBase();
 
    		} catch (IOException e) {
 
        		throw new Error("Error copying database");
 
        	}
    	}
 
    }
	
	 /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){
 
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = DB_PATH + DatabaseName;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    	}catch(SQLiteException e){
 
    		//database does't exist yet.
 
    	}
 
    	if(checkDB != null){
 
    		checkDB.close();
 
    	}
 
    	return checkDB != null ? true : false;
    }
 
    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transferring bytestream.
     * */
    private void copyDataBase() throws IOException{
 
    	//Open your local db as the input stream
    	InputStream myInput = _context.getAssets().open(DatabaseName);
 
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DatabaseName;
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }
 
    public void openDataBase() throws SQLException{
    	//Open the database
        String myPath = DB_PATH + DatabaseName;
    	_dataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
 
    }
 
    @Override
	public synchronized void close() {
 
    	    if(_dataBase != null)
    		    _dataBase.close();
 
    	    super.close();
 
	}
    
    static final String table = "Tasks";
    static final int idColumn = 0;
    static final int NameColumn = 1;
    static final int DueDateColumn = 2;
    static final int CategoryColumn = 3;
    static final int CreateDateColumn = 4;
    static final int CompleteColumn =  5;
    
    public ArrayList<Task> getTasks(String query, String[] args){
    	
    	String[] columns = null;
    	String orderBy = "DueDate DESC";
    	ArrayList<Task> tasks = new ArrayList<Task>();
    	this.openDataBase();
    	try{
    		Cursor results = _dataBase.query(table, columns, query, args, null, null, orderBy);
    		if(results.moveToFirst()){
    			while(results.isAfterLast() == false){
    				Task task = new Task(results.getInt(idColumn)
    						,results.getString(NameColumn)
    						,FromEpoch(results.getLong(DueDateColumn))
    						,results.getInt(CompleteColumn) != 0);
    				tasks.add(task);
    				results.moveToNext();
    			}
    		}
    		results.close();
    	}
    	finally{
    		this.close();
    	}
    	return tasks;
    }
    
    String whereClause = "_id = ?";
    public boolean delete(int taskId){
    	this.openDataBase();
    	try{
    		return _dataBase.delete(table, whereClause, new String[]{Integer.toString(taskId)}) == 1;
    	}
    	finally{
    		this.close();
    	}
    }
    
    public Task add(Task task) throws SQLException{
    	if(task.get_id() > 0)
    		return this.update(task);
    	this.openDataBase();
    	try{
    		int id = (int)_dataBase.insert(table, null, task.getValuesMap());
    		task.set_id(id);
    	}
    	finally{
    		this.close();
    	}
    	
    	return task;
    }
    
    public Task update(Task task) throws SQLException {
    	this.openDataBase();
    	try{
    		_dataBase.update(table, task.getValuesMap(),whereClause,  new String[]{Integer.toString(task.get_id())});
    	}
    	finally{
    		this.close();
    	}
    	return task;
	}

	private Date FromEpoch(Long dateEpoch){
    	return new Date(dateEpoch);
    }
    
//    @Override
//    public synchronized SQLiteDatabase getWritableDatabase(){
//    	return null;
//    }
//    
//    @Override
//    public synchronized SQLiteDatabase getReadableDatabase(){
//    	return null;
//    }

}
