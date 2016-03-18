package com.play.eldarbabayev.wordland.model.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.play.eldarbabayev.wordland.model.provider.WordsContract.CustomWordsEntry;
import com.play.eldarbabayev.wordland.model.provider.WordsContract.BaseWordsEntry;
import com.play.eldarbabayev.wordland.model.provider.WordsContract.PlayerWordEntry;
import com.play.eldarbabayev.wordland.model.provider.WordsContract.PlayerRatingEntry;


/**
 * Content Provider to access Words Database.
 */
public class WordsProvider extends ContentProvider {
    /**
     * Debugging tag used by the Android logger.
     */
    private static final String TAG =
            WordsProvider.class.getSimpleName();

    /**
     * Use WordsDatabaseHelper to manage database creation and version
     * management.
     */
    private WordsDatabaseHelper mOpenHelper;
    /**
     * UriMatcher code for the Words table.
     */
    public static final int CUSTOM_WORDS = 100;

    /**
     * UriMatcher code for a specific row in the Words table.
     */
    public static final int CUSTOM_WORD = 101;

    /**
     * UriMatcher code for the Words table.
     */
    public static final int BASE_WORDS = 200;

    /**
     * UriMatcher code for a specific row in the Words table.
     */
    public static final int BASE_WORD = 201;

    /**
     * UriMatcher code for the Words table.
     */
    public static final int PLAYER_WORDS = 300;

    /**
     * UriMatcher code for a specific row in the Words table.
     */
    public static final int PLAYER_WORD = 301;

    /**
     * UriMatcher code for the Words table.
     */
    public static final int PLAYER_RATINGS = 400;

    /**
     * UriMatcher code for a specific row in the Words table.
     */
    public static final int PLAYER_RATING = 401;




    /**
     * The URI Matcher used by this content provider.
     */
    private static final UriMatcher sUriMatcher =
            buildUriMatcher();

    /**
     * Helper method to match each URI to the WORDS integers
     * constant defined above.
     *
     * @return UriMatcher
     */
    private static UriMatcher buildUriMatcher() {
        // All paths added to the UriMatcher have a corresponding code
        // to return when a match is found.  The code passed into the
        // constructor represents the code to return for the rootURI.
        // It's common to use NO_MATCH as the code for this case.
        final UriMatcher matcher =
                new UriMatcher(UriMatcher.NO_MATCH);

        // Initialize the matcher with the URIs used to access each
        // table.
        matcher.addURI(WordsContract.CONTENT_AUTHORITY,
                WordsContract.PATH_CUSTOM_WORDS,
                CUSTOM_WORDS);
        matcher.addURI(WordsContract.CONTENT_AUTHORITY,
                WordsContract.PATH_CUSTOM_WORDS
                        + "/#",
                CUSTOM_WORD);

        // Initialize the matcher with the URIs used to access each
        // table.
        matcher.addURI(WordsContract.CONTENT_AUTHORITY,
                WordsContract.PATH_BASE_WORDS,
                BASE_WORDS);
        matcher.addURI(WordsContract.CONTENT_AUTHORITY,
                WordsContract.PATH_BASE_WORDS
                        + "/#",
                BASE_WORD);

        // Initialize the matcher with the URIs used to access each
        // table.
        matcher.addURI(WordsContract.CONTENT_AUTHORITY,
                WordsContract.PATH_PLAYER_WORDS,
                PLAYER_WORDS);
        matcher.addURI(WordsContract.CONTENT_AUTHORITY,
                WordsContract.PATH_PLAYER_WORDS
                        + "/#",
                PLAYER_WORD);

        // Initialize the matcher with the URIs used to access each
        // table.
        matcher.addURI(WordsContract.CONTENT_AUTHORITY,
                WordsContract.PATH_PLAYER_RATING,
                PLAYER_RATINGS);
        matcher.addURI(WordsContract.CONTENT_AUTHORITY,
                WordsContract.PATH_PLAYER_RATING
                        + "/#",
                PLAYER_RATING);

        return matcher;
    }

    /**
     * Hook method called when Database is created to initialize the
     * Database Helper that provides access to the Words Database.
     */
    @Override
    public boolean onCreate() {
        mOpenHelper =
                new WordsDatabaseHelper(getContext());
        return true;
    }


    /**
     * Method called to handle type requests from client applications.
     * It returns the MIME type of the data associated with each URI.
     */
    @Override
    public String getType(Uri uri) {
        // Use the passed Uri to determine what data is being asked
        // for and return the appropriate MIME type
        switch (sUriMatcher.match(uri)) {
            case CUSTOM_WORDS:
                return CustomWordsEntry.CUSTOM_WORDS_ITEMS;
            case CUSTOM_WORD:
                return CustomWordsEntry.CUSTOM_WORDS_ITEM;
            case BASE_WORDS:
                return BaseWordsEntry.BASE_WORDS_ITEMS;
            case BASE_WORD:
                return BaseWordsEntry.BASE_WORDS_ITEM;
            case PLAYER_WORDS:
                return PlayerWordEntry.PLAYER_WORDS_ITEMS;
            case PLAYER_WORD:
                return PlayerWordEntry.PLAYER_WORDS_ITEM;
            case PLAYER_RATINGS:
                return PlayerRatingEntry.PLAYER_RATING_ITEMS;
            case PLAYER_RATING:
                return PlayerRatingEntry.PLAYER_RATING_ITEM;


            default:
                throw new UnsupportedOperationException("Unknown URI "
                        + uri);
        }
    }

    /**
     * Hook method called to handle requests to insert a new row.  As
     * a courtesy, notifyChange() is called after inserting.
     */
    @Override
    public Uri insert(Uri uri,
                      ContentValues values) {

        // The table to perform the insert on.
        String table;

        // The Uri containing the inserted row's id that is returned
        // to the caller.
        Uri resultUri;



        // Determine the base Uri to return and the table to insert on
        // using the UriMatcher.
        switch (sUriMatcher.match(uri)) {


            case CUSTOM_WORDS:
                table = CustomWordsEntry.CUSTOM_WORDS_TABLE_NAME;
                resultUri = CustomWordsEntry.CUSTOM_WORDS_CONTENT_URI;
                break;


            case CUSTOM_WORD:
                table = CustomWordsEntry.CUSTOM_WORDS_TABLE_NAME;
                resultUri = CustomWordsEntry.CUSTOM_WORDS_CONTENT_URI;
                break;

            case BASE_WORDS:
                table = BaseWordsEntry.BASE_WORDS_TABLE_NAME;
                resultUri = BaseWordsEntry.BASE_WORDS_CONTENT_URI;
                break;


            case BASE_WORD:
                table = BaseWordsEntry.BASE_WORDS_TABLE_NAME;
                resultUri = BaseWordsEntry.BASE_WORDS_CONTENT_URI;
                break;

            case PLAYER_WORDS:
                table = PlayerWordEntry.PLAYER_WORDS_TABLE_NAME;
                resultUri = PlayerWordEntry.PLAYER_WORDS_CONTENT_URI;
                break;


            case PLAYER_WORD:
                table = PlayerWordEntry.PLAYER_WORDS_TABLE_NAME;
                resultUri = PlayerWordEntry.PLAYER_WORDS_CONTENT_URI;
                break;

            case PLAYER_RATINGS:
                table = PlayerRatingEntry.PLAYER_RATING_TABLE_NAME;
                resultUri = PlayerRatingEntry.PLAYER_RATING_CONTENT_URI;
                break;


            case PLAYER_RATING:
                table = PlayerRatingEntry.PLAYER_RATING_TABLE_NAME;
                resultUri = PlayerRatingEntry.PLAYER_RATING_CONTENT_URI;
                break;



            default:
                throw new IllegalArgumentException("Unknown URI "
                        + uri);
        }

        // Insert the data into the correct table.
        final long insertRow =
                mOpenHelper.getWritableDatabase().insert
                        (table,
                                null,
                                values);

        // Check to ensure that the insertion worked.
        if (insertRow > 0) {

            // Create the result URI.
            Uri newUri = ContentUris.withAppendedId(resultUri,
                    insertRow);

            // Register to watch a content URI for changes.
            getContext().getContentResolver().notifyChange(newUri,
                    null);

            return newUri;
        } else
            throw new SQLException("Fail to add a new record into "
                    + uri);
    }


    /**
     * Method that handles bulk insert requests.
     */
    @Override
    public int bulkInsert(Uri uri,
                          ContentValues[] values) {

        Log.d(TAG, "I am here" );
        // Fetch the db from the helper.
        final SQLiteDatabase db =
                mOpenHelper.getWritableDatabase();

        String dbName;

        // Match the Uri against the table's uris to determine the
        // table in which table to insert the values.
        switch(sUriMatcher.match(uri)) {
            case CUSTOM_WORDS:
                dbName =
                        CustomWordsEntry.CUSTOM_WORDS_TABLE_NAME;
                break;
            case BASE_WORDS:
                dbName =
                        BaseWordsEntry.BASE_WORDS_TABLE_NAME;
                break;
            case PLAYER_WORDS:
                dbName =
                        PlayerWordEntry.PLAYER_WORDS_TABLE_NAME;
                break;
            case PLAYER_RATINGS:
                dbName =
                        PlayerRatingEntry.PLAYER_RATING_TABLE_NAME;
                break;



            default:
                throw new IllegalArgumentException("Unknown URI "
                        + uri);
        }

        // Insert the values into the table in one transaction by
        // beginning a transaction in EXCLUSIVE mode.
        db.beginTransaction();
        int returnCount = 0;
        try {
            for (ContentValues value : values) {
                final long id =
                        db.insert(dbName,
                                null,
                                value);
                if (id != -1)
                    returnCount++;
            }
            // Marks the current transaction as successful.
            db.setTransactionSuccessful();
        } finally {
            // End the transaction
            db.endTransaction();
        }

        // Notifies registered observers that rows were updated and
        // attempt to sync changes to the network.
        getContext().getContentResolver().notifyChange(uri,
                null);

        return returnCount;

    }




    /**
     * Hook method called to handle query requests from clients.
     */
    @Override
    public Cursor query(Uri uri,
                        String[] projection,
                        String whereStatement,
                        String[] whereStatementArgs,
                        String sortOrder) {
        Cursor retCursor;


        // Use the passed Uri to determine how to build the
        // query. This will determine the table that the query will
        // act on and possibly add row qualifications to the WHERE
        // clause.
        switch (sUriMatcher.match(uri)) {
            case CUSTOM_WORDS:
                retCursor =
                        mOpenHelper.getReadableDatabase().query
                                (CustomWordsEntry.CUSTOM_WORDS_TABLE_NAME,
                                        projection,
                                        whereStatement,
                                        whereStatementArgs,
                                        null,
                                        null,
                                        sortOrder);
                break;
            case CUSTOM_WORD:
                final String rowId1 =
                                ""
                                + CustomWordsEntry._ID
                                + " = '"
                                + ContentUris.parseId(uri)
                                + "'";
                retCursor =
                        mOpenHelper.getReadableDatabase().query
                                (CustomWordsEntry.CUSTOM_WORDS_TABLE_NAME,
                                        projection,
                                        rowId1,
                                        null,
                                        null,
                                        null,
                                        sortOrder);
                break;
            case BASE_WORDS:

                retCursor =
                        mOpenHelper.getReadableDatabase().query
                                (BaseWordsEntry.BASE_WORDS_TABLE_NAME,
                                        projection,
                                        whereStatement,
                                        whereStatementArgs,
                                        null,
                                        null,
                                        sortOrder);
                break;
            case BASE_WORD:
                final String rowId2 =
                        ""
                                + BaseWordsEntry._ID
                                + " = '"
                                + ContentUris.parseId(uri)
                                + "'";
                retCursor =
                        mOpenHelper.getReadableDatabase().query
                                (BaseWordsEntry.BASE_WORDS_TABLE_NAME,
                                        projection,
                                        rowId2,
                                        null,
                                        null,
                                        null,
                                        sortOrder);
                break;
            case PLAYER_WORDS:

                retCursor =
                        mOpenHelper.getReadableDatabase().query
                                (PlayerWordEntry.PLAYER_WORDS_TABLE_NAME,
                                        projection,
                                        whereStatement,
                                        whereStatementArgs,
                                        null,
                                        null,
                                        sortOrder);
                break;
            case PLAYER_WORD:
                final String rowId3 =
                        ""
                                + PlayerWordEntry._ID
                                + " = '"
                                + ContentUris.parseId(uri)
                                + "'";
                retCursor =
                        mOpenHelper.getReadableDatabase().query
                                (PlayerWordEntry.PLAYER_WORDS_TABLE_NAME,
                                        projection,
                                        rowId3,
                                        null,
                                        null,
                                        null,
                                        sortOrder);
                break;
            case PLAYER_RATINGS:

                retCursor =
                        mOpenHelper.getReadableDatabase().query
                                (PlayerRatingEntry.PLAYER_RATING_TABLE_NAME,
                                        projection,
                                        whereStatement,
                                        whereStatementArgs,
                                        null,
                                        null,
                                        sortOrder);
                break;
            case PLAYER_RATING:
                final String rowId4 =
                        ""
                                + PlayerRatingEntry._ID
                                + " = '"
                                + ContentUris.parseId(uri)
                                + "'";
                retCursor =
                        mOpenHelper.getReadableDatabase().query
                                (PlayerRatingEntry.PLAYER_RATING_TABLE_NAME,
                                        projection,
                                        rowId4,
                                        null,
                                        null,
                                        null,
                                        sortOrder);
                break;


            default:

                throw new UnsupportedOperationException("Unknown URI " + uri);
        }

        retCursor.setNotificationUri(getContext().getContentResolver(),
                uri);


        return retCursor;
    }

    /**
     * Hook method called to handle requests to update one or more
     * rows. The implementation should update all rows matching the
     * selection to set the columns according to the provided values
     * map. As a courtesy, notifyChange() is called after updating .
     */
    @Override
    public int update(Uri uri,
                      ContentValues values,
                      String whereStatement,
                      String[] whereStatementArgs) {
        // Number of rows updated.
        int rowsUpdated;

        final SQLiteDatabase db =
                mOpenHelper.getWritableDatabase();

        // Update the appropriate rows.  If the URI includes a
        // specific row to update, add that row to the where
        // statement.
        switch (sUriMatcher.match(uri)) {
            case CUSTOM_WORDS:
                rowsUpdated =
                        db.update(CustomWordsEntry.CUSTOM_WORDS_TABLE_NAME,
                                values,
                                whereStatement,
                                whereStatementArgs);
                break;
            case CUSTOM_WORD:
                rowsUpdated =
                        db.update(CustomWordsEntry.CUSTOM_WORDS_TABLE_NAME,
                                values,
                                whereStatement,
                                whereStatementArgs);
                break;

            case BASE_WORDS:
                rowsUpdated =
                        db.update(BaseWordsEntry.BASE_WORDS_TABLE_NAME,
                                values,
                                whereStatement,
                                whereStatementArgs);
                break;
            case BASE_WORD:
                rowsUpdated =
                        db.update(BaseWordsEntry.BASE_WORDS_TABLE_NAME,
                                values,
                                whereStatement,
                                whereStatementArgs);
                break;
            case PLAYER_WORDS:
                rowsUpdated =
                        db.update(PlayerWordEntry.PLAYER_WORDS_TABLE_NAME,
                                values,
                                whereStatement,
                                whereStatementArgs);
                break;
            case PLAYER_WORD:
                rowsUpdated =
                        db.update(PlayerWordEntry.PLAYER_WORDS_TABLE_NAME,
                                values,
                                whereStatement,
                                whereStatementArgs);
                break;
            case PLAYER_RATINGS:
                rowsUpdated =
                        db.update(PlayerRatingEntry.PLAYER_RATING_TABLE_NAME,
                                values,
                                whereStatement,
                                whereStatementArgs);
                break;
            case PLAYER_RATING:
                rowsUpdated =
                        db.update(PlayerRatingEntry.PLAYER_RATING_TABLE_NAME,
                                values,
                                whereStatement,
                                whereStatementArgs);
                break;



            default:
                throw new IllegalArgumentException("Unknown URI "
                        + uri);
        }

        // Register to watch a content URI for changes.
        getContext().getContentResolver().notifyChange(uri,
                null);

        return rowsUpdated;

    }

    /**
     * Hook method to handle requests to delete one or more rows.  The
     * implementation should apply the selection clause when
     * performing deletion, allowing the operation to affect multiple
     * rows in a directory.  As a courtesy, notifyChange() is called
     * after deleting.
     */
    @Override
    public int delete(Uri uri,
                      String whereStatement,
                      String[] whereStatementArgs) {
        // Number of rows deleted.
        int rowsDeleted;

        final SQLiteDatabase db =
                mOpenHelper.getWritableDatabase();

        // Delete the appropriate rows based on the Uri. If the URI
        // includes a specific row to delete, add that row to the
        // WHERE statement.
        switch (sUriMatcher.match(uri)) {
            case CUSTOM_WORDS:
                rowsDeleted =
                        db.delete(CustomWordsEntry.CUSTOM_WORDS_TABLE_NAME,
                                whereStatement,
                                whereStatementArgs);
                break;
            case CUSTOM_WORD:
                rowsDeleted =
                        db.delete(CustomWordsEntry.CUSTOM_WORDS_TABLE_NAME,
                                whereStatement,
                                whereStatementArgs);
                break;
            case BASE_WORDS:
                rowsDeleted =
                        db.delete(BaseWordsEntry.BASE_WORDS_TABLE_NAME,
                                whereStatement,
                                whereStatementArgs);
                break;
            case BASE_WORD:
                rowsDeleted =
                        db.delete(BaseWordsEntry.BASE_WORDS_TABLE_NAME,
                                whereStatement,
                                whereStatementArgs);
                break;
            case PLAYER_WORDS:
                rowsDeleted =
                        db.delete(PlayerWordEntry.PLAYER_WORDS_TABLE_NAME,
                                whereStatement,
                                whereStatementArgs);
                break;
            case PLAYER_WORD:
                rowsDeleted =
                        db.delete(PlayerWordEntry.PLAYER_WORDS_TABLE_NAME,
                                whereStatement,
                                whereStatementArgs);
                break;
            case PLAYER_RATINGS:
                rowsDeleted =
                        db.delete(PlayerRatingEntry.PLAYER_RATING_TABLE_NAME,
                                whereStatement,
                                whereStatementArgs);
                break;
            case PLAYER_RATING:
                rowsDeleted =
                        db.delete(PlayerRatingEntry.PLAYER_RATING_TABLE_NAME,
                                whereStatement,
                                whereStatementArgs);
                break;


            default:
                throw new IllegalArgumentException("Unknown URI "
                        + uri);
        }

        // Register to watch a content URI for changes.
        getContext().getContentResolver().notifyChange(uri,
                null);

        return rowsDeleted;
    }
}