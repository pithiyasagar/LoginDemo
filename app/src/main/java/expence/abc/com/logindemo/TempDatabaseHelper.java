package expence.abc.com.logindemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TempDatabaseHelper extends SQLiteOpenHelper {

    //Name database
    private static String DATABASE_NAME = "user_db";

    //Version database
    private static int DATABASE_VERSION = 1;

    //name table
    private static String TABLE_USER_NAME = "user_tbl";

    //name table field
    private static String FIELD_USER_NAME = "user_name";
    private static String FIELD_USER_DETAIL = "user_detail";

    public TempDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_USER_NAME + "( " +
                FIELD_USER_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIELD_USER_DETAIL + " TEXT " + ")";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertUser() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //String insertData = "INSERT INTO " + TABLE_USER_NAME + " VALUES(6, 'Sagar');";
        //sqLiteDatabase.execSQL(insertData);

        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_USER_DETAIL, "RAVI");

        sqLiteDatabase.insert(TABLE_USER_NAME, null, contentValues);
        //Test
    }

    public void selectUser() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USER_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        do {
            Log.i("FIELD_USER_DETAIL", cursor.getString(1));
        } while (cursor.moveToNext());
    }

}
