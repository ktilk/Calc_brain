package com.example.kaspartilk.calc_brain;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by KasparTilk on 10.04.2016.
 */
public class OperandRepo extends Repo<Operator> {

    public OperandRepo(SQLiteDatabase database, String tablename, String[] allColumns) {
        super(database, tablename, allColumns);
    }

    @Override
    public ContentValues entityToContentValues(Operator entity) {
        ContentValues values = new ContentValues();
        values.put(getAllColumns()[1], entity.getOperator());
        values.put(getAllColumns()[2], entity.getLifetimeCounter());
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
        Operator newOfEntity;
        Cursor cursor = getDatabase().query(getTablename(),
                getAllColumns(), getAllColumns()[1] + " = '" + op +"'",
                null, null, null, null);

        if (cursor == null || cursor.getCount()<1) {
            //lisame
            Operator opObj = new Operator();
            opObj.setOperator(op);
            opObj.setLifetimeCounter(0);
            newOfEntity = add(opObj);
        } else {
            cursor.moveToFirst();
            newOfEntity = cursorToEntity(cursor);
        }

        return newOfEntity;
    }
}
