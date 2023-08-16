package com.example.loginregister;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class CDB  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DMS";
    public CDB(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table TbUser(dno integer primary key autoincrement,uname text,upassword text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TbUser");
        onCreate(db);

    }
    public void addDept(String dn,String dl) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("uname", dn);
            cv.put("upassword", dl);
            db.insert("TbUser", null, cv);
            db.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public String[] getOneDepartment(int dno) {
        String a[] = new String[2];
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query("TbUser", new String[]{"dno", "uname", "upassword"}, "dno" + "=?", new String[]{
                    String.valueOf(dno)}, null, null, null);
            if (cursor != null && cursor.getCount() != 0) {
                cursor.moveToFirst();
                a[0] = cursor.getString(1);
                a[1] = cursor.getString(2);
            } else {
                a[0] = "";
                a[1] = "";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return a;
    }
    public int deleteDept(int dno) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("TbUser", "dno=?", new String[]{String.valueOf(dno)});
    }
    public boolean update(String uname, String oldPassword, String newPassword) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            Cursor cursor = db.query("TbUser", new String[]{"dno", "uname", "upassword"}, "uname" + "=? AND upassword" + "=?", new String[]{uname, oldPassword}, null, null, null);

            if (cursor != null && cursor.getCount() != 0) {
                cursor.moveToFirst();
                String storedPassword = cursor.getString(2);

                if (storedPassword.equals(newPassword)) {
                    return false;
                } else {
                    ContentValues values = new ContentValues();
                    values.put("upassword", newPassword);
                    db.update("TbUser", values, "dno=?", new String[]{cursor.getString(0)});
                    db.close();
                    return true;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public String find(String uname, String upassword) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query("TbUser", new String[]{"dno", "uname", "upassword"}, "uname" + "=?", new String[]{uname}, null, null, null);

            if (cursor != null && cursor.getCount() != 0) {
                cursor.moveToFirst();
                String storedPassword = cursor.getString(2);
                if (storedPassword.equals(upassword)) {
                    return cursor.getString(1);
                } else {
                    return "Incorrect password";
                }
            } else {
                return "User does not exist";
            }
        } catch (Exception e) {
            System.out.println(e);
            return "An error occurred";
        }
    }

}
