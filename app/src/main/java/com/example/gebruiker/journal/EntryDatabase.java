package com.example.gebruiker.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class EntryDatabase extends SQLiteOpenHelper {

    // creating variables
    private static EntryDatabase instance;
    private static final String TABLE_NAME = "ONENTRY";

    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name , factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating string to create table
        String CREATE_TABLE = "CREATE TABLE ONENTRY (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "title TEXT NOT NULL," +
                "text TEXT NOT NULL," +
                "mood TEXT NOT NULL," +
                "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        // create table
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // on upgrade, delete table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public static EntryDatabase getInstance(Context context){

        if (instance == null){
            instance = new EntryDatabase(context,  TABLE_NAME, null, 10);
        }
        return instance;
    }

    public Cursor selectAll(){

        // select all data in table and returning that data in cursor
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

    public void insert(JournalEntry entry_row){

        SQLiteDatabase db = getWritableDatabase();

        // create content values and putting data in it
        ContentValues cv = new ContentValues();
        cv.put("Title", entry_row.getTitle());
        cv.put("Text", entry_row.getContent());
        cv.put("Mood", entry_row.getMood());

        // insert data in table
        db.insert(TABLE_NAME, null, cv);
    }

    public void delete(int id){

        SQLiteDatabase db = getWritableDatabase();

        // delete row from table
        String query = "DELETE from ONENTRY WHERE _id = " + id + ";";
        db.execSQL(query);
    }
}
