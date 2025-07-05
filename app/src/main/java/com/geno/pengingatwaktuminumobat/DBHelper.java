package com.geno.pengingatwaktuminumobat;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;
import java.util.*;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "reminders.db";
    private static final int DB_VER = 1;
    private static final String TABLE = "tbl_reminder";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_HOUR = "hour";
    private static final String COL_MIN = "minute";

    public DBHelper(Context ctx) {
        super(ctx, DB_NAME, null, DB_VER);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT, "
                + COL_HOUR + " INTEGER, "
                + COL_MIN + " INTEGER)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    // Insert
    public long addReminder(Reminder r) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, r.getName());
        cv.put(COL_HOUR, r.getHour());
        cv.put(COL_MIN, r.getMinute());
        return db.insert(TABLE, null, cv);
    }
    // Get all
    public List<Reminder> getAll() {
        List<Reminder> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE, null, null, null, null, null, COL_HOUR + "," + COL_MIN);
        if (c.moveToFirst()) {
            do {
                Reminder r = new Reminder(
                        c.getInt(c.getColumnIndexOrThrow(COL_ID)),
                        c.getString(c.getColumnIndexOrThrow(COL_NAME)),
                        c.getInt(c.getColumnIndexOrThrow(COL_HOUR)),
                        c.getInt(c.getColumnIndexOrThrow(COL_MIN))
                );
                list.add(r);
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }
    // Get by ID
    public Reminder getById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE, null, COL_ID+"=?", new String[]{String.valueOf(id)},
                null, null, null);
        if (c != null && c.moveToFirst()) {
            Reminder r = new Reminder(
                    c.getInt(c.getColumnIndexOrThrow(COL_ID)),
                    c.getString(c.getColumnIndexOrThrow(COL_NAME)),
                    c.getInt(c.getColumnIndexOrThrow(COL_HOUR)),
                    c.getInt(c.getColumnIndexOrThrow(COL_MIN))
            );
            c.close();
            return r;
        }
        return null;
    }

    public int updateReminder(Reminder r) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, r.getName());
        cv.put(COL_HOUR, r.getHour());
        cv.put(COL_MIN, r.getMinute());
        return db.update(TABLE, cv, COL_ID + "=?", new String[]{ String.valueOf(r.getId()) });
    }
    // Delete
    public int deleteById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE, COL_ID+"=?", new String[]{String.valueOf(id)});
    }
}

