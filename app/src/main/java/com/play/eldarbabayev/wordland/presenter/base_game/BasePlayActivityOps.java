package com.play.eldarbabayev.wordland.presenter.base_game;

import com.play.eldarbabayev.wordland.common.GenericAsyncTaskOps;
import com.play.eldarbabayev.wordland.common.PresenterOps;
import com.play.eldarbabayev.wordland.model.cache.WordCache;
import com.play.eldarbabayev.wordland.common.GenericAsyncTask;
import com.play.eldarbabayev.wordland.view.base_game.BasePlayActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * It plays role of the "Presenter" in the
 * Model-View-Presenter pattern and can communicate with the
 * AddWordActivity in the "View" layer.
 */
public class BasePlayActivityOps
        implements GenericAsyncTaskOps<ArrayList<Iterator>, Void, Integer>,
        PresenterOps<BasePlayActivity> {

    /**
     * Logging tag.
     */
    private final static String TAG =
            BasePlayActivityOps.class.getCanonicalName();

    /**
     * Used to enable garbage collection.
     */
    private WeakReference<BasePlayActivity> mView;

    /**
     * The GenericAsyncTask
     */
    private GenericAsyncTask<ArrayList<Iterator>,
            Void,
            Integer,
            BasePlayActivityOps> mAsyncTask;

    private      ArrayList<String> words;
    private       ArrayList<String> explanations;

    /**
     * The Word Cache
     */
    private WordCache mCache;




    /**
     * Default constructor is needed by the GenericActivity framework.
     */
    public BasePlayActivityOps() {
    }

    /**
     * Hook method called when a new instance of BasePlayActivityOps is
     * created.  One time initialization code goes here, e.g., storing
     * a WeakReference to the View layer and initializing the Model
     * layer.
     *
     * @param view
     *            A reference to the View layer.
     */
    @Override
    public void onCreate(BasePlayActivity view) {
        // Set the WeakReference.
        mView = new WeakReference<>(view);
        // Create a local instance word cache
        mCache =
                new WordCache(mView.get().getApplicationContext());


    }

    /**
     * Hook method dispatched by the GenericActivity framework to
     * initialize the BasePlayActivityOps object after a runtime
     * configuration change.
     *
     * @param view         The currently active View.
     */
    @Override
    public void onConfigurationChange(BasePlayActivity view) {
        // Reset the WeakReference.
        mView = new WeakReference<>(view);
        // Create a local instance word cache
        mCache =
                new WordCache(mView.get().getApplicationContext());

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
     * Perform smth in background thread.
     */
    public Integer doInBackground(ArrayList<Iterator>... iterators) {
        return 0;
    }

    /**
     * Send the result of background calculations to the
     * AddWordActivity in the UI thread.
     */
    public void onPostExecute(Integer integer) {
        // No op.
    }
}





