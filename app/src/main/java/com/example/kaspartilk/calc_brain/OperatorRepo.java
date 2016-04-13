package com.example.kaspartilk.calc_brain;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by KasparTilk on 10.04.2016.
 */
public class OperatorRepo extends Repo<Operator> {

    public OperatorRepo(SQLiteDatabase database, String tablename, String[] allColumns) {
        super(database, tablename, allColumns);
    }

    @Override
    public ContentValues entityToContentValues(Operator entity) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.OPERATORS_COLUMN_OPERATOR, entity.getOperator());
        values.put(SQLiteHelper.OPERATORS_COLUMN_LIFETIMECOUNTER, entity.getLifetimeCounter());
        return values;
    }

    @Override
    public Operator cursorToEntity(Cursor cursor) {
        Operator operator = new Operator();
        operator.setId(cursor.getLong(0));
        operator.setOperator(cursor.getString(1));
        operator.setLifetimeCounter(cursor.getInt(2));
        return operator;
    }

    public Operator getByOperator(String op) {
        Operator entity;
        Cursor cursor = getDatabase().query(getTablename(),
                getAllColumns(), getAllColumns()[1] + " = '" + op +"'", // SELECT * FROM operators WHERE operator = op
                null, null, null, null);
        cursor.moveToFirst();
        entity = cursorToEntity(cursor);
        return entity;
    }
}
