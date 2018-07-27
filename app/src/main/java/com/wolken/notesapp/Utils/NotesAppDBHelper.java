package com.wolken.notesapp.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wolken.notesapp.Models.Task;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static com.wolken.notesapp.Utils.DatabaseContract.FeedEntry.COLUMN_NAME_DATE;
import static com.wolken.notesapp.Utils.DatabaseContract.FeedEntry.COLUMN_NAME_DESCRIPTION;
import static com.wolken.notesapp.Utils.DatabaseContract.FeedEntry.COLUMN_NAME_ID;
import static com.wolken.notesapp.Utils.DatabaseContract.FeedEntry.COLUMN_NAME_NAME;
import static com.wolken.notesapp.Utils.DatabaseContract.FeedEntry.TABLE_NAME;


public class NotesAppDBHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME_NAME + " TEXT," +
                    COLUMN_NAME_DESCRIPTION + " TEXT," +
                    COLUMN_NAME_DATE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "FeedReader.db";

    public NotesAppDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    ;
    private Long newRowId;

    public Long insertData(String name, String description, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        try {
            // Gets the data repository in write mode


            // Create a new map of values, where column names are the keys

            values.put(DatabaseContract.FeedEntry.COLUMN_NAME_NAME, name);
            values.put(DatabaseContract.FeedEntry.COLUMN_NAME_DESCRIPTION, description);
            values.put(DatabaseContract.FeedEntry.COLUMN_NAME_DATE, date);

            // Insert the new row, returning the primary key value of the new row
            newRowId = db.insert(TABLE_NAME, null, values);

        } catch (Exception e) {
            Log.d("NotesAppDBHelper", "DBException" + e.getMessage());
        }
        db.close();
        return newRowId;


    }

    public void deleteNote(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DatabaseContract.FeedEntry.TABLE_NAME, _ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }


    String name = null;

    String desc = null;

    String date = null;

    public List<Task> getAllNotes() {
        List<Task> notes = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + DatabaseContract.FeedEntry.TABLE_NAME + " ORDER BY " +
                DatabaseContract.FeedEntry.COLUMN_NAME_ID + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {

                name = (cursor.getString(cursor.getColumnIndex(DatabaseContract.FeedEntry.COLUMN_NAME_NAME)));
                desc = (cursor.getString(cursor.getColumnIndex(DatabaseContract.FeedEntry.COLUMN_NAME_DESCRIPTION)));
                date = (cursor.getString(cursor.getColumnIndex(DatabaseContract.FeedEntry.COLUMN_NAME_DATE)));

                Task task = new Task(name, desc, date, COLUMN_NAME_ID);
                notes.add(task);


            } while (cursor.moveToNext());

        }


        db.close();

        return notes;

    }
}