package com.play.eldarbabayev.wordland.model.cache;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.play.eldarbabayev.wordland.model.provider.WordsContract;

import java.lang.ref.WeakReference;

/**
 * Cache that uses a content provider to store data
 */
public class RatingCache {

    /**
     * LogCat tag.
     */
    private final static String TAG =
            WordCache.class.getSimpleName();

    /**
     * Store a weak Reference of Context so it can be collected
     * by Garbage Collector later.
     */
    private WeakReference<Context> mContext;

    /**
     * Constructor
     */
    public RatingCache(Context context) {
        // Store the context.
        mContext = new WeakReference<Context>(context);

    }

    /**
     * Helper method that places a Word object into the
     * database.
     */
    public void modifyRecords(int count, int rating) {

        // Create ContentValues to insert into the
        // database.
        ContentValues cvs = new ContentValues();

        // Insert each word object into the ContentValues array.
        cvs.put(WordsContract.PlayerRatingEntry.COLUMN_COUNT,count);
        cvs.put(WordsContract.PlayerRatingEntry.COLUMN_RATING,rating);


        //  insert the rows into the Words table.
        mContext.get().getContentResolver()
                .update
                        (WordsContract.PlayerRatingEntry.PLAYER_RATING_CONTENT_URI,
                                cvs,
                                null,
                                null);
    }

    /**
     * Attempts to retrieve the Word
     * object with corresponding id.
     */
    public int getRating() {

        // Attempt to retrieve the word's data from the content
        // provider.
        try (Cursor cursor =
                     mContext.get().getContentResolver()
                             .query(WordsContract.PlayerRatingEntry.PLAYER_RATING_CONTENT_URI,
                                     new String[] { WordsContract.PlayerRatingEntry.COLUMN_RATING },
                                     null,
                                     null,
                                     null)) {
            cursor.moveToFirst();
                Log.v(TAG,
                        "Cursor not null and has first item");
                // Convert the contents of the cursor into a
                // Word object.
                return cursor.getInt(cursor.getColumnIndexOrThrow(WordsContract.PlayerRatingEntry.COLUMN_RATING));

        }
    }


    /**
     * Attempts to retrieve the Word
     * object with corresponding id.
     */
    public int getCount() {

        // Attempt to retrieve the word's data from the content
        // provider.
        try (Cursor cursor =
                     mContext.get().getContentResolver()
                             .query(WordsContract.PlayerRatingEntry.PLAYER_RATING_CONTENT_URI,
                                     new String[] { WordsContract.PlayerRatingEntry.COLUMN_COUNT },
                                     null,
                                     null,
                                     null)) {
            // Check that the cursor isn't null and contains an item.
            cursor.moveToFirst();

                return cursor.getInt(cursor.getColumnIndexOrThrow(WordsContract.PlayerRatingEntry.COLUMN_COUNT));
            }
        }


}