package com.example.kaspartilk.calc_brain;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

/**
 * Created by KasparTilk on 11.04.2016.
 */
public class OperationsAdapter extends CursorAdapter {

    private UOW uow;

    public OperationsAdapter(Context context, Cursor c, UOW uow) {
        super(context, c, 0);
        this.uow = uow;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.operation, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewOperatorData = (TextView) view.findViewById(R.id.operation);
        Operation operation = uow.operationRepo.cursorToEntity(cursor);
        textViewOperatorData.setText(operation.toString());
    }

}
