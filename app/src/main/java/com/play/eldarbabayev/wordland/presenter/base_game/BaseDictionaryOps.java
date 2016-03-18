package com.play.eldarbabayev.wordland.presenter.base_game;


import com.play.eldarbabayev.wordland.common.GenericAsyncTask;
import com.play.eldarbabayev.wordland.common.GenericAsyncTaskOps;
import com.play.eldarbabayev.wordland.common.PresenterOps;
import com.play.eldarbabayev.wordland.model.cache.WordCache;
import com.play.eldarbabayev.wordland.model.jsonword.Word;
import com.play.eldarbabayev.wordland.view.base_game.BaseDictionaryActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * It plays role of the "Presenter" in the
 * Model-View-Presenter pattern and can communicate with the
 * BaseDictionaryActivity in the "View" layer.
 */
public class BaseDictionaryOps
        implements GenericAsyncTaskOps<ArrayList<Iterator>, Void, Integer>,
        PresenterOps<BaseDictionaryActivity> {

    /**
     * Logging tag.
     */
    private final static String TAG =
            BaseDictionaryOps.class.getCanonicalName();

    /**
     * Used to enable garbage collection.
     */
    private WeakReference<BaseDictionaryActivity> mView;

    /**
     * The GenericAsyncTask
     */
    private GenericAsyncTask<ArrayList<Iterator>,
            Void,
            Integer,
            BaseDictionaryOps> mAsyncTask;

        private      ArrayList<String> words;
        private       ArrayList<String> explanations;


    /**
     * The Word Cache
     */
    private WordCache mCache;


    /**
     * Default constructor is needed by the GenericActivity framework.
     */
    public BaseDictionaryOps() {

    }

    /**
     * Hook method called when a new instance of BaseDictionaryOps is
     * created.  One time initialization code goes here, e.g., storing
     * a WeakReference to the View layer and initializing the Model
     * layer.
     *
     * @param view A reference to the View layer.
     */
    @Override
    public void onCreate(BaseDictionaryActivity view) {
        // Set the WeakReference.
        mView = new WeakReference<>(view);
        // Create a local instance word cache
        mCache =
                new WordCache(mView.get().getApplicationContext());


    }

    /**
     * Hook method dispatched by the GenericActivity framework to
     * initialize the BaseDictionaryOps object after a runtime
     * configuration change.
     *
     * @param view The currently active View.
     */
    @Override
    public void onConfigurationChange(BaseDictionaryActivity view) {
        // Reset the WeakReference.
        mView = new WeakReference<>(view);
        // Reset the Cache
        mCache = new WordCache(mView.get().getApplicationContext());

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
     * Retrieve the List of Words
     */
    @Override
    public Integer doInBackground(ArrayList<Iterator>... iterators) {

        return 0;
    }

    /**
     * Display the results in the UI Thread.
     */
    @Override
    public void onPostExecute(Integer integers) {

    }

    /**
     * Display the Words in ListView.
     *
     * @param words
     */
    public void displayWordList(List<Word> words) {


        }
    }




