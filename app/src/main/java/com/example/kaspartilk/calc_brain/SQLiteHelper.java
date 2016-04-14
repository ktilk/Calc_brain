package com.example.kaspartilk.calc_brain;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by KasparTilk on 10.04.2016.
 */
public class SQLiteHelper extends SQLiteOpenHelper{

    public static final String TABLE_OPERATORS = "operators";
    public static final String OPERATORS_COLUMN_ID = "_id";
    public static final String OPERATORS_COLUMN_OPERATOR = "operator";
    public static final String OPERATORS_COLUMN_LIFETIMECOUNTER = "lifetimeCounter";
    public static final String[] OPERATORS_ALLCOLUMNS =
            { OPERATORS_COLUMN_ID, OPERATORS_COLUMN_OPERATOR, OPERATORS_COLUMN_LIFETIMECOUNTER };

    public static final String TABLE_OPERATIONS = "operations";
    public static final String OPERATIONS_COLUMN_ID = "_id";
    public static final String OPERATIONS_COLUMN_OPERATORID = "operatorId";
    public static final String OPERATIONS_COLUMN_NUM1 = "num1";
    public static final String OPERATIONS_COLUMN_NUM2 = "num2";
    public static final String OPERATIONS_COLUMN_RES = "res";
    public static final String OPERATIONS_COLUMN_TIMESTAMP = "timestamp";
    public static final String[] OPERATIONS_ALLCOLUMNS =
            { OPERATIONS_COLUMN_ID, OPERATIONS_COLUMN_OPERATORID, OPERATIONS_COLUMN_NUM1, OPERATIONS_COLUMN_NUM2, OPERATIONS_COLUMN_RES, OPERATIONS_COLUMN_TIMESTAMP };

    public static final String TABLE_STATISTICS = "day_statistics";
    public static final String STATISTICS_COLUMN_ID = "_id";
    public static final String STATISTICS_COLUMN_DAYSTAMP = "dayStamp";
    public static final String STATISTICS_COLUMN_OPERATORID = "operatorId";
    public static final String STATISTICS_COLUMN_DAYCOUNTER = "dayCounter";
    public static final String[] STATISTICS_ALLCOLUMNS =
            { STATISTICS_COLUMN_ID, STATISTICS_COLUMN_DAYSTAMP, STATISTICS_COLUMN_OPERATORID, STATISTICS_COLUMN_DAYCOUNTER };

    private static final String DATABASE_NAME = "calculatordb.db";
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE_OPERATORS = "create table "
            + TABLE_OPERATORS + "("
            + OPERATORS_COLUMN_ID + " integer primary key autoincrement, "
            + OPERATORS_COLUMN_OPERATOR + " text not null, "
            + OPERATORS_COLUMN_LIFETIMECOUNTER + " integer not null);";

    private static final String DATABASE_CREATE_OPERATIONS = "create table "
            + TABLE_OPERATIONS + "("
            + OPERATIONS_COLUMN_ID + " integer primary key autoincrement, "
            + OPERATIONS_COLUMN_OPERATORID + " integer not null, "
            + OPERATIONS_COLUMN_NUM1 + " text not null, "
            + OPERATIONS_COLUMN_NUM2 + " text not null, "
            + OPERATIONS_COLUMN_RES + " text not null, "
            + OPERATIONS_COLUMN_TIMESTAMP + " integer not null);";

    private static final String DATABASE_CREATE_STATISTICS = "create table "
            + TABLE_STATISTICS + "("
            + STATISTICS_COLUMN_DAYSTAMP + " integer not null, "
            + STATISTICS_COLUMN_ID + " integer primary key autoincrement, "
            + STATISTICS_COLUMN_OPERATORID + " integer not null, "
            + STATISTICS_COLUMN_DAYCOUNTER + " integer not null);";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_OPERATORS);
        db.execSQL(DATABASE_CREATE_OPERATIONS);
        db.execSQL(DATABASE_CREATE_STATISTICS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERATORS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATISTICS);
        onCreate(db);
    }

    public void dropCreateDatabase(SQLiteDatabase db){
        //Kustuta need read:
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERATORS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATISTICS);
        //Tekita need read:
        db.execSQL(DATABASE_CREATE_OPERATORS);
        db.execSQL(DATABASE_CREATE_OPERATIONS);
        db.execSQL(DATABASE_CREATE_STATISTICS);

    }

    //code from https://github.com/sanathp/DatabaseManager_For_Android
    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "mesage" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });
            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {
                alc.set(0,c);
                c.moveToFirst();
                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }
    }
}
