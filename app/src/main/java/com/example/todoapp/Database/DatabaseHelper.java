package com.example.todoapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String TAG = DatabaseHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "Todolist.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TODO_TABLE = "todo_main";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITTLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";

    public static final String USER_TABLE = "users";
    public static final String COLUMN_IDUSER = "_id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "password";

    SQLiteDatabase db =  this.getWritableDatabase();

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String table_todo =
//                "CREATE TABLE " + TODO_TABLE +
//                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                        COLUMN_TITTLE + " TEXT, " +
//                        COLUMN_DESCRIPTION + " TEXT);";

        String createTableTodo = String.format("CREATE TABLE %s (" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT, %s TEXT)", TODO_TABLE,
                COLUMN_TITTLE, COLUMN_DESCRIPTION);

        String createTableUser = String.format("CREATE TABLE %s (" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT, %s TEXT)", USER_TABLE,
                COLUMN_EMAIL, COLUMN_PASS);
//        String table_user =
//                "CREATE TABLE " + USER_TABLE + "("
//                + COLUMN_IDUSER + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + COLUMN_EMAIL + " TEXT,"
//                + COLUMN_PASS + " TEXT);";

//        db.execSQL(table_todo);
//        db.execSQL(table_user);
        db.execSQL(createTableTodo);
        db.execSQL(createTableUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE  IF EXISTS " + TODO_TABLE);
        db.execSQL("DROP TABLE  IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

    public void addUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASS, password);

        long id = db.insert(USER_TABLE, null, values);
        db.close();

        Log.d(TAG, "user inserted" + id);
    }

    public boolean getUser(String email, String pass){
        //HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "select * from  " + USER_TABLE + " where " +
                COLUMN_EMAIL + " = " + "'"+email+"'" + " and " + COLUMN_PASS + " = " + "'"+pass+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            return true;
        }
        cursor.close();
        db.close();

        return false;
    }

    public void addTask(String title, String description) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITTLE, title);
        cv.put(COLUMN_DESCRIPTION, description);
        long result = db.insert(TODO_TABLE, null, cv);
        if(result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TODO_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void updateData(String row_id, String title, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITTLE, title);
        cv.put(COLUMN_DESCRIPTION, description);

        long result = db.update(TODO_TABLE, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TODO_TABLE, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

//    public void deleteTask(int id){
//        db.delete(, ID + "= ?", new String[] {String.valueOf(id)});
//    }

}
