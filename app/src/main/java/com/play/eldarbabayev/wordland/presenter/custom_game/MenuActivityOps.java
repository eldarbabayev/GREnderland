package com.play.eldarbabayev.wordland.presenter.custom_game;


import java.lang.ref.WeakReference;

import com.play.eldarbabayev.wordland.common.GenericAsyncTaskOps;
import com.play.eldarbabayev.wordland.common.PresenterOps;
import com.play.eldarbabayev.wordland.view.custom_game.MenuActivity;
import com.play.eldarbabayev.wordland.common.GenericAsyncTask;

/**
 * It plays role of the "Presenter" in the
 * Model-View-Presenter pattern and can communicate with the
 * BaseMenuActivity in the "View" layer.
 */
public class MenuActivityOps
        implements GenericAsyncTaskOps<Integer, Void, Integer>,
        PresenterOps<MenuActivity> {

    /**
     * Logging tag.
     */
    private final static String TAG =
            MenuActivityOps.class.getCanonicalName();

    /**
     * Used to enable garbage collection.
     */
    private WeakReference<MenuActivity> mView;

    /**
     * The GenericAsyncTask
     */
    private GenericAsyncTask<Integer,
            Void,
            Integer,
            MenuActivityOps> mAsyncTask;


    /**
     * Default constructor is needed by the GenericActivity framework.
     */
    public MenuActivityOps() {

    }

    /**
     * Hook method called when a new instance of BaseMenuActivityOps is
     * created.  One time initialization code goes here, e.g., storing
     * a WeakReference to the View layer and initializing the Model
     * layer.
     *
     * @param view
     *            A reference to the View layer.
     */
    @Override
    public void onCreate(MenuActivity view) {
        // Set the WeakReference.
        mView = new WeakReference<>(view);

    }

    /**
     * Hook method dispatched by the GenericActivity framework to
     * initialize the BaseMenuActivityOps object after a runtime
     * configuration change.
     *
     * @param view         The currently active View.
     */
    @Override
    public void onConfigurationChange(MenuActivity view) {
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
     * BaseMenuActivity in the UI thread.
     */
    public void onPostExecute(Integer integer) {
        // No op.
    }
}
