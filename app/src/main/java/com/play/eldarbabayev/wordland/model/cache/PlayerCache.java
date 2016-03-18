package com.play.eldarbabayev.wordland.model.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.play.eldarbabayev.wordland.model.jsonword.Word;
import com.play.eldarbabayev.wordland.model.provider.WordsContract;

import java.lang.ref.WeakReference;


/**
 * Cache that uses a content provider to store data
 */
public class PlayerCache {

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
    public PlayerCache(Context context) {
        // Store the context.
        mContext = new WeakReference<Context>(context);

    }

    /**
     * Helper method that creates a content values object that can be
     * inserted into the db's WordsEntry table from a given
     * Word object.
     */
    public ContentValues makePlayerWordDataContentValues(Word word) {
        ContentValues cv = new ContentValues();

        cv.put(WordsContract.PlayerWordEntry.COLUMN_PLAYER_WORD, word.getMainWord());
        cv.put(WordsContract.PlayerWordEntry.COLUMN_PLAYER_EXPLANATION, word.getExplanation());
        cv.put(WordsContract.PlayerWordEntry.COLUMN_PLAYER_WORD_ID, word.getWordId());

        return cv;
    }

    /**
     * Place the Word object into the cache.
     */
    public void putPlayerWord(Word word) {
        putPlayerWordImpl(word);
    }


    /**
     * Helper method that places a Word object into the
     * database.
     */
    private void putPlayerWordImpl(Word word) {

        // Create ContentValues to insert into the
        // database.
        ContentValues cvs;

        // Insert each word object into the ContentValues array.
        cvs = makePlayerWordDataContentValues(word);


        //  insert the rows into the Words table.
        mContext.get().getContentResolver()
                .insert
                        (WordsContract.PlayerWordEntry.PLAYER_WORDS_CONTENT_URI,
                                cvs);
    }

    /**
     * Attempts to retrieve the Word
     * object with corresponding id.
     */
    public Word getPlayerWord(long wordId) {
        // Selection statement that matches the wordId of the row
        // with the given wordId
        final String rowId =
                ""
                        + WordsContract.PlayerWordEntry.COLUMN_PLAYER_WORD_ID
                        + " = '"
                        + wordId
                        + "'";
        // Attempt to retrieve the word's data from the content
        // provider.
        try (Cursor cursor =
                     mContext.get().getContentResolver()
                             .query(WordsContract.PlayerWordEntry.PLAYER_WORDS_CONTENT_URI,
                                     null,
                                     rowId,
                                     null,
                                     null)) {
            // Check that the cursor isn't null and contains an item.
            if (cursor.moveToFirst()) {
                Log.v(TAG,
                        "Cursor not null and has first item");
                // Convert the contents of the cursor into a
                // Word object.
                return getPlayerWordFromCursor(cursor);
            } else
                // Query was empty or returned null.
                Log.d(TAG, "Cursor is null");
            return null;
        }
    }

    /**
     * Constructor using a cursor returned by the WordsProvider.
     * This cursor must contain all the data for the object - i.e., it
     * must contain a row for each Word object corresponding to the
     * Word object.
     */
    private Word getPlayerWordFromCursor(Cursor cursor) throws IllegalArgumentException {
        String mainWord =
                cursor.getString(cursor.getColumnIndexOrThrow(WordsContract.PlayerWordEntry.COLUMN_PLAYER_WORD));
        String explanation =
                cursor.getString(cursor.getColumnIndexOrThrow(WordsContract.PlayerWordEntry.COLUMN_PLAYER_EXPLANATION));

        // Return a Word object.
        return new Word(
                mainWord,
                explanation);
    }

    /**
     * Return the current number of Word objects in Database.
     *
     * @return size
     */
    public int sizePlayer() {
        // Query the data for all rows of the Weather Values table.
        try (Cursor cursor =
                     mContext.get().getContentResolver().query
                             (WordsContract.PlayerWordEntry.PLAYER_WORDS_CONTENT_URI,
                                     new String[]{WordsContract.PlayerWordEntry._ID},
                                     null,
                                     null,
                                     null)) {
            // Return the number of rows in the table, which is equivlent
            // to the number of objects
            return cursor.getCount();
        }
    }

    /**
     * Helper method that places a Word object into the
     * database.
     */
    public void deleteAllRecords() {

        //  insert the rows into the Words table.
        mContext.get().getContentResolver()
                .delete
                        (WordsContract.PlayerWordEntry.PLAYER_WORDS_CONTENT_URI,
                                null,
                                null);
    }

}
