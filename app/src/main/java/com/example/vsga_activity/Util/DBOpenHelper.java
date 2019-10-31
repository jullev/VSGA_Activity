package com.example.vsga_activity.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DBOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // has to be 1 first time or app will crash
    private static final String userTable = "user_list";
    private static final String DATABASE_NAME = "userlist";
    // Column names...
    public static final String KEY_ID = "_id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    // ... and a string array of columns.
    private static final String[] COLUMNS = {KEY_ID, USERNAME,PASSWORD};
    private static final String USER_TABLE_CREATE =
            "CREATE TABLE " + userTable + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY, " +
                    // will auto-increment if no value passed
                    PASSWORD + " TEXT,"+ USERNAME + " TEXT );";

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+userTable);
        onCreate(db);
    }

    public boolean insertUser (String username, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, username);
        contentValues.put(PASSWORD, pass);
        db.insert(userTable, null, contentValues);
        return true;
    }

    public Cursor getData(String username,String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from user_list where username='"+username+"' and password ='"+pass+"'", null );

        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, userTable);
        return numRows;
    }

    public boolean updateData (Integer id, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, username);
        contentValues.put(PASSWORD, password);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllData() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(USERNAME)));
            res.moveToNext();
        }
        return array_list;
    }
}
