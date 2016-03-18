package com.play.eldarbabayev.wordland.view.custom_game;

import android.app.DialogFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.play.eldarbabayev.wordland.common.GenericActivity;
import com.play.eldarbabayev.wordland.R;
import com.play.eldarbabayev.wordland.model.cache.WordCache;
import com.play.eldarbabayev.wordland.model.jsonword.Word;
import com.play.eldarbabayev.wordland.model.provider.WordsContract;
import com.play.eldarbabayev.wordland.presenter.custom_game.CustomDictionaryOps;
import com.play.eldarbabayev.wordland.view.ui.FloatingActionButton;
import com.play.eldarbabayev.wordland.view.ui.WordAdapter;

import java.util.List;


/**
 * BaseDictionaryActivity that is used to display the dictionary
 */
public class CustomDictionaryActivity
        extends GenericActivity<CustomDictionaryActivity,
                CustomDictionaryOps,
                CustomDictionaryOps>
        implements LoaderManager.LoaderCallbacks<Cursor>{

    private DialogFragment mDialog;

    /**
     * Activity Title Bar
     */
    private TextView titleBar;

    /**
     * The Floating Action Button that when pressed will open Add word activity.
     */
    private FloatingActionButton mAddWord;

    /**
     * The ListView that contains a list of Words available from
     * Words Database.
     */
    private ListView mWordsList;

    /**
     * Cursor Adapter used to show the mWordList in a ListView
     */

    private Toolbar toolBar;

    private WordAdapter mAdapter;

    private WordCache mWordCache;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // superclass onCreate method call
        super.onCreate(savedInstanceState);
        // Initialise layout
        setContentView(R.layout.dictionary_activity);

        // initialise the display
        initialiseDisplay();
        // Initialise the cursor loader that updates
        // pthe list of words
        getLoaderManager().initLoader(0,
                                      null,
                                      this);
        // Invoke the special onCreate() method in GenericActivity,
        // passing in the VideoOps class to instantiate/manage and
        // "this" to provide VideoOps with the VideoOps.View instance.
        super.onCreate(CustomDictionaryOps.class,
                this);

        getPresenter().getWordListAndPopulateAdapter();

        mWordCache = new WordCache(this);
    }

    /**
     * Method used to initialise display
     */
    private void initialiseDisplay() {

        toolBar = (Toolbar) findViewById(R.id.ToolbarWelcome);
        setSupportActionBar(toolBar);


        // Inflate a menu to be displayed in the toolbar
        toolBar.inflateMenu(R.menu.dictionary_menu);

        Typeface typeFace= Typeface.createFromAsset(getAssets(), "fonts/Chalkduster.ttf");
        // Get reference to the ListView for displaying the results
        // entered.
        mWordsList =
                (ListView) findViewById(R.id.wordList);
        // Get reference to the Floating Action Button.
        mAddWord =
                (FloatingActionButton) findViewById(R.id.addWordButton);
        // Displays a chooser dialog that gives options to
        // upload video from either by Gallery or by
        // VideoRecorder.
        mAddWord.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goToAddWordActivity = new Intent(CustomDictionaryActivity.this, AddWordActivity.class);
                startActivity(goToAddWordActivity);
            }
        });

       /** // Initialize the Cursor Adapter
        mAdapter = new SimpleCursorAdapter(getApplicationContext(),
                                            R.layout.dictionary_list_item,
                                            null,
                                            new String[] {
                                                    WordsContract.CustomWordsEntry.COLUMN_MAIN_WORD_CUSTOM,
                                                    WordsContract.CustomWordsEntry.COLUMN_EXPLANATION_CUSTOM},
                                            new int[] {
                                                    R.id.Word,
                                                    R.id.Explanation},
                                            1);

        **/

        mAdapter = new WordAdapter(getActivityContext());

        // Set the ListView to the Adapter
        mWordsList.setAdapter(mAdapter);

        // Set Fast Scrolling option
        mWordsList.setFastScrollEnabled(true);





    }

    /**
     * Hook Method called immediately after onStart()
     */
    @Override
    protected void onResume() {
        // Call up to the super class
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
                        WordsContract.CustomWordsEntry.CUSTOM_WORDS_CONTENT_URI,             // Table to query
                        null,                                                  // No projection to return
                        null,                                                 // No selection clause
                        null,                                                // No selection arguments
                        // @@
                        WordsContract.CustomWordsEntry.COLUMN_MAIN_WORD_CUSTOM + " ASC"); // Sort by main word


    }

    /**
     * Called when a previously created loader is being reset, thus
     * making its data unavailable
     */
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    /**
     * Called when a previously created loader has finished its load
     */
    @Override
    public void onLoadFinished(Loader<Cursor> loader,
                               Cursor cursor) {
        mAdapter.swapCursor(cursor);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dictionary_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Show Dialog when More information item is selected
        if (id == R.id.action_delete) {
            List<Word> words = mAdapter.getWords();
            for (Word word : words) {
                if (word.isSelected()) {
                    mWordCache.removeCustomWord(word);
                }
            }
        }

        return super.onOptionsItemSelected(item);
    }



}