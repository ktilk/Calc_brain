package com.example.kaspartilk.calc_brain;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by KasparTilk on 10.04.2016.
 */
public class OperandRepo extends Repo<Operand> {

    public OperandRepo(SQLiteDatabase database, String tablename, String[] allColumns) {
        super(database, tablename, allColumns);
    }

    @Override
    public ContentValues entityToContentValues(Operand entity) {
        ContentValues values = new ContentValues();
        values.put(getAllColumns()[1], entity.getOperand());
        values.put(getAllColumns()[2], entity.getLifetimeCounter());
        return values;
    }

    @Override
    public Operand cursorToEntity(Cursor cursor) {
        Operand operand = new Operand();
        operand.setId(cursor.getLong(0));
        operand.setOperand(cursor.getString(1));
        operand.setLifetimeCounter(cursor.getInt(2));
        return operand;
    }
}
