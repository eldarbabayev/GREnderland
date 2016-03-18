package com.play.eldarbabayev.wordland.view.custom_game;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.play.eldarbabayev.wordland.common.GenericActivity;
import com.play.eldarbabayev.wordland.model.cache.WordCache;
import com.play.eldarbabayev.wordland.model.jsonword.Word;
import com.play.eldarbabayev.wordland.R;
import com.play.eldarbabayev.wordland.presenter.custom_game.PlayActivityOps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * BasePlayActivity that is used to play the game with existing dictionary
 */
public class PlayActivity
        extends GenericActivity<PlayActivity,
                PlayActivityOps,
                PlayActivityOps> {

    /**
     * Activity Title Bar
     */
    private TextView titleBar;

    /**
     * Word Cache
     */
    private WordCache mWordCache;

    /**
     * Random word from database that will serve the main word
     */
    private String mMainWord;

    /**
     * The explanation of that random word
     */
    private String mExplanation;

    /**
     * Text View displaying main word
     */
    private TextView mainTextView;

    /**
     * First Choice button
     */
    private Button button1;

    /**
     * Second Choice button
     */
    private Button button2;

    /**
     * Third Choice button
     */
    private Button button3;

    /**
     * Fourth Choice button
     */
    private Button button4;

    /**
     * Fifth Choice button
     */
    private Button button5;

    /**
     * Sixth Choice button
     */
    private Button button6;

    /**
     * Main word database location
     */
    private int mainWordInt;

    /**
     * Second word database location
     */
    private int secondChoiceInt;

    /**
     * Third word database location
     */
    private int thirdChoiceInt;

    /**
     * Fourth word database location
     */
    private int fourthChoiceInt;

    /**
     * Fifth word database location
     */
    private int fifthChoiceInt;

    /**
     * Sixth word database location
     */
    private int sixthChoiceInt;

    /**
     * Random integer generator used to get 6 random words from database
     */
    private Random randomGenerator;

    private Toolbar toolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // superclass onCreate method call
        super.onCreate(savedInstanceState);
        // Initialise layout
        setContentView(R.layout.play_activity);
        // Initialise display
        initialiseDisplay();
        // Get reference to Word Cache
        mWordCache = new WordCache(this);
        // Get the random id in the range of size of current database
        randomGenerator = new Random();
        // Invoke the special onCreate() method in GenericActivity,
        // passing in the VideoOps class to instantiate/manage and
        // "this" to provide VideoOps with the VideoOps.View instance.
        super.onCreate(PlayActivityOps.class,
                this);
        // Start the game
        startGame();

    }


    /**
     * Method used to initialise display
     */
    private void initialiseDisplay() {

        toolBar = (Toolbar) findViewById(R.id.ToolbarWelcome);
        setSupportActionBar(toolBar);

        Typeface typeFace= Typeface.createFromAsset(getAssets(), "fonts/Chalkduster.ttf");

        // Set the main word style
        Typeface wordsTypeFace= Typeface.createFromAsset(getAssets(), "fonts/Bradley Hand Bold.ttf");
        mainTextView = (TextView) findViewById(R.id.MainWord);
        mainTextView.setTypeface(wordsTypeFace);

        // Set the first button style
        button1 = (Button) findViewById(R.id.FirstChoice);
        button1.setTypeface(typeFace);

        // Set the second button style
        button2 = (Button) findViewById(R.id.SecondChoice);
        button2.setTypeface(typeFace);

        // Set the third button style
        button3 = (Button) findViewById(R.id.ThirdChoice);
        button3.setTypeface(typeFace);

        // Set the fourth button style
        button4 = (Button) findViewById(R.id.FourthChoice);
        button4.setTypeface(typeFace);

        // Set the fifth button style
        button5 = (Button) findViewById(R.id.FifthChoice);
        button5.setTypeface(typeFace);

        // Set the sixth button style
        button6 = (Button) findViewById(R.id.SixthChoice);
        button6.setTypeface(typeFace);

    }

    /**
     * Method that will give the six random numbers
     * between 0 and size of database
     */
    public void getSixRandomNumbers() {
        // Create a new list that contains 6 numbers
        ArrayList<Integer> list = new ArrayList<Integer>();
        // Add integers from 0 to size - 1
        int length = mWordCache.sizeCustom();
        for (int i=1; i<=length; i++) {
            list.add(i);
        }
        // Shuffle these integers
        Collections.shuffle(list);
        // Initialise the 6 random integer choices by picking first
        // 6 numbers from the shuffled list
        mainWordInt = list.get(0);
        secondChoiceInt = list.get(1);
        thirdChoiceInt = list.get(2);
        fourthChoiceInt = list.get(3);
        fifthChoiceInt = list.get(4);
        sixthChoiceInt = list.get(5);
    }

    /**
     * Start playing the 6-choice game
     */
    public void startGame() {
        // Enable all buttons
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);
        button6.setEnabled(true);
        // Get 6 random numbers
        getSixRandomNumbers();

        Log.d(TAG, "Main word" + mWordCache.getAllCustomWords());
        // Find the random word from database along with it's Explanation
        Log.d(TAG, "Main word" + mainWordInt);

        Word word_main = mWordCache.getCustomWord(mainWordInt);
        Log.d(TAG, "Main word" + word_main);
        // Find other words
        Log.d(TAG, "Main word" + secondChoiceInt);

        Word word2 = mWordCache.getCustomWord(secondChoiceInt);
        Log.d(TAG, "word2" + word2);
        Log.d(TAG, "Main word" + thirdChoiceInt);

        Word word3 = mWordCache.getCustomWord(thirdChoiceInt);
        Log.d(TAG, "word2" + word3);
        Log.d(TAG, "Main word" + fourthChoiceInt);

        Word word4 = mWordCache.getCustomWord(fourthChoiceInt);
        Log.d(TAG, "word2" + word4);
        Log.d(TAG, "Main word" + fifthChoiceInt);

        Word word5 = mWordCache.getCustomWord(fifthChoiceInt);
        Log.d(TAG, "word2" + word5);
        Log.d(TAG, "Main word" + sixthChoiceInt);

        Word word6 = mWordCache.getCustomWord(sixthChoiceInt);
        Log.d(TAG, "word2" + word6);

        // Get word of the main word
        mMainWord = word_main.getMainWord();
        // Get explanation of the main word
        mExplanation = word_main.getExplanation();
        // Get all other explanations
        String Explanation2 = word2.getExplanation();
        String Explanation3 = word3.getExplanation();
        String Explanation4 = word4.getExplanation();
        String Explanation5 = word5.getExplanation();
        String Explanation6 = word6.getExplanation();
        // The list that will hold explanations
        List<String> list = new ArrayList<String>();
        // Populate this list
        list.add(mExplanation);
        list.add(Explanation2);
        list.add(Explanation3);
        list.add(Explanation4);
        list.add(Explanation5);
        list.add(Explanation6);
        // Get random number to get random button
        int int1 = randomGenerator.nextInt(list.size());
        // Set text for random button
        button1.setText(list.get(int1));
        // remove chosen number from list
        list.remove(int1);
        // repeat for the rest 5 buttons
        int int2 = randomGenerator.nextInt(list.size());
        button2.setText(list.get(int2));
        list.remove(int2);
        int int3 = randomGenerator.nextInt(list.size());
        button3.setText(list.get(int3));
        list.remove(int3);
        int int4 = randomGenerator.nextInt(list.size());
        button4.setText(list.get(int4));
        list.remove(int4);
        int int5 = randomGenerator.nextInt(list.size());
        button5.setText(list.get(int5));
        list.remove(int5);
        int int6 = randomGenerator.nextInt(list.size());
        button6.setText(list.get(int6));
        list.remove(int6);
        // Set the main word text view
        mainTextView.setText(mMainWord);
    }


    /**
     * Method called when first button is pressed
     *
     * @param v
     */
    public void firstButton(View v) {
        // if the choice is correct
        if (button1.getText() == mExplanation) {
            // start new game
            startGame();
        } else {
            // else disable this choice
            button1.setEnabled(false);

        }

    }

    /**
     * Method called when second button is pressed
     *
     * @param v
     */
    public void secondButton(View v) {
        // if the choice is correct
        if (button2.getText() == mExplanation) {
            // start new game
            startGame();
        } else {
            // else disable this choice
            button2.setEnabled(false);
        }
    }

    /**
     * Method called when third button is pressed
     *
     * @param v
     */
    public void thirdButton(View v) {
        // if the choice is correct
        if (button3.getText() == mExplanation) {
            // start new game
            startGame();
        } else {
            // else disable this choice
            button3.setEnabled(false);
        }
    }

    /**
     * Method called when fourth button is pressed
     *
     * @param v
     */
    public void fourthButton(View v) {
        // if the choice is correct
        if (button4.getText() == mExplanation) {
            // start new game
            startGame();
        } else {
            // else disable this choice
            button4.setEnabled(false);
        }
    }

    /**
     * Method called when fifth button is pressed
     *
     * @param v
     */
    public void fifthButton(View v) {
        // if the choice is correct
        if (button5.getText() == mExplanation) {
            // start new game
            startGame();
        } else {
            // else disable this choice
            button5.setEnabled(false);
        }
    }

    /**
     * Method called when sixth button is pressed
     *
     * @param v
     */
    public void sixthButton(View v) {
        // if the choice is correct
        if (button6.getText() == mExplanation) {
            // start new game
            startGame();
        } else {
            // else disable this choice
            button6.setEnabled(false);
        }
    }

}
