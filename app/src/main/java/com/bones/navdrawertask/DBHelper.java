package com.bones.navdrawertask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo ip on 12/06/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "dbBalance.sqlite";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table tbl_income (id integer primary key autoincrement,title text,amount integer)");
        sqLiteDatabase.execSQL("create table tbl_outcome (id integer primary key autoincrement,title text,amount integer)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbl_income");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbl_outcome");
        onCreate(sqLiteDatabase);
    }

    public void addData(String table,String title,int amount){
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("amount", amount);

        // 3. insert
        db.insert(table, null, values);

        // 4. close
        db.close();
    }


    public int getTotalIncome(){
        int total = 0;

        String query = "select SUM(amount) from tbl_income";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            total = cursor.getInt(0);
        }

        return total;
    }

    public int getTotalOutcome(){
        int total = 0;

        String query = "select SUM(amount) from tbl_outcome";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            total = cursor.getInt(0);
        }

        return total;
    }

    public List<DatabaseModel> getData(String table) {
        List<DatabaseModel> modelList = new ArrayList<>();
        String query = "select * from " + table;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (table.equals("tbl_income")) {
            while (cursor.moveToNext()) {
                DatabaseModel dm = new DatabaseModel();

                dm.setIncomeId(cursor.getInt(cursor.getColumnIndex("id")));
                dm.setIncomeTitle(cursor.getString(cursor.getColumnIndex("title")));
                dm.setIncomeAmount(cursor.getInt(cursor.getColumnIndex("amount")));

            modelList.add(dm);
        }
        } else  {
            while (cursor.moveToNext()) {
                DatabaseModel dm = new DatabaseModel();

                    dm.setOutcomeId(cursor.getInt(cursor.getColumnIndex("id")));
                    dm.setOutcomeTitle(cursor.getString(cursor.getColumnIndex("title")));
                    dm.setOutcomeAmount(cursor.getInt(cursor.getColumnIndex("amount")));

                modelList.add(dm);
            }
        }

        return modelList;
    }



}
