package com.example.kaspartilk.calc_brain;

/**
 * Created by KasparTilk on 10.04.2016.
 */
public class Operation {
    private long id;
    private long operandId;
    private float num1;
    private float num2;
    private float res;
    private int timestamp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOperandId() {
        return operandId;
    }

    public void setOperandId(long operandId) {
        this.operandId = operandId;
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
