package com.play.eldarbabayev.wordland.model.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.play.eldarbabayev.wordland.model.jsonword.Word;
import com.play.eldarbabayev.wordland.model.provider.WordsContract;

import java.lang.ref.WeakReference;
import java.util.ArrayList;


/**
 * Cache that uses a content provider to store data
 */
public class WordCache {

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
    public WordCache(Context context) {
        // Store the context.
        mContext = new WeakReference<Context>(context);

    }
    /**
     * Helper method that creates a content values object that can be
     * inserted into the db's WordsEntry table from a given
     * Word object.
     */
    public ContentValues makeCustomWordDataContentValues(Word word) {
        ContentValues cv = new ContentValues();

        cv.put(WordsContract.CustomWordsEntry.COLUMN_MAIN_WORD_CUSTOM, word.getMainWord());
        cv.put(WordsContract.CustomWordsEntry.COLUMN_EXPLANATION_CUSTOM, word.getExplanation());

        return cv;
    }

    /**
     * Helper method that creates a content values object that can be
     * inserted into the db's WordsEntry table from a given
     * Word object.
     */
    public ContentValues makeCustomWordDataContentValuesWithId(Word word) {
        ContentValues cv = new ContentValues();

        cv.put(WordsContract.CustomWordsEntry.COLUMN_MAIN_WORD_CUSTOM, word.getMainWord());
        cv.put(WordsContract.CustomWordsEntry.COLUMN_EXPLANATION_CUSTOM, word.getExplanation());
        cv.put(WordsContract.CustomWordsEntry.COLUMN_WORDID_CUSTOM, word.getWordId());

        return cv;
    }

    /**
     * Helper method that creates a content values object that can be
     * inserted into the db's WordsEntry table from a given
     * Word object.
     */
    public ContentValues makeBaseWordDataContentValues(Word word) {
        ContentValues cv = new ContentValues();

        cv.put(WordsContract.BaseWordsEntry.COLUMN_MAIN_WORD_BASE, word.getMainWord());
        cv.put(WordsContract.BaseWordsEntry.COLUMN_EXPLANATION_BASE, word.getExplanation());

        return cv;
    }




    /**
     * Place the Word object into the cache.
     */
    public void putCustomWord(Word word) {
        putCustomWordImpl(word);
    }


    /**
     * Helper method that places a Word object into the
     * database.
     */
    private void putCustomWordImpl(Word word){

        // Create ContentValues to insert into the
        // database.
        ContentValues cvs;

        // Insert each word object into the ContentValues array.
        cvs = makeCustomWordDataContentValuesWithId(word);


        //  insert the rows into the Words table.
        mContext.get().getContentResolver()
                .insert
                        (WordsContract.CustomWordsEntry.CUSTOM_WORDS_CONTENT_URI,
                                cvs);
    }

    /**
     * Place the Word object into the cache.
     */
    public void putBaseWord(Word word) {
        putBaseWordImpl(word);
    }


    /**
     * Helper method that places a Word object into the
     * database.
     */
    private void putBaseWordImpl(Word word){

        // Create ContentValues to insert into the
        // database.
        ContentValues cvs;

        // Insert each word object into the ContentValues array.
        cvs = makeBaseWordDataContentValues(word);


        //  insert the rows into the Words table.
        mContext.get().getContentResolver()
                .insert
                        (WordsContract.BaseWordsEntry.BASE_WORDS_CONTENT_URI,
                                cvs);
    }

    /**
     * Helper method that places a Word object into the
     * database.
     */
    public int putBaseWordBulk(ArrayList<Word> words){

        // Create ContentValues to insert into the
        // database.
        ContentValues[] cvsArray = new ContentValues[words.size()];

        int i = 0;

        for (Word word : words)
                cvsArray[i++] = makeBaseWordDataContentValues(word);

        //  insert the rows into the Words table.
        return mContext.get().getContentResolver()
                .bulkInsert
                        (WordsContract.BaseWordsEntry.BASE_WORDS_CONTENT_URI,
                                cvsArray);
    }



    /**
     * Attempts to retrieve the Word
     * object with corresponding id.
     */
    public Word getCustomWord(long wordId) {
        // Selection statement that matches the wordId of the row
        // with the given wordId
        final String rowId =
                ""
                + WordsContract.CustomWordsEntry.COLUMN_WORDID_CUSTOM
                + " = '"
                + wordId
                + "'";

        Log.d(TAG, " " + rowId);
        // Attempt to retrieve the word's data from the content
        // provider.
        try (Cursor cursor =
                     mContext.get().getContentResolver()
                         .query(WordsContract.CustomWordsEntry.CUSTOM_WORDS_CONTENT_URI,
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
                return getCustomWordFromCursor(cursor);
            } else
                // Query was empty or returned null.
                Log.d(TAG, "Cursor is null");
                return null;
        }
    }

    /**
     * Attempts to retrieve all words
     */
    public ArrayList<Word> getAllCustomWords() {
        ArrayList<Word> words = new ArrayList<Word>();

        // Attempt to retrieve the location's data from the content
        // provider.
        try (Cursor cursor =
                     mContext.get().getContentResolver()
                             .query(WordsContract.CustomWordsEntry.CUSTOM_WORDS_CONTENT_URI,
                                     null,
                                     null,
                                     null,
                                     null)) {

                // Iterate through all the cursors and get words
                while (cursor.moveToNext()) {
                    // Get word and add to list
                    words.add(getCustomWordFromCursor(cursor));
                }

        }

        return words;

    }





    /**
     * Constructor using a cursor returned by the WordsProvider.
     * This cursor must contain all the data for the object - i.e., it
     * must contain a row for each Word object corresponding to the
     * Word object.
     */
    private Word getCustomWordFromCursor(Cursor cursor) throws IllegalArgumentException {
        String mainWord =
                cursor.getString(cursor.getColumnIndexOrThrow(WordsContract.CustomWordsEntry.COLUMN_MAIN_WORD_CUSTOM));
        String explanation =
                cursor.getString(cursor.getColumnIndexOrThrow(WordsContract.CustomWordsEntry.COLUMN_EXPLANATION_CUSTOM));

        // Return a Word object.
        return new Word(
                        mainWord,
                        explanation);
    }

    /**
     * Attempts to retrieve the Word
     * object with corresponding id.
     */
    public Word getBaseWord(int wordId) {
        // Selection statement that matches the wordId of the row
        // with the given wordId
        final String rowId =
                ""
                        + WordsContract.BaseWordsEntry._ID
                        + " = '"
                        + wordId
                        + "'";
        // Attempt to retrieve the word's data from the content
        // provider.
        try (Cursor cursor =
                     mContext.get().getContentResolver()
                             .query(WordsContract.BaseWordsEntry.BASE_WORDS_CONTENT_URI,
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
                return getBaseWordFromCursor(cursor);
            } else
                // Query was empty or returned null.
                Log.d(TAG, "Cursor is null");
            return null;
        }
    }

    /**
     * Attempts to retrieve all words
     */
    public ArrayList<Word> getAllBaseWords() {
        ArrayList<Word> words = new ArrayList<Word>();

        // Attempt to retrieve the location's data from the content
        // provider.
        try (Cursor cursor =
                     mContext.get().getContentResolver()
                             .query(WordsContract.BaseWordsEntry.BASE_WORDS_CONTENT_URI,
                                     null,
                                     null,
                                     null,
                                     null)) {

            // Iterate through all the cursors and get words
            while (cursor.moveToNext()) {
                // Get word and add to list
                words.add(getBaseWordFromCursor(cursor));
            }

        }

        return words;

    }

    /**
     * Attempts to retrieve all words
     */
    public ArrayList<Word> getAllCustomWords(Cursor cursor) {

        if (cursor != null) {

        ArrayList<Word> words = new ArrayList<Word>();


            // Iterate through all the cursors and get words
            while (cursor.moveToNext()) {
                // Get word and add to list
                words.add(getCustomWordFromCursor(cursor));
            }



        return words; } else {
            return null;
        }

    }

    public void removeCustomWord(Word word) {
        String word_text = word.getMainWord();
        mContext.get().getContentResolver().delete(WordsContract.CustomWordsEntry.CUSTOM_WORDS_CONTENT_URI,
                                                    WordsContract.CustomWordsEntry.COLUMN_MAIN_WORD_CUSTOM + " =?",
                                                    new String[] { word_text });
    }





    /**
     * Constructor using a cursor returned by the WordsProvider.
     * This cursor must contain all the data for the object - i.e., it
     * must contain a row for each Word object corresponding to the
     * Word object.
     */
    private Word getBaseWordFromCursor(Cursor cursor) throws IllegalArgumentException {
        String mainWord =
                cursor.getString(cursor.getColumnIndexOrThrow(WordsContract.BaseWordsEntry.COLUMN_MAIN_WORD_BASE));
        String explanation =
                cursor.getString(cursor.getColumnIndexOrThrow(WordsContract.BaseWordsEntry.COLUMN_EXPLANATION_BASE));

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
    public int sizeCustom() {
        // Query the data for all rows of the Weather Values table.
        try (Cursor cursor =
                     mContext.get().getContentResolver().query
                             (WordsContract.CustomWordsEntry.CUSTOM_WORDS_CONTENT_URI,
                                     new String[] {WordsContract.CustomWordsEntry._ID},
                                     null,
                                     null,
                                     null)) {
            // Return the number of rows in the table, which is equivlent
            // to the number of objects
            return cursor.getCount();
        }
    }


    /**
     * Return the current number of Word objects in Database.
     *
     * @return size
     */
    public int sizeBase() {
        // Query the data for all rows of the Weather Values table.
        try (Cursor cursor =
                     mContext.get().getContentResolver().query
                             (WordsContract.BaseWordsEntry.BASE_WORDS_CONTENT_URI,
                                     new String[] {WordsContract.BaseWordsEntry._ID},
                                     null,
                                     null,
                                     null)) {
            // Return the number of rows in the table, which is equivlent
            // to the number of objects
            Log.d(TAG,"size " + cursor.getCount());
            return cursor.getCount();
        }

    }




}
