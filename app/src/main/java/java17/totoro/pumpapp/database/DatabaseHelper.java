package java17.totoro.pumpapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Totoro on 2018-03-16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String DATABASE_NAME = "LoginDB.db";
    private static final String TABLE_NAME = "login_table";
    private static final String COL0 = "ID";
    private static final String COL1 = "NAME";
    private static final String COL2 = "AGE";
    private static final String COL3 = "EMAIL";
    private static final String COL4 = "USERNAME";
    private static final String COL5 = "PASSWORD";

    // When this constructor is called, the database is created
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1 + " TEXT, " +
                COL2 + " INTEGER, " +
                COL3 + " TEXT, " +
                COL4 + " TEXT, " +
                COL5 + " TEXT)";
        db.execSQL(createTable);
        // db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,AGE INTEGER,EMAIL TEXT,USERNAME TEXT,PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, int age, String email, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL1, name);
        cv.put(COL2, age);
        cv.put(COL3, email);
        cv.put(COL4, username);
        cv.put(COL5, password);

        Log.d(TAG, "insertData: inserting " + name + ", " + age + ", " + email + ", " + username + ", " + password + " to " + TABLE_NAME);

        //returns -1 om det inte får in någon data
        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1)
            return false;
        else
            return true;
    }

    /************************************************
     * hämtar all data från databasen
     ************************************************/
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /************************************************
     * returnerar id för det namn som passar in
     ************************************************/
    public Cursor getItemId(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL0 + " FROM " + TABLE_NAME +
                " WHERE " + COL1 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /************************************************
     * updaterar namnet, där id och namn passar in
     ************************************************/
    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL1 +
                " = '" + newName + "' WHERE " + COL0 + " = '" + id + "'" +
                " AND " + COL1 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    /************************************************
     * deletar från databasen, där id och namn passar in
     ************************************************/
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " +
                COL0 + " = '" + id + "'" +
                " AND " + COL1 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

    /************************************************
     * Kontrollerar lösenord
     ************************************************/
    public String searchPassword(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COL4 + ", " + COL5 + " FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        String a, b;
        b = "not found";
        if (cursor.moveToFirst()){
            do{
                a = cursor.getString(0);

                if (a.equals(username)){
                    b = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());

        }
        return b;
    }
}
