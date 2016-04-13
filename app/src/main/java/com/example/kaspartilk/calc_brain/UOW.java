package com.example.kaspartilk.calc_brain;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by KasparTilk on 11.04.2016.
 */
public class UOW {
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    private final Context context;

    public OperatorRepo operatorRepo;
    public OperationRepo operationRepo;
    public StatisticsRepo statisticsRepo;

    public UOW(Context context){
        this.context = context;
        dbHelper = new SQLiteHelper(context);
        open();
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
        operatorRepo = new OperatorRepo(database, dbHelper.TABLE_OPERATORS, dbHelper.OPERATORS_ALLCOLUMNS);
        operationRepo = new OperationRepo(database, dbHelper.TABLE_OPERATIONS, dbHelper.OPERATIONS_ALLCOLUMNS);
        statisticsRepo = new StatisticsRepo(database, dbHelper.TABLE_STATISTICS, dbHelper.STATISTICS_ALLCOLUMNS);
    }

    public void close() {
        dbHelper.close();
    }

    public void dropCreateDatabase() {
        dbHelper.dropCreateDatabase(database);
        seedDatabase();
    }

    public void addStatistics(String operator, Double num1, Double num2, Double answer){
        Operator op = operatorRepo.getByOperator(operator);
        //increment lifetime counter by one
        op.setLifetimeCounter(op.getLifetimeCounter()+1);
        operatorRepo.update(op);
        //add new operation to database
        operationRepo.add(new Operation(op.getId(), num1, num2, answer));
        // increment daily counter
        statisticsRepo.incrementDayCounter(op.getId());
    }
    public void seedDatabase(){
        operatorRepo.add(new Operator("+"));
        operatorRepo.add(new Operator("-"));
        operatorRepo.add(new Operator("*"));
        operatorRepo.add(new Operator("/"));
        statisticsRepo.add(new Statistics(20160413, 1, 1));
    }
}
