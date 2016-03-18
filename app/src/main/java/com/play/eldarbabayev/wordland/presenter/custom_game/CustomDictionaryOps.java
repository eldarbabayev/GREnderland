package com.play.eldarbabayev.wordland.presenter.custom_game;


import java.lang.ref.WeakReference;
import java.util.List;

import com.play.eldarbabayev.wordland.common.GenericAsyncTask;
import com.play.eldarbabayev.wordland.common.GenericAsyncTaskOps;
import com.play.eldarbabayev.wordland.common.PresenterOps;
import com.play.eldarbabayev.wordland.model.cache.WordCache;
import com.play.eldarbabayev.wordland.model.jsonword.Word;
import com.play.eldarbabayev.wordland.model.provider.WordsDatabaseHelper;
import com.play.eldarbabayev.wordland.view.custom_game.CustomDictionaryActivity;
import com.play.eldarbabayev.wordland.view.ui.WordAdapter;

/**
 * It plays role of the "Presenter" in the
 * Model-View-Presenter pattern and can communicate with the
 * BaseDictionaryActivity in the "View" layer.
 */
public class CustomDictionaryOps
        implements GenericAsyncTaskOps<Void, Void, List<Word>>,
        PresenterOps<CustomDictionaryActivity> {

    /**
     * Logging tag.
     */
    private final static String TAG =
            CustomDictionaryOps.class.getCanonicalName();

    /**
     * Used to enable garbage collection.
     */
    private WeakReference<CustomDictionaryActivity> mView;

    /**
     * The GenericAsyncTask
     */
    private GenericAsyncTask<Void,
                Void,
                List<Word>,
                CustomDictionaryOps> mAsyncTask;


    /**
     * The Word Cache
     */
    private WordCache mCache;

    /**
     * The Database Open Helper
     */
    private WordsDatabaseHelper mOpenHelper;

    /**
     * The Word Adapter
     */
    private WordAdapter mAdapter;





    /**
     * Default constructor is needed by the GenericActivity framework.
     */
    public CustomDictionaryOps() {

    }

    /**
     * Hook method called when a new instance of BaseDictionaryOps is
     * created.  One time initialization code goes here, e.g., storing
     * a WeakReference to the View layer and initializing the Model
     * layer.
     *
     * @param view
     *            A reference to the View layer.
     */
    @Override
    public void onCreate(CustomDictionaryActivity view) {
        // Set the WeakReference.
        mView = new WeakReference<>(view);
        // Create a local instance word cache
        mCache =
                new WordCache(mView.get().getActivityContext());
        // Create a local instance words open helper
        mOpenHelper =
                new WordsDatabaseHelper(mView.get().getApplicationContext());

        mAdapter = new WordAdapter(mView.get().getActivityContext());

    }

    /**
     * Hook method dispatched by the GenericActivity framework to
     * initialize the BaseDictionaryOps object after a runtime
     * configuration change.
     *
     * @param view         The currently active View.
     */
    @Override
    public void onConfigurationChange(CustomDictionaryActivity view) {
        // Reset the WeakReference.
        mView = new WeakReference<>(view);
        // Reset the Cache
        mCache = new WordCache(mView.get().getActivityContext());
        // Reset a local instance words open helper
        mOpenHelper =
                new WordsDatabaseHelper(mView.get().getApplicationContext());
        mAdapter = new WordAdapter(mView.get().getActivityContext());
    }

    /**
     * Hook method called to shutdown the Presenter layer.
     *
     * @param isChangeConfigurations
     *        True if a runtime configuration triggered the onDestroy() call.
     */
    @Override
    public void onDestroy(boolean isChangingConfigurations) {
        // No op.
    }


    /**
     * Gets the WordList from database by executing the AsyncTask to
     * get the words without blocking the caller.
     */
    public void getWordListAndPopulateAdapter(){
        mAsyncTask = new GenericAsyncTask<>(this);
        mAsyncTask.execute();
    }

    /**
     * Retrieve the List of Words
     */
    @Override
    public List<Word> doInBackground(Void... params) {

        return mCache.getAllCustomWords();

    }

    /**
     * Display the results in the UI Thread.
     */
    @Override
    public void onPostExecute(List<Word> words) {

        if (words != null) {
            mAdapter.setWords(words);
        }

    }
    public void removeWord(Word word) {
        mCache.removeCustomWord(word);
    }
    }




