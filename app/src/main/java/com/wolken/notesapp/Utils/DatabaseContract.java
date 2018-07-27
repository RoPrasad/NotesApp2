package com.wolken.notesapp.Utils;

import android.provider.BaseColumns;



public class DatabaseContract {

    private DatabaseContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "todo";
        public static final String COLUMN_NAME_NAME = "taskName";
        public static final String COLUMN_NAME_DESCRIPTION = "taskDescription";
        public static final String COLUMN_NAME_DATE = "taskDate";
        public static final String COLUMN_NAME_ID = "taskID";
    }

}






