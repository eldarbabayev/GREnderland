package com.play.eldarbabayev.wordland.view.shared_activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.play.eldarbabayev.wordland.common.GenericActivity;
import com.play.eldarbabayev.wordland.presenter.shared_ops.WelcomeActivityOps;
import com.play.eldarbabayev.wordland.view.base_game.BaseMenuActivity;
import com.play.eldarbabayev.wordland.view.custom_game.MenuActivity;
import com.play.eldarbabayev.wordland.R;
import com.play.eldarbabayev.wordland.model.cache.WordCache;
import com.play.eldarbabayev.wordland.view.custom_game.CustomDictionaryActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * WelcomeActivity that is the main activity two display two game modes
 */
public class WelcomeActivity
        extends GenericActivity<WelcomeActivity,
                WelcomeActivityOps,
                WelcomeActivityOps> {

    /**
     * Background image view
     */
    private ImageView imageView;

    /**
     * The tool bar
     */
    private Toolbar toolBar;

    /**
     * Custom player button
     */
    private Button custom_button;

    /**
     * Base player Button
     */
    private Button base_button;

    /**
     * Word Cache
     */
    private WordCache mCache;

    /**
     * Word Cache
     */
    private int actionBarHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // superclass onCreate method call
        super.onCreate(savedInstanceState);
        // Initialise layout
        setContentView(R.layout.welcome_activity);

        // Initialise display
        initialiseDisplay();
        // Initialise the word cache
        mCache = new WordCache(this);
        // Invoke the special onCreate() method in GenericActivity,
        // passing in the VideoOps class to instantiate/manage and
        // "this" to provide VideoOps with the VideoOps.View instance.
        super.onCreate(WelcomeActivityOps.class,
                this);

    }

    /**
     * Method used to initialise display
     */
    private void initialiseDisplay() {
        toolBar = (Toolbar) findViewById(R.id.ToolbarWelcome);
        setSupportActionBar(toolBar);

        // Get display parameters
        List<Integer> parametersList = getDisplayParameters();

        // Set the background wallpaper
        Drawable dr = getResources().getDrawable(R.drawable.mountains);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, parametersList.get(1), parametersList.get(0) - getStatusBarHeight(), true));
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageDrawable(d);


        Typeface typeFace= Typeface.createFromAsset(getAssets(), "fonts/Chalkduster.ttf");

        // Initialise the custom player button
        custom_button = (Button) findViewById(R.id.custom);
        custom_button.setTypeface(typeFace);

        // Initialise the base player button
        base_button = (Button) findViewById(R.id.base);
        base_button.setTypeface(typeFace);
    }


    /**
     * Method that returns height and width of Android device's display
     */
    private List<Integer> getDisplayParameters() {

        List<Integer> list = new ArrayList<>();

        int display_height;
        int display_width;

        Point size = new Point();

        WindowManager w = getWindowManager();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            w.getDefaultDisplay().getSize(size);
            display_width = size.x;
            display_height = size.y;

        } else {

            Display d = w.getDefaultDisplay();
            display_width = d.getWidth();
            display_height = d.getHeight();

        }

        list.add(0,display_height);
        list.add(1,display_width);

        return list;

    }

    /**
     * Method that returns the height of status bar
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    /**
     *
     * Start custom player mode
     *
     * @param v
     */
    public void customButton(View v) {
        // Check if the player already has at least 10 words to play with
        if (mCache.sizeCustom() < 10) {
            // If not go to Dictionary activity
            Intent goToDictionaryActivity = new Intent(WelcomeActivity.this, CustomDictionaryActivity.class);
            startActivity(goToDictionaryActivity);
        } else {
            // If yes then go straight to the playground
            Intent goToMenuActivity = new Intent(WelcomeActivity.this, MenuActivity.class);
            startActivity(goToMenuActivity);
        }
    }

    /**
     *
     * Start base player mode
     *
     * @param v
     */
    public void baseButton(View v) {

        Intent goToBaseMenuActivity = new Intent(WelcomeActivity.this, BaseMenuActivity.class);

        startActivity(goToBaseMenuActivity);
    }




}


