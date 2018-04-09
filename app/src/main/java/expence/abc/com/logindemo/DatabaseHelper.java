package expence.abc.com.logindemo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import expence.abc.com.logindemo.model.User;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "user_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create users table
        db.execSQL(User.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertUser(String user) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(User.COLUMN_NAME, user);

        // insert row
        long id = db.insert(User.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public User getUser(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(User.TABLE_NAME,
                new String[]{User.COLUMN_ID, User.COLUMN_NAME, User.COLUMN_TIMESTAMP},
                User.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare user object
        User user = new User(
                cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return user;
    }

    public List<User> getAllUsers() {
        List<User> userArrayList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + User.TABLE_NAME + " ORDER BY " +
                User.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.id = (cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)));
                user.name = cursor.getString(cursor.getColumnIndex(User.COLUMN_NAME));
                user.timestamp = cursor.getString(cursor.getColumnIndex(User.COLUMN_TIMESTAMP));

                userArrayList.add(user);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return userArrayList list
        return userArrayList;
    }

    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + User.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.COLUMN_NAME, user.name);

        // updating row
        return db.update(User.TABLE_NAME, values, User.COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.id)});
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(User.TABLE_NAME, User.COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.id)});
        db.close();
    }
}