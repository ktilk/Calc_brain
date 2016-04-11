package com.example.kaspartilk.calc_brain;

/**
 * Created by KasparTilk on 10.04.2016.
 */
public class Statistics {
    private long id;
    private int daystamp;
    private long operandId;
    private int dayCounter;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDaystamp() {
        return daystamp;
    }

    public void setDaystamp(int daystamp) {
        this.daystamp = daystamp;
    }

    public long getOperandId() {
        return operandId;
    }

    public void setOperandId(long operandId) {
        this.operandId = operandId;
    }

    public int getDayCounter() {
        return dayCounter;
    }

    public void setDayCounter(int dayCounter) {
        this.dayCounter = dayCounter;
    }
}
