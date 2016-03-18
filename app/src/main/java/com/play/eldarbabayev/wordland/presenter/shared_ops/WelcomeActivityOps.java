package com.play.eldarbabayev.wordland.presenter.shared_ops;


import java.lang.ref.WeakReference;


import com.play.eldarbabayev.wordland.common.ContextView;
import com.play.eldarbabayev.wordland.common.GenericAsyncTask;
import com.play.eldarbabayev.wordland.common.GenericAsyncTaskOps;
import com.play.eldarbabayev.wordland.common.PresenterOps;
import com.play.eldarbabayev.wordland.model.cache.WordCache;
import com.play.eldarbabayev.wordland.model.provider.WordsDatabaseHelper;
import com.play.eldarbabayev.wordland.view.shared_activities.WelcomeActivity;

/**
 * It plays role of the "Presenter" in the
 * Model-View-Presenter pattern and can communicate with the
 * WelcomeActivity in the "View" layer.
 */
public class WelcomeActivityOps
        implements GenericAsyncTaskOps<Integer, Void, Integer>,
        PresenterOps<WelcomeActivity> {

    /**
     * Debugging tag used by the Android logger.
     */
    private static final String TAG =
            WelcomeActivityOps.class.getSimpleName();

    /**
     * This interface defines the minimum interface needed by the
     * WelcomeActivityOps class in the "Presenter" layer to interact with the
     * BasePlayActivity in the "View" layer.
     */
    public interface View extends ContextView {
    }

    /**
     * Used to enable garbage collection.
     */
    private WeakReference<WelcomeActivity> mView;

    /**
     * Count for Words in database.
     */
    private long mCount;

    /**
     * Words Cache.
     */
    private WordCache mWordCache;

    /**
     * Words Database Helper.
     */
    private WordsDatabaseHelper mOpenHelper;


    /**
     * The GenericAsyncTask
     */
    private GenericAsyncTask<Integer,
            Void,
            Integer,
            WelcomeActivityOps> mAsyncTask;




    /**
     * Default constructor that's needed by the GenericActivity
     * framework.
     */
    public WelcomeActivityOps() {

    }

    /**
     * Hook method called when a new instance of WelcomeActivityOps is
     * created.  One time initialization code goes here, e.g., storing
     * a WeakReference to the View layer and initializing the Model
     * layer.
     *
     * @param view
     *            A reference to the View layer.
     */
    @Override
    public void onCreate(WelcomeActivity view) {
        // Set the WeakReference.
        mView = new WeakReference<>(view);
        // Set the Word Cache.
        mWordCache = new WordCache(view.getApplicationContext());
        // Set the Database Open Helper.
        mOpenHelper = new WordsDatabaseHelper(view.getApplicationContext());
    }



    /**
     * Hook method dispatched by the GenericActivity framework to
     * initialize the AddWordOps object after a runtime
     * configuration change.
     *
     * @param view         The currently active View.
     */
    @Override
    public void onConfigurationChange(WelcomeActivity view) {
        // Reset the WeakReference.
        mView = new WeakReference<>(view);
        // Reset the Word Cache.
        mWordCache = new WordCache(view.getApplicationContext());
        // Reset the Words Database Helper.
        mOpenHelper = new WordsDatabaseHelper(view.getApplicationContext());
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






