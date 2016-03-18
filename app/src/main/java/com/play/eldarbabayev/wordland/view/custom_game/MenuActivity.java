package com.play.eldarbabayev.wordland.view.custom_game;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.play.eldarbabayev.wordland.common.GenericActivity;
import com.play.eldarbabayev.wordland.R;
import com.play.eldarbabayev.wordland.presenter.custom_game.MenuActivityOps;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseMenuActivity that is the main activity two display two game modes
 */
public class MenuActivity
        extends GenericActivity<MenuActivity,
                MenuActivityOps,
                MenuActivityOps> {

    /**
     * Background image view
     */
    private ImageView imageView;

    /**
     * Activity Title Bar
     */
    private TextView titleBar;

    /**
     * Play button
     */
    private Button play_button;

    /**
     * Dictionary Button
     */
    private Button dictionary_button;

    private Toolbar toolBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // superclass onCreate method call
        super.onCreate(savedInstanceState);
        // Initialise layout
        setContentView(R.layout.menu_layout);

        // Initialise display
        initialiseDisplay();
        // Invoke the special onCreate() method in GenericActivity,
        // passing in the VideoOps class to instantiate/manage and
        // "this" to provide VideoOps with the VideoOps.View instance.
        super.onCreate(MenuActivityOps.class,
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
        Drawable dr = getResources().getDrawable(R.drawable.mountainsorange);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, parametersList.get(1), parametersList.get(0) - getStatusBarHeight(), true));
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageDrawable(d);

        // Set the Title text style and Logo
        Typeface typeFace= Typeface.createFromAsset(getAssets(), "fonts/Chalkduster.ttf");

        // Initialise the play button
        play_button = (Button) findViewById(R.id.play_button);
        play_button.setTypeface(typeFace);

        // Initialise the dictionary button
        dictionary_button = (Button) findViewById(R.id.dictionary_button);
        dictionary_button.setTypeface(typeFace);
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
     * Start game
     *
     * @param v
     */
    public void playButton(View v) {
        // go straight to the playground
        Intent goToPlayActivity = new Intent(MenuActivity.this, PlayActivity.class);
        startActivity(goToPlayActivity);
    }

    /**
     *
     * Go to dictionary
     *
     * @param v
     */
    public void dictionaryButton(View v) {
        // go to dictionary
        Intent goToDictionaryActivity = new Intent(MenuActivity.this, CustomDictionaryActivity.class);
        startActivity(goToDictionaryActivity);
    }
}
