package com.play.eldarbabayev.wordland.view.base_game;


import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.play.eldarbabayev.wordland.common.GenericActivity;
import com.play.eldarbabayev.wordland.presenter.base_game.BaseDictionaryOps;
import com.play.eldarbabayev.wordland.R;
import com.play.eldarbabayev.wordland.model.provider.WordsContract;
import com.play.eldarbabayev.wordland.model.provider.WordsDatabaseHelper;

/**
 * BaseDictionaryActivity that is used to display the dictionary
 */
public class BaseDictionaryActivity
        extends GenericActivity<BaseDictionaryActivity,
                BaseDictionaryOps,
                BaseDictionaryOps>
        implements LoaderManager.LoaderCallbacks<Cursor>{

    /**
     * Activity Title Bar
     */
    private TextView titleBar;


    /**
     * The ListView that contains a list of Words available from
     * Words Database.
     */
    private ListView mWordsList;

    /**
     * Cursor Adapter used to show the mWordList in a ListView
     */
    private SimpleCursorAdapter mAdapter;

    /**
     *
     * @param savedInstanceState
     */
    private WordsDatabaseHelper mOpenHelper;

    private Toolbar toolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // superclass onCreate method call
        super.onCreate(savedInstanceState);
        // Initialise layout
        setContentView(R.layout.base_dictionary_activity);

        // initialise the display
        initialiseDisplay();



        // Initialise the cursor loader that updates
        // the list of words
        getLoaderManager().initLoader(0,
                                      null,
                                      this);
        // Invoke the special onCreate() method in GenericActivity,
        // passing in the VideoOps class to instantiate/manage and
        // "this" to provide VideoOps with the VideoOps.View instance.
        super.onCreate(BaseDictionaryOps.class,
                this);


    }

    /**
     * Method used to initialise display
     */
    private void initialiseDisplay() {
        toolBar = (Toolbar) findViewById(R.id.ToolbarWelcome);
        setSupportActionBar(toolBar);
        Typeface typeFace= Typeface.createFromAsset(getAssets(), "fonts/Chalkduster.ttf");
        // Get reference to the ListView for displaying the results
        // entered.
        mWordsList =
                (ListView) findViewById(R.id.wordList);
        // Initialize the Cursor Adapter
        mAdapter = new SimpleCursorAdapter(getApplicationContext(),
                                            R.layout.base_dictionary_list_item,
                                            null,
                                            new String[] {
                                                    WordsContract.BaseWordsEntry.COLUMN_MAIN_WORD_BASE,
                                                    WordsContract.BaseWordsEntry.COLUMN_EXPLANATION_BASE},
                                            new int[] {
                                                    R.id.Word,
                                                    R.id.Explanation},
                                            1);

        // Set the ListView to the Adapter
        mWordsList.setAdapter(mAdapter);

        // Set Fast Scrolling option
        mWordsList.setFastScrollEnabled(true);

        mWordsList.setFastScrollAlwaysVisible(true);


    }

    /**
     * Hook Method called immediately after onStart()
     */
    @Override
    protected void onResume() {
        // Call up to the super class
        Log.d(TAG, "I am here 1");
        super.onResume();
        // Restart the loader
        getLoaderManager().restartLoader(0,
                null,
                this);
    }

    /**
     * Called by Loader Manager to instantiate and return a new Loader
     * that loads the video data from our Video ContentProvider at
     * the given COntentUri
     */
    @Override
    public Loader<Cursor> onCreateLoader(int id,
                                         Bundle args) {
        // @@
        return new CursorLoader
                       (this,                                                    // Application context
                        WordsContract.BaseWordsEntry.BASE_WORDS_CONTENT_URI,             // Table to query
                        null,                                                  // No projection to return
                        null,                                                 // No selection clause
                        null,                                                // No selection arguments
                        // @@
                        WordsContract.BaseWordsEntry.COLUMN_MAIN_WORD_BASE + " ASC"); // Sort by main word


    }

    /**
     * Called when a previously created loader is being reset, thus
     * making its data unavailable
     */
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        Log.d(TAG, "I am here 3");
        mAdapter.swapCursor(null);
    }

    /**
     * Called when a previously created loader has finished its load
     */
    @Override
    public void onLoadFinished(Loader<Cursor> loader,
                               Cursor cursor) {
        Log.d(TAG,"I am here 4");

        mAdapter.swapCursor(cursor);

    }




}