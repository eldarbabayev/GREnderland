package com.play.eldarbabayev.wordland.view.ui;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.play.eldarbabayev.wordland.model.cache.WordCache;
import com.play.eldarbabayev.wordland.model.jsonword.Word;
import com.play.eldarbabayev.wordland.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Show the view for each Video's meta-data in a ListView.
 */
public class WordAdapter
       extends BaseAdapter {

    /**
     * LogCat tag.
     */
    private final static String TAG =
            WordAdapter.class.getSimpleName();

    /**
     * Allows access to application-specific resources and classes.
     */
    private final Context mContext;

    /**
     * ArrayList to hold list of Videos that is shown in ListView.
     */
    private List<Word> wordList =
        new ArrayList<>();

    private WordCache wordCache;

    /**
     * Construtor that stores the Application Context.
     * 
     * @param Context
     */
    public WordAdapter(Context context) {
        super();
        mContext = context;
    }

    /**
     * Method used by the ListView to "get" the "view" for each row of
     * data in the ListView.
     * 
     * @param position
     *            The position of the item within the adapter's data
     *            set of the item whose view we want. convertView The
     *            old view to reuse, if possible. Note: You should
     *            check that this view is non-null and of an
     *            appropriate type before using. If it is not possible
     *            to convert this view to display the correct data,
     *            this method can create a new view. Heterogeneous
     *            lists can specify their number of view types, so
     *            that this View is always of the right type (see
     *            getViewTypeCount() and getItemViewType(int)).
     * @param parent
     *            The parent that this view will eventually be
     *            attached to
     * @return A View corresponding to the data at the specified
     *         position.
     */
    public View getView(int position,
                        View convertView,
                        ViewGroup parent) {
        Word word = wordList.get(position);

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =
                mInflater.inflate(R.layout.dictionary_list_item,null);
        }

        TextView wordmain =
            (TextView) convertView.findViewById(R.id.Word);
        wordmain.setText(word.getMainWord());
        TextView explan =
                (TextView) convertView.findViewById(R.id.Explanation);
        explan.setText(word.getExplanation());

        final CheckBox checkbox = (CheckBox) convertView.findViewById(R.id.checkbox);

        checkbox.setTag(word);

        checkbox.setChecked(false);

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Word new_word = (Word) checkbox.getTag();
                Log.d(TAG, " " + isChecked);
                new_word.setSelected(isChecked);
            }
        });

        return convertView;
    }

    /**
     * Adds a Video to the Adapter and notify the change.
     */
    public void add(Word word) {
        wordList.add(word);
        notifyDataSetChanged();
    }

    /**
     * Removes a Video from the Adapter and notify the change.
     */
    public void remove(Word word) {
        wordList.remove(word);
        notifyDataSetChanged();
    }

    /**
     * Get the List of Videos from Adapter.
     */
    public List<Word> getWords() {
        return wordList;
    }

    /**
     * Set the Adapter to list of Videos.
     */
    public void setWords(List<Word> words) {
        this.wordList = words;
        notifyDataSetChanged();
    }

    /**
     * Get the no of videos in adapter.
     */
    public int getCount() {
            return wordList.size();
    }

    /**
     * Get video from a given position.
     */
    public Word getItem(int position) {
        return wordList.get(position);
    }

    /**
     * Get Id of video from a given position.
     */
    public long getItemId(int position) {
        return position;
    }

    public void swapCursor(Cursor cursor) {

        if (cursor != null) {
            wordCache = new WordCache(mContext);

            List<Word> new_words = wordCache.getAllCustomWords(cursor);

            setWords(new_words);
        } else {
            setWords(new ArrayList<Word>());
        }

    }
}
