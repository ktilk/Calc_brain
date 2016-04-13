package com.example.kaspartilk.calc_brain;

/**
 * Created by KasparTilk on 10.04.2016.
 */
public class Statistics {
    private long id;
    private int dayStamp;
    private long operatorId;
    private int dayCounter;

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
}
