package com.example.effcode.sqldatabasedemo.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME="Student.db";
    public static final String TABLE_NAME="Student_Table";
    public static final String Column_id="ID";
    public static final String column_name="NAME";
    public static final String column_surname="SURNAME";
    public static final String column_marks="MARKS";


    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean insertData(String name,String surname,String marks)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(column_name,name);
        contentValues.put(column_surname,surname);
        contentValues.put(column_marks,marks);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
        {
            return false;
        }
        else
            return  true;
    }


    public Cursor getData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM " +TABLE_NAME,null);
        return  cursor;

    }

    public boolean updateData(String id,String name,String surname,String marks)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Column_id,id);
        contentValues.put(column_name,name);
        contentValues.put(column_surname,surname);
        contentValues.put(column_marks,marks);
        db.update(TABLE_NAME,contentValues,"ID = ?",new String[] {id});
        return true;

    }

    public Integer getDelete(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID= ?",new String[] {id});

    }

}
