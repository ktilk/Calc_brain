package com.example.kaspartilk.calc_brain;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by KasparTilk on 10.04.2016.
 */
public class StatisticsRepo extends Repo<Statistics> {
    OperatorRepo operatorRepo;
    public StatisticsRepo(SQLiteDatabase database, String tablename, String[] allColumns, OperatorRepo opRepo) {
        super(database, tablename, allColumns);
        operatorRepo = opRepo;
    }

    @Override
    public ContentValues entityToContentValues(Statistics entity) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.STATISTICS_COLUMN_DAYSTAMP, entity.getDayStamp());
        values.put(SQLiteHelper.STATISTICS_COLUMN_OPERATORID, entity.getOperatorId());
        values.put(SQLiteHelper.STATISTICS_COLUMN_DAYCOUNTER, entity.getDayCounter());
        return values;
    }

    @Override
    public Statistics cursorToEntity(Cursor cursor) {
        Statistics statistics = new Statistics();
        statistics.setId(cursor.getLong(0));
        statistics.setDayStamp(cursor.getInt(1));
        statistics.setOperatorId(cursor.getInt(2));
        statistics.setDayCounter(cursor.getInt(3));
        Operator operator = operatorRepo.getById(statistics.getOperatorId());
        statistics.setOperator(operator.getOperator());
        return statistics;
    }

    public int getDayStamp(){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(sdf.format(date));
    }

    public Statistics incrementDayCounter(long id){
        Statistics stats;
        int dayStamp = getDayStamp();
        Cursor cursor = getDatabase().query(getTablename(), getAllColumns(), getAllColumns()[1] + "=" + dayStamp + " and " + getAllColumns()[2] + "=" + id, null, null, null, null);
        //if demanded row is not in database, add a row
        if(cursor == null || cursor.getCount() < 1){
            Statistics newStats = new Statistics();
            newStats.setDayCounter(1);
            newStats.setDayStamp(dayStamp);
            newStats.setOperatorId(id);
            return add(newStats);
        }else { // else if row exists, increment day counter by 1
            cursor.moveToFirst();
            stats = cursorToEntity(cursor);
            stats.setDayCounter(stats.getDayCounter()+1);
            update(stats);
            return stats;
        }
    }
}
