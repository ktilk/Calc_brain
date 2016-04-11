package com.example.kaspartilk.calc_brain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by KasparTilk on 10.04.2016.
 */
public class SQLiteHelper extends SQLiteOpenHelper{

    public static final String TABLE_OPERANDS = "operands";
    public static final String OPERANDS_COLUMN_ID = "_id";
    public static final String OPERANDS_COLUMN_OPERAND = "operand";
    public static final String OPERANDS_COLUMN_LIFETIMECOUNTER = "lifetimecounter";
    public static final String[] OPERANDS_ALLCOLUMNS =
            { OPERANDS_COLUMN_ID, OPERANDS_COLUMN_OPERAND, OPERANDS_COLUMN_LIFETIMECOUNTER };

    public static final String TABLE_OPERATIONS = "operations";
    public static final String OPERATIONS_COLUMN_ID = "_id";
    public static final String OPERATIONS_COLUMN_OPERANDID = "operandId";
    public static final String OPERATIONS_COLUMN_NUM1 = "num1";
    public static final String OPERATIONS_COLUMN_NUM2 = "num2";
    public static final String OPERATIONS_COLUMN_RES = "res";
    public static final String OPERATIONS_COLUMN_TIMESTAMP = "timestamp";
    public static final String[] OPERATIONS_ALLCOLUMNS =
            { OPERATIONS_COLUMN_ID, OPERATIONS_COLUMN_OPERANDID, OPERATIONS_COLUMN_NUM1, OPERATIONS_COLUMN_NUM2, OPERATIONS_COLUMN_RES, OPERATIONS_COLUMN_TIMESTAMP };

    public static final String TABLE_STATISTICS = "day_statistics";
    public static final String STATISTICS_COLUMN_ID = "_id";
    public static final String STATISTICS_COLUMN_DAYSTAMP = "daystamp";
    public static final String STATISTICS_COLUMN_OPERANDID = "operandId";
    public static final String STATISTICS_COLUMN_DAYCOUNTER = "daycounter";
    public static final String[] STATISTICS_ALLCOLUMNS =
            { STATISTICS_COLUMN_ID, STATISTICS_COLUMN_OPERANDID, STATISTICS_COLUMN_DAYCOUNTER };

    private static final String DATABASE_NAME = "calculatordb.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE_OPERANDS = "create table "
            + TABLE_OPERANDS + "("
            + OPERANDS_COLUMN_ID + " integer primary key autoincrement, "
            + OPERANDS_COLUMN_OPERAND + " text not null, "
            + OPERANDS_COLUMN_LIFETIMECOUNTER + "integer not null);";

    private static final String DATABASE_CREATE_OPERATIONS = "create table "
            + TABLE_OPERATIONS + "("
            + OPERATIONS_COLUMN_ID + " integer primary key autoincrement, "
            + OPERATIONS_COLUMN_OPERANDID + " integer not null, "
            + OPERATIONS_COLUMN_NUM1 + "text not null, "
            + OPERATIONS_COLUMN_NUM2 + "text not null, "
            + OPERATIONS_COLUMN_RES + "text not null, "
            + OPERATIONS_COLUMN_TIMESTAMP + "integer not null);";

    private static final String DATABASE_CREATE_STATISTICS = "create table "
            + TABLE_STATISTICS + "("
            + STATISTICS_COLUMN_DAYSTAMP + " integer not null, "
            + STATISTICS_COLUMN_ID + " integer primary key autoincrement, "
            + STATISTICS_COLUMN_OPERANDID + " integer not null, "
            + STATISTICS_COLUMN_DAYCOUNTER + "integer not null);";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_OPERANDS);
        db.execSQL(DATABASE_CREATE_OPERATIONS);
        db.execSQL(DATABASE_CREATE_STATISTICS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERANDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATISTICS);
        onCreate(db);
    }
}
