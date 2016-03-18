package com.play.eldarbabayev.wordland.model.provider;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


/**
 * Manages a local database for Words data.
 */
public class WordsDatabaseHelper extends SQLiteAssetHelper {
    /**
     * If the database schema is changed, the database version must be
     * incremented.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Database name.
     */
    public static final String DATABASE_NAME =
            "WordsDatabase.db";


    /**
     * Constructor for WordsDatabaseHelper.
     *
     * @param context
     */
    public WordsDatabaseHelper(Context context) {
        super(context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION);
    }




}
