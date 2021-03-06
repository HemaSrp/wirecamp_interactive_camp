package com.wirecamp.assignment.wirecamp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.Looper;

import com.wirecamp.assignment.wirecamp.model.DatabaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to store,retrieve and delete the data in db
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "WireCampInteractive";

    // Table name
    private static final String CONNECTION_DETAILS = "CONNECTION_DETAILS";

    // From name
    private static final String FROM_NAME = "FROM_NAME";

    // From Latitude
    private static final String FROM_LATITUDE = "FROM_LATITUDE";

    //From longitude
    private static final String FROM_LONGITUDE = "FROM_LONGITUDE";

    //Depature time
    private static final String DEPARTURE_TIME = "DEPARTURE_TIME";

    // Favourites
    private static final String FAVOURITES = "FAVOURITES";

    // To name
    private static final String TO_NAME = "TO_NAME";

    // To latitude
    private static final String TO_LATITUDE = "TO_LATITUDE";

    //To longitude
    private static final String TO_LONGITUDE = "TO_LONGITUDE";

    //To arrival time
    private static final String TO_ARRIVAL_TIME = "TO_ARRIVAL_TIME";

    //TO random id
    private static final String RANDOM_ID="RANDOM_ID";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE_QUERY = "CREATE TABLE " + CONNECTION_DETAILS + "" +
                "(" +
                FROM_NAME + " TEXT not null,"
                + FROM_LATITUDE + " TEXT,"
                + FROM_LONGITUDE + " TEXT," +
                DEPARTURE_TIME + " TEXT," +
                TO_NAME + " TEXT not null,"
                + TO_LATITUDE + " TEXT,"
                + TO_LONGITUDE + " TEXT," +
                TO_ARRIVAL_TIME + " TEXT," +
                 RANDOM_ID + " TEXT,"+
                FAVOURITES + " TEXT"+
                ")";
        sqLiteDatabase.execSQL(CREATE_TABLE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CONNECTION_DETAILS);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    /**
     * This method is used to insert the photo in table.
     *
     * @param photo object
     */

    public void insertCollections(DatabaseModel photo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FROM_LATITUDE, photo.getFromLatitude());
        values.put(FROM_NAME, photo.getFromName());
        values.put(DEPARTURE_TIME, photo.getDepatureTime());
        values.put(FROM_LONGITUDE, photo.getFromLongitude());
        values.put(TO_NAME, photo.getToName());
        values.put(TO_LATITUDE, photo.getToLatitude());
        values.put(TO_LONGITUDE, photo.getToLongitude());
        values.put(TO_ARRIVAL_TIME, photo.getArrivalTime());
        values.put(FAVOURITES, photo.getFavourites());
        values.put(RANDOM_ID, photo.getRandomId());

        db.insert(CONNECTION_DETAILS, null, values);
        db.close(); // Closing database connection
    }

    /**
     * This method is used to get all collection from the table
     *
     * @return list
     */
    public List<DatabaseModel> getAllDetails() {
        List<DatabaseModel> photosList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + CONNECTION_DETAILS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DatabaseModel photo = new DatabaseModel();
                photo.setFromName(cursor.getString(0));
                photo.setFromLatitude(cursor.getDouble(1));
                photo.setFromLongitude(cursor.getDouble(2));
                photo.setDepatureTime(cursor.getString(3));
                photo.setToName(cursor.getString(4));
                photo.setToLatitude(cursor.getDouble(5));
                photo.setToLongitude(cursor.getDouble(6));
                photo.setArrivalTime(cursor.getString(7));
                photo.setFavourites(cursor.getString(9));
                photo.setRandomId(cursor.getString(8));
                photosList.add(photo);
            } while (cursor.moveToNext());
        }

        // return photo list
        return photosList;
    }

    /**
     * This method is used to get all favourites from the table
     *
     * @return
     */
    public List<DatabaseModel> getAllFavourites() {
        List<DatabaseModel> photosList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + CONNECTION_DETAILS +" WHERE "+FAVOURITES +" = 1" ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                DatabaseModel photo = new DatabaseModel();
                photo.setFromName(cursor.getString(0));
                photo.setFromLatitude(cursor.getDouble(1));
                photo.setFromLongitude(cursor.getDouble(2));
                photo.setDepatureTime(cursor.getString(3));
                photo.setToName(cursor.getString(4));
                photo.setToLatitude(cursor.getDouble(5));
                photo.setToLongitude(cursor.getDouble(6));
                photo.setArrivalTime(cursor.getString(7));
                photo.setFavourites(cursor.getString(7));
                photo.setRandomId(cursor.getString(8));
                photosList.add(photo);
            } while (cursor.moveToNext());
        }

        // return photo list
        return photosList;
    }
    /**
     * This method is used to update the favourites
     *
     *
     * @param updateString updateValue
     * @param collection   value
     * @return 1
     */
    public int updateContact(String updateString, DatabaseModel collection) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FAVOURITES,updateString);

        // updating row
        return db.update(CONNECTION_DETAILS, values, RANDOM_ID + " = ?",
                new String[]{String.valueOf(collection.getRandomId())});
    }


    /**
     * This method is used to delete the record from db
     */
    public void deleteRecord() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CONNECTION_DETAILS, null, null);
    }

}
