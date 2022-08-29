package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper
{
    private Context context;
    private static final String DATABASE_NAME = "like_db.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "like_table";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_RID = "r_id";

    public MyDatabaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + TABLE_NAME
                + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_RID + " TEXT); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addLike(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_RID, id);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "레시피에 좋아요를 눌렀습니다", Toast.LENGTH_SHORT).show();
        }
    }

    public Integer delLike(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Toast.makeText(context, "좋아요를 취소했습니다", Toast.LENGTH_SHORT).show();
        return db.delete(TABLE_NAME, "r_id = ?", new String[] {id});
    }

    public Cursor AllView() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.query(TABLE_NAME, new String[] { COLUMN_ID, COLUMN_RID }, null, null, null, null, null);
    }

}