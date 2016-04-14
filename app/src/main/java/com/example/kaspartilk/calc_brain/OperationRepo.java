package com.example.kaspartilk.calc_brain;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by KasparTilk on 10.04.2016.
 */
public class OperationRepo extends Repo<Operation> {

    private OperatorRepo operatorRepo;
    public OperationRepo(SQLiteDatabase database, String tablename, String[] allColumns, OperatorRepo opRepo) {
        super(database, tablename, allColumns);
        operatorRepo = opRepo;
    }

    @Override
    public ContentValues entityToContentValues(Operation entity) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.OPERATIONS_COLUMN_OPERATORID, entity.getOperatorId());
        values.put(SQLiteHelper.OPERATIONS_COLUMN_NUM1, entity.getNum1());
        values.put(SQLiteHelper.OPERATIONS_COLUMN_NUM2, entity.getNum2());
        values.put(SQLiteHelper.OPERATIONS_COLUMN_RES, entity.getRes());
        values.put(SQLiteHelper.OPERATIONS_COLUMN_TIMESTAMP, entity.getTimestamp());
        return values;
    }

    @Override
    public Operation cursorToEntity(Cursor cursor) {
        Operation operation = new Operation();
        operation.setId(cursor.getLong(0));
        operation.setOperatorId(cursor.getInt(1));
        operation.setNum1(cursor.getFloat(2));
        operation.setNum2(cursor.getFloat(3));
        operation.setRes(cursor.getFloat(4));
        operation.setTimestamp(cursor.getInt(5));
        Operator operator = operatorRepo.getById(operation.getOperatorId());
        operation.setOperator(operator.getOperator());
        return operation;
    }
}
