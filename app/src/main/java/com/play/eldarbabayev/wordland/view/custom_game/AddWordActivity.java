package com.play.eldarbabayev.wordland.view.custom_game;


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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.play.eldarbabayev.wordland.common.GenericActivity;
import com.play.eldarbabayev.wordland.model.cache.WordCache;
import com.play.eldarbabayev.wordland.presenter.custom_game.AddWordOps;
import com.play.eldarbabayev.wordland.R;
import com.play.eldarbabayev.wordland.model.jsonword.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * AddWordActivity that is used to add Words to the dictionary
 */
public class AddWordActivity
        extends GenericActivity<AddWordActivity,
                                        AddWordOps,
                                        AddWordOps> {


    /**
     * Background image view
     */
    private ImageView imageView;

    /**
     * Activity Title Bar
     */
    private TextView titleBar;

    /**
     * Main word edit text
     */
    private EditText mainText;
    
    /**
     * Explanation edit text
     */
    private EditText explanationText;

    /**
     * Add word button
     */
    private Button addWordButton;

    /**
     * Word Cache used to access database
     */
    private WordCache mCache;
    private Toolbar toolBar;

    private long count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // superclass onCreate method call
        super.onCreate(savedInstanceState);
        // Initialise layout
        setContentView(R.layout.add_word_activity);

        // Initialise display
        initialiseDisplay();

        // Initialise Word Cache
        mCache = new WordCache(this);

        count = mCache.sizeCustom() + 1;

        // Invoke the special onCreate() method in GenericActivity,
        // passing in the VideoOps class to instantiate/manage and
        // "this" to provide VideoOps with the VideoOps.View instance.
        super.onCreate(AddWordOps.class,
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
        Drawable dr = getResources().getDrawable(R.drawable.bluesky);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, parametersList.get(1), parametersList.get(0) - getStatusBarHeight(), true));
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageDrawable(d);

        // Set the Title text style and Logo
        Typeface typeFace= Typeface.createFromAsset(getAssets(), "fonts/Chalkduster.ttf");

        // Initialise add word button
        addWordButton = (Button) findViewById(R.id.button);
        addWordButton.setTypeface(typeFace);

        // Initialise main word edit text
        mainText = (EditText) findViewById(R.id.text1);
        mainText.setTypeface(typeFace);

        // Initialise explanation edit text
        explanationText = (EditText) findViewById(R.id.text2);
        explanationText.setTypeface(typeFace);

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

        int height = 0;

        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = getResources().getDimensionPixelSize(resourceId);
        }
        return height;

    }

    /**
     * Method returned when add new word button clicked
     */
    public void addNewWordButton(View v) {

        // get the location in database after the last element
        int size = mCache.sizeCustom();

        // Create a new word from user input
        Word word = new Word();
        if (!mainText.getText().toString().isEmpty() && !explanationText.getText().toString().isEmpty() ) {
            word.setMainWord(mainText.getText().toString());
            word.setExplanation(explanationText.getText().toString());
            word.setWordId(count);
            count = count + 1;
            // Put this word at the right location

            mCache.putCustomWord(word);
        }
        // Reset the edit texts
        mainText.setText(null);
        explanationText.setText(null);

    }


}

