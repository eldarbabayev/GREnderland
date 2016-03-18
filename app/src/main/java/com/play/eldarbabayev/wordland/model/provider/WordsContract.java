package com.play.eldarbabayev.wordland.model.provider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;


/**
 * Defines table and column names for the Words database.
 */
public final class WordsContract{
    /**
     * The "Content authority" is a name for the entire content
     * provider, similar to the relationship between a domain name and
     * its website.  A convenient string to use for the content
     * authority is the package name for the app, which must be unique
     * on the device.
     */
    public static final String CONTENT_AUTHORITY =
            "com.play.eldarbabayev2.grenderland";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's that apps
     * will use to contact the content provider.
     */
    public static final Uri BASE_CONTENT_URI =
            Uri.parse("content://"
                    + CONTENT_AUTHORITY);

    public static final String PATH_CUSTOM_WORDS =
            CustomWordsEntry.CUSTOM_WORDS_TABLE_NAME;

    public static final String PATH_BASE_WORDS =
            BaseWordsEntry.BASE_WORDS_TABLE_NAME;

    public static final String PATH_PLAYER_WORDS =
            PlayerWordEntry.PLAYER_WORDS_TABLE_NAME;

    public static final String PATH_PLAYER_RATING =
            PlayerRatingEntry.PLAYER_RATING_TABLE_NAME;




    /**
     * Inner class that defines the contents of the Words table.
     */
    public static final class CustomWordsEntry implements BaseColumns {


        /**
         * Name of the database table.
         */
        public static final String CUSTOM_WORDS_TABLE_NAME =
                "custom_word_table";


        /**
         * Use BASE_CONTENT_URI to create the unique URI for Words
         * Table that apps will use to contact the content provider.
         */
        public static final Uri CUSTOM_WORDS_CONTENT_URI =
                BASE_CONTENT_URI.buildUpon()
                        .appendPath(PATH_CUSTOM_WORDS)
                        .build();


        /**
         * MIME type for multiple Word Values rows.
         */
        public static final String CUSTOM_WORDS_ITEMS =
                ContentResolver.CURSOR_DIR_BASE_TYPE
                        + "/"
                        + CONTENT_AUTHORITY
                        + "/"
                        + PATH_CUSTOM_WORDS;

        /**
         * MIME type for a single Word Values row
         */
        public static final String CUSTOM_WORDS_ITEM =
                ContentResolver.CURSOR_ITEM_BASE_TYPE
                        + "/"
                        + CONTENT_AUTHORITY
                        + "/"
                        + PATH_CUSTOM_WORDS;


        /**
         * Columns to store Data of each Word.
         */
        public static final String COLUMN_MAIN_WORD_CUSTOM = "custom_word_column";
        public static final String COLUMN_EXPLANATION_CUSTOM = "custom_explanation_column";
        public static final String COLUMN_WORDID_CUSTOM = "word_id";


        /**
         * Return a Uri that points to the row containing a given id.
         *
         * @param id
         * @return Uri
         */
        public static Uri buildWordUri(Long id) {
            return ContentUris.withAppendedId(CUSTOM_WORDS_CONTENT_URI,
                    id);
        }
    }

        /**
         * Inner class that defines the contents of the Words table.
         */
        public static final class BaseWordsEntry implements BaseColumns {


            /**
             * Name of the database table.
             */
            public static final String BASE_WORDS_TABLE_NAME =
                    "base_word_table";


            /**
             * Use BASE_CONTENT_URI to create the unique URI for Words
             * Table that apps will use to contact the content provider.
             */
            public static final Uri BASE_WORDS_CONTENT_URI =
                    BASE_CONTENT_URI.buildUpon()
                            .appendPath(PATH_BASE_WORDS)
                            .build();


            /**
             * MIME type for multiple Word Values rows.
             */
            public static final String BASE_WORDS_ITEMS =
                    ContentResolver.CURSOR_DIR_BASE_TYPE
                            + "/"
                            + CONTENT_AUTHORITY
                            + "/"
                            + PATH_BASE_WORDS;

            /**
             * MIME type for a single Word Values row
             */
            public static final String BASE_WORDS_ITEM =
                    ContentResolver.CURSOR_ITEM_BASE_TYPE
                            + "/"
                            + CONTENT_AUTHORITY
                            + "/"
                            + PATH_BASE_WORDS;


            /**
             * Columns to store Data of each Word.
             */
            public static final String COLUMN_MAIN_WORD_BASE = "base_word_column";
            public static final String COLUMN_EXPLANATION_BASE = "base_explanation_column";



            /**
             * Return a Uri that points to the row containing a given id.
             *
             * @param id
             * @return Uri
             */
            public static Uri buildWordUri(Long id) {
                return ContentUris.withAppendedId(BASE_WORDS_CONTENT_URI,
                        id);
            }
    }


    /**
     * Inner class that defines the contents of the Words table.
     */
    public static final class PlayerWordEntry implements BaseColumns {


        /**
         * Name of the database table.
         */
        public static final String PLAYER_WORDS_TABLE_NAME =
                "player_words_table";


        /**
         * Use BASE_CONTENT_URI to create the unique URI for Words
         * Table that apps will use to contact the content provider.
         */
        public static final Uri PLAYER_WORDS_CONTENT_URI =
                BASE_CONTENT_URI.buildUpon()
                        .appendPath(PATH_PLAYER_WORDS)
                        .build();


        /**
         * MIME type for multiple Word Values rows.
         */
        public static final String PLAYER_WORDS_ITEMS =
                ContentResolver.CURSOR_DIR_BASE_TYPE
                        + "/"
                        + CONTENT_AUTHORITY
                        + "/"
                        + PATH_PLAYER_WORDS;

        /**
         * MIME type for a single Word Values row
         */
        public static final String PLAYER_WORDS_ITEM =
                ContentResolver.CURSOR_ITEM_BASE_TYPE
                        + "/"
                        + CONTENT_AUTHORITY
                        + "/"
                        + PATH_PLAYER_WORDS;


        /**
         * Columns to store Data of each Word.
         */
        public static final String COLUMN_PLAYER_WORD = "player_word_column";
        public static final String COLUMN_PLAYER_EXPLANATION = "player_explanation_column";
        public static final String COLUMN_PLAYER_WORD_ID = "player_word_id";



        /**
         * Return a Uri that points to the row containing a given id.
         *
         * @param id
         * @return Uri
         */
        public static Uri buildWordUri(Long id) {
            return ContentUris.withAppendedId(PLAYER_WORDS_CONTENT_URI,
                    id);
        }
    }

    /**
     * Inner class that defines the contents of the Words table.
     */
    public static final class PlayerRatingEntry implements BaseColumns {


        /**
         * Name of the database table.
         */
        public static final String PLAYER_RATING_TABLE_NAME =
                "player_rating_data";


        /**
         * Use BASE_CONTENT_URI to create the unique URI for Words
         * Table that apps will use to contact the content provider.
         */
        public static final Uri PLAYER_RATING_CONTENT_URI =
                BASE_CONTENT_URI.buildUpon()
                        .appendPath(PATH_PLAYER_RATING)
                        .build();


        /**
         * MIME type for multiple Word Values rows.
         */
        public static final String PLAYER_RATING_ITEMS =
                ContentResolver.CURSOR_DIR_BASE_TYPE
                        + "/"
                        + CONTENT_AUTHORITY
                        + "/"
                        + PATH_PLAYER_RATING;

        /**
         * MIME type for a single Word Values row
         */
        public static final String PLAYER_RATING_ITEM =
                ContentResolver.CURSOR_ITEM_BASE_TYPE
                        + "/"
                        + CONTENT_AUTHORITY
                        + "/"
                        + PATH_PLAYER_RATING;


        /**
         * Columns to store Data of each Word.
         */
        public static final String COLUMN_COUNT = "count";
        public static final String COLUMN_RATING = "rating";



        /**
         * Return a Uri that points to the row containing a given id.
         *
         * @param id
         * @return Uri
         */
        public static Uri buildWordUri(Long id) {
            return ContentUris.withAppendedId(PLAYER_RATING_CONTENT_URI,
                    id);
        }
    }




}


