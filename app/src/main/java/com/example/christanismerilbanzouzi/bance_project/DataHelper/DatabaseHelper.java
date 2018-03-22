package com.example.christanismerilbanzouzi.bance_project.DataHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by christanismerilbanzouzi on 19/03/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="MyDB.db";
    public static final String TABLE_NAME="Article.db";
    public static final String Col_1="ID";
    public static final String COl_2="NAME";
    public static final String COl_3="PRICE";
    public static final String COl_4="DESCRIPTION";
    public static final String COl_5="IMAGE";

    // Constructeur de la base de donnée
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Article ( ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, PRICE INTEGER, DESCRIPTION TEXT, IMAGE TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
