package com.play.eldarbabayev.wordland.presenter.custom_game;

import java.lang.ref.WeakReference;

import com.play.eldarbabayev.wordland.common.GenericAsyncTask;
import com.play.eldarbabayev.wordland.common.GenericAsyncTaskOps;
import com.play.eldarbabayev.wordland.common.PresenterOps;
import com.play.eldarbabayev.wordland.view.custom_game.PlayActivity;

/**
 * It plays role of the "Presenter" in the
 * Model-View-Presenter pattern and can communicate with the
 * AddWordActivity in the "View" layer.
 */
public class PlayActivityOps
        implements GenericAsyncTaskOps<Integer, Void, Integer>,
        PresenterOps<PlayActivity> {

    /**
     * Logging tag.
     */
    private final static String TAG =
            PlayActivityOps.class.getCanonicalName();

    /**
     * Used to enable garbage collection.
     */
    private WeakReference<PlayActivity> mView;

    /**
     * The GenericAsyncTask
     */
    private GenericAsyncTask<Integer,
                Void,
                Integer,
                PlayActivityOps> mAsyncTask;


    /**
     * Default constructor is needed by the GenericActivity framework.
     */
    public PlayActivityOps() {
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
    public void onCreate(PlayActivity view) {
        // Set the WeakReference.
        mView = new WeakReference<>(view);

    }

    /**
     * Hook method dispatched by the GenericActivity framework to
     * initialize the BasePlayActivityOps object after a runtime
     * configuration change.
     *
     * @param view         The currently active View.
     */
    @Override
    public void onConfigurationChange(PlayActivity view) {
        // Reset the WeakReference.
        mView = new WeakReference<>(view);
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
    public Integer doInBackground(Integer... integers) {
        // No op.
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





