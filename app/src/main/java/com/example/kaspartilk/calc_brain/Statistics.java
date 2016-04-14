package com.example.kaspartilk.calc_brain;

/**
 * Created by KasparTilk on 10.04.2016.
 */
public class Statistics implements IEntity{
    private long id;
    private int dayStamp;
    private long operatorId;
    private int dayCounter;
    private String operator;

    public Statistics(){}

    public Statistics(int dayStamp, long opId, int dayCounter){
        this.dayStamp= dayStamp;
        operatorId = opId;
        this.dayCounter = dayCounter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDayStamp() {
        return dayStamp;
    }

    public void setDayStamp(int dayStamp) {
        this.dayStamp = dayStamp;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    public int getDayCounter() {
        return dayCounter;
    }

    public void setDayCounter(int dayCounter) {
        this.dayCounter = dayCounter;
    }

    @Override
    public String toString(){
        String date = convertDayStampToDate(dayStamp);
        return "Date: " + date + " Operator: " + operator + " Number of operations: " + dayCounter;
    }

    public String convertDayStampToDate(int stampInt){
        String stamp = stampInt + "";
        StringBuilder sb = new StringBuilder();
        sb.append(stamp.substring(6, 8));
        sb.append(".");
        sb.append(stamp.substring(4, 6));
        sb.append(".");
        sb.append(stamp.substring(0, 4));
        return sb.toString();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
