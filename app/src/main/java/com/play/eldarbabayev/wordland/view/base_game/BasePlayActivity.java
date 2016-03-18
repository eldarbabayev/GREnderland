package com.play.eldarbabayev.wordland.view.base_game;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.play.eldarbabayev.wordland.common.GenericActivity;
import com.play.eldarbabayev.wordland.common.Utils;
import com.play.eldarbabayev.wordland.model.cache.PlayerCache;
import com.play.eldarbabayev.wordland.model.cache.RatingCache;
import com.play.eldarbabayev.wordland.model.cache.WordCache;
import com.play.eldarbabayev.wordland.model.jsonword.Word;
import com.play.eldarbabayev.wordland.presenter.base_game.BasePlayActivityOps;
import com.play.eldarbabayev.wordland.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * BasePlayActivity that is used to play the game with existing dictionary
 */
public class BasePlayActivity
        extends GenericActivity<BasePlayActivity,
                BasePlayActivityOps,
                BasePlayActivityOps> {

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

    /**
     * Forty random words from the database used to play at each level.
     */
    private PlayerCache mPlayerCache;

    /**
     * Forty random words from the database used to play at each level.
     */
    private RatingCache mRatingCache;


    private int current_rating;
    private Toolbar toolBar;

    private int count;



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

        current_rating = 50;

        mPlayerCache = new PlayerCache(this);



        mRatingCache = new RatingCache(this);
        // Invoke the special onCreate() method in GenericActivity,
        // passing in the VideoOps class to instantiate/manage and
        // "this" to provide VideoOps with the VideoOps.View instance.
        super.onCreate(BasePlayActivityOps.class,
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
        // Set the Title text style and Logo
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
     * Method that will give the forty random numbers
     * between 0 and size of database
     */
    public void getFortyRandomWords() {
        // Create a new list that contains 6 numbers
        ArrayList<Integer> list = new ArrayList<Integer>();
        // Add integers from 0 to size - 1
        int length = mWordCache.sizeBase();

        for (int i=1; i<=length; i++) {
            list.add(i);
        }
        // Shuffle these integers
        Collections.shuffle(list);
        // Initialise the 40 random integer choices by picking first
        // 40 numbers from the shuffled list

        for (int i=0; i<10; i++) {
            Word new_word = mWordCache.getBaseWord(list.get(i));
            new_word.setWordId(count);
            mPlayerCache.putPlayerWord(new_word);
            count = count + 1;
        }
    }


    /**
     * Method that will give the six random numbers
     * between 0 and size of database
     */
    public void getSixRandomNumbersFromForty() {
        // Create a new list that contains 6 numbers
        ArrayList<Integer> list = new ArrayList<Integer>();
        // Add integers from 0 to size - 1
        for (int i=1; i<=10; i++) {
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

        count = 1;
        // Get 40 random words
       if (mPlayerCache.sizePlayer() == 0) {

            getFortyRandomWords();

       }

        getSixRandomNumbersFromForty();


        // Find the random word from database along with it's Explanation
        Word word_main = mPlayerCache.getPlayerWord(mainWordInt);
        // Find other words
        Word word2 = mPlayerCache.getPlayerWord(secondChoiceInt);
        Word word3 = mPlayerCache.getPlayerWord(thirdChoiceInt);
        Word word4 = mPlayerCache.getPlayerWord(fourthChoiceInt);
        Word word5 = mPlayerCache.getPlayerWord(fifthChoiceInt);
        Word word6 = mPlayerCache.getPlayerWord(sixthChoiceInt);
        // Get word of the main word
        mMainWord = word_main.getMainWord();
        // Get explanation of the main word
        mExplanation = word_main.getExplanation();
        if (mExplanation.length() > 40) {
            mExplanation = mExplanation.substring(0, 40) + "...";
        }
        // Get all other explanations
        String Explanation2 = word2.getExplanation();
        if (Explanation2.length() > 40) {
            Explanation2 = Explanation2.substring(0, 40) + "...";
        }
        String Explanation3 = word3.getExplanation();
        if (Explanation3.length() > 40) {
            Explanation3 = Explanation3.substring(0, 40) + "...";
        }
        String Explanation4 = word4.getExplanation();
        if (Explanation4.length() > 40) {
            Explanation4 = Explanation4.substring(0, 40) + "...";
        }
        String Explanation5 = word5.getExplanation();
        if (Explanation5.length() > 40) {
            Explanation5 = Explanation5.substring(0, 40) + "...";
        }
        String Explanation6 = word6.getExplanation();
        if (Explanation6.length() > 40) {
            Explanation6 = Explanation6.substring(0, 40) + "...";
        }
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
        String text1 = list.get(int1);


        // Set text for random button

            button1.setText(text1);

        // remove chosen number from list
        list.remove(int1);


        // repeat for the rest 5 buttons
        int int2 = randomGenerator.nextInt(list.size());
        String text2 = list.get(int2);


        // Set text for random button

            button2.setText(text2);

        list.remove(int2);


        int int3 = randomGenerator.nextInt(list.size());
        String text3 = list.get(int3);


        // Set text for random button

            button3.setText(text3);

        list.remove(int3);


        int int4 = randomGenerator.nextInt(list.size());
        String text4 = list.get(int4);


        // Set text for random button

            button4.setText(text4);

        list.remove(int4);


        int int5 = randomGenerator.nextInt(list.size());
        String text5 = list.get(int5);


        // Set text for random button

            button5.setText(text5);

        list.remove(int5);


        int int6 = randomGenerator.nextInt(list.size());
        String text6 = list.get(int6);


        // Set text for random button

            button6.setText(text6);

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
            Log.d(TAG, " got rating " + current_rating);

            updateRatingTable();

        } else {
            // else disable this choice
            button1.setEnabled(false);
            if (current_rating > 0) {
                current_rating = current_rating - 10;
            } else {
                //do nothing
            }

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
            Log.d(TAG, " got rating " + current_rating);

            updateRatingTable();
        } else {
            // else disable this choice
            button2.setEnabled(false);
            if (current_rating > 0) {
                current_rating = current_rating - 10;
            } else {
                //do nothing
            }
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
            Log.d(TAG, " got rating " + current_rating);

            updateRatingTable();
        } else {
            // else disable this choice
            button3.setEnabled(false);
            if (current_rating > 0) {
                current_rating = current_rating - 10;
            } else {
                //do nothing
            }

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
            Log.d(TAG, " got rating " + current_rating);

            updateRatingTable();
        } else {
            // else disable this choice
            button4.setEnabled(false);
            if (current_rating > 0) {
                current_rating = current_rating - 10;
            } else {
                //do nothing
            }

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
            Log.d(TAG, " got rating " + current_rating);

            updateRatingTable();
        } else {
            // else disable this choice
            button5.setEnabled(false);
            if (current_rating > 0) {
                current_rating = current_rating - 10;
            } else {
                //do nothing
            }

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
            Log.d(TAG, " got rating " + current_rating);

            updateRatingTable();
        } else {
            // else disable this choice
            button6.setEnabled(false);
            if (current_rating > 0) {
                current_rating = current_rating - 10;
            } else {
                //do nothing
            }

        }
    }

    public void updateRatingTable() {

        int old_count = mRatingCache.getCount();

        int new_count = old_count + 1;

        int old_rating = mRatingCache.getRating();

        int new_rating = (old_count * old_rating + current_rating) / new_count;

        Log.d(TAG, " average rating so far " + new_rating);
        Log.d(TAG, " count so far " + new_count);
        current_rating = 50;

        if (new_count < 10) {
            Log.d(TAG, " I am here one " );
            mRatingCache.modifyRecords(new_count, new_rating);
            startGame();
        } else {

            if (new_rating < 40) {
                Log.d(TAG, " I am here two " );

                Utils.showToast(getActivityContext(), "Try again, you can grow!");
                startGameWithOldWords();
            } else {
                Log.d(TAG, " I am here three " );

                Utils.showToast(getActivityContext(), "Well done, but you can grow even more!");
                startGameWithNewWords();

            }
        }

    }

    public void startGameWithOldWords() {
        mRatingCache.modifyRecords(0,0);
        startGame();

    }

    public void startGameWithNewWords() {
        mRatingCache.modifyRecords(0,0);
        mPlayerCache.deleteAllRecords();
        startGame();
    }





}
