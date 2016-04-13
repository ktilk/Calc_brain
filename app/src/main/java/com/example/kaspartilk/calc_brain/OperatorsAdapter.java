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

    private final LayoutInflater layoutInflater;
    private UOW uow;
    private ViewGroup parentViewGroup;

    public OperatorsAdapter(Context context, Cursor c, UOW uow) {
        super(context, c, 0);
        layoutInflater = LayoutInflater.from(context);
        this.uow = uow;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final View view = layoutInflater.inflate(R.layout.operator_statistics, parent, false);
        parentViewGroup = parent;
        return view;
    }
    //TODO bindView
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewOperatorData = (TextView) view.findViewById(R.id.operator_statistics);
        Operator operator = uow.operatorRepo.cursorToEntity(cursor);
        textViewOperatorData.setText("Tehet " + operator.getOperator() + " on kasutatud " + operator.getLifetimeCounter() + " korda.");
    }

}
