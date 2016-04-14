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
public class StatisticsAdapter extends CursorAdapter {

    private UOW uow;

    public StatisticsAdapter(Context context, Cursor c, UOW uow) {
        super(context, c, 0);
        this.uow = uow;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.day_statistics, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewOperatorData = (TextView) view.findViewById(R.id.day_statistics);
        Statistics statistics = uow.statisticsRepo.cursorToEntity(cursor);
        textViewOperatorData.setText(statistics.toString());
    }
}
