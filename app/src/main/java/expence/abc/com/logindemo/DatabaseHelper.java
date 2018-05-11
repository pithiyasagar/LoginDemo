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

    private static final String TABLE_NAME = "user";

    private static final String COLUMN_ID = "id";
    public final String COLUMN_NAME = "name";
    public final String COLUMN_EMAIL = "email";
    public final String COLUMN_PASSWORD = "password";
    public final String COLUMN_ADDRESS = "address";
    public final String COLUMN_GENDER = "gender";
    public final String COLUMN_HOBBY = "hobby";
    private final String COLUMN_TIMESTAMP = "timestamp";


    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create users table
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_ADDRESS + " TEXT,"
                + COLUMN_GENDER + " INTEGER,"
                + COLUMN_HOBBY + " INTEGER,"
                + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                + ")";

        db.execSQL(CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        //onCreate(db);
    }

    public long insertUser(ContentValues contentValues) {
        // get writable database as we want to write data
        SQLiteDatabase writableDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(COLUMN_NAME, "");
        values.put(COLUMN_EMAIL, "");
        values.put(COLUMN_PASSWORD, "");
        values.put(COLUMN_ADDRESS, "");
        values.put(COLUMN_GENDER, 1);
        values.put(COLUMN_HOBBY, 1);

        // insert row
        long id = writableDatabase.insert(TABLE_NAME, null, contentValues);

        // close writableDatabase connection
        writableDatabase.close();

        // return newly inserted row id
        return id;
    }

    public User getUser(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_TIMESTAMP},
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare user object
        User user = new User();
        user.id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
        user.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        user.email = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        user.password = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        user.address = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        user.gender = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME));
        user.timestamp = cursor.getString(cursor.getColumnIndex(COLUMN_TIMESTAMP));

        // close the db connection
        cursor.close();

        return user;
    }

    public List<User> getAllUsers() {
        List<User> userArrayList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " ORDER BY " +
                COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.id = (cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                user.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                user.email = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                user.password = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                user.address = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                user.gender = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME));
                user.hobby = cursor.getInt(cursor.getColumnIndex(COLUMN_TIMESTAMP));

                userArrayList.add(user);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return userArrayList list
        return userArrayList;
    }

    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateUser(int updateId, ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(COLUMN_NAME, "");
        values.put(COLUMN_EMAIL, "");
        values.put(COLUMN_PASSWORD, "");
        values.put(COLUMN_ADDRESS, "");
        values.put(COLUMN_GENDER, 1);
        values.put(COLUMN_HOBBY, 1);

        // updating row
        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(updateId)});
    }

    public void deleteUser(int deleteId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(deleteId)});
        db.close();
    }
}