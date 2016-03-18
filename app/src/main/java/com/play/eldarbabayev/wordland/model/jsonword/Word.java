package com.play.eldarbabayev.wordland.model.jsonword;

import java.util.Objects;

/**
 * "Plain Ol' Java Object" (POJO) class
 */
public class Word {
    /**
     * Various fields
     */
    private String main_word;
    private String explanation;
    private boolean selected = false;
    private long wordId;

    /**
     * No-op constructor
     */
    public Word() {
    }

    /**
     * Constructor that initializes main_word and explanation.
     */
    public Word(
                String main_word,
                String explanation
                ) {
        this.main_word = main_word;
        this.explanation = explanation;
    }


    /**
     * Constructor that initializes main_word and explanation.
     */
    public Word(
            String main_word,
            String explanation,
            long wordId
    ) {
        this.main_word = main_word;
        this.explanation = explanation;
        this.wordId = wordId;
    }

    /*
     * Getters and setters to access Words.
     */

    /**
     * Get the main word of the Word.
     *
     * */
    public String getMainWord() {
        return main_word;
    }

    /**
     * Get the Video by Id
     *
     */
    public void setMainWord(String main_word) {
        this.main_word = main_word;
    }

    /**
     * Get the Explanation of Word.
     *
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * Set the Explanation of Word.
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    /**
     * Get the main word of the Word.
     *
     * */
    public long getWordId() {
        return wordId;
    }

    /**
     * Get the Video by Id
     *
     */
    public void setWordId(long wordId) {
        this.wordId = wordId;
    }


    /**
     * @return the textual representation of Video object.
     */
    @Override
    public String toString() {
        return "{" +
                "Main Word: "+ main_word + ", "+
                "Explanation: "+ explanation + ", "+
                "Selected: "+ selected +
                "}";
    }

    /**
     * @return an Integer hash code for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getMainWord(),
                getExplanation());
    }

    /**
     * @return Compares this Video instance with specified
     *         Video and indicates if they are equal.
     */
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Word)
                && Objects.equals(getMainWord(),
                ((Word) obj).getMainWord())
                && Objects.equals(getExplanation(),((Word) obj).getExplanation());
    }
}