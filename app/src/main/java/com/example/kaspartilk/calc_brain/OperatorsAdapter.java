package com.example.kaspartilk.calc_brain;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by KasparTilk on 11.04.2016.
 */
public class OperatorsAdapter extends CursorAdapter {

    private UOW uow;

    public OperatorsAdapter(Context context, Cursor c, UOW uow) {
        super(context, c, 0);
        this.uow = uow;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.operator_statistics, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewOperatorData = (TextView) view.findViewById(R.id.operator_statistics);
        Operator operator = uow.operatorRepo.cursorToEntity(cursor);
        textViewOperatorData.setText("Operator \"" + operator.getOperator() + "\" has been used " + operator.getLifetimeCounter() + " times.");
    }

}
