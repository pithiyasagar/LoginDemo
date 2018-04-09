package expence.abc.com.logindemo.model;

public class User {
    public static final String TABLE_NAME = "user";
 
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TIMESTAMP = "timestamp";
 
    public int id;
    public String name;
    public String timestamp;
 
    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";
 
    public User() {
    }
 
    public User(int id, String name, String timestamp) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
    }
}