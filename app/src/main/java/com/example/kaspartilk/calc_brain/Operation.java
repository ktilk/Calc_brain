package com.example.kaspartilk.calc_brain;

import java.util.Date;

/**
 * Created by KasparTilk on 10.04.2016.
 */
public class Operation {
    private long id;
    private long operatorId;
    private float num1;
    private float num2;
    private float res;
    private int timestamp;

    public Operation(long opId, float num1, float num2, float res){
        this.operatorId = opId;
        this.num1 = num1;
        this.num2 = num2;
        this.res = res;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    public float getNum1() {
        return num1;
    }

    public void setNum1(float num1) {
        this.num1 = num1;
    }

    public float getNum2() {
        return num2;
    }

    public void setNum2(float num2) {
        this.num2 = num2;
    }

    public float getRes() {
        return res;
    }

    public void setRes(float res) {
        this.res = res;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
