package com.example.kaspartilk.calc_brain;

import java.util.Date;

/**
 * Created by KasparTilk on 10.04.2016.
 */
public class Operation implements IEntity {
    private long id;
    private long operatorId;
    private double num1;
    private double num2;
    private double res;
    private int timestamp;

    public Operation(long opId, double num1, double num2, double res){
        this.operatorId = opId;
        this.num1 = num1;
        this.num2 = num2;
        this.res = res;
        timestamp = (int) new Date().getTime(); // integeri kuupäevaks muutmine : http://stackoverflow.com/a/3371337/5580816
    }

    public Operation(){}

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

    public double getNum1() {
        return num1;
    }

    public void setNum1(double num1) {
        this.num1 = num1;
    }

    public double getNum2() {
        return num2;
    }

    public void setNum2(double num2) {
        this.num2 = num2;
    }

    public double getRes() {
        return res;
    }

    public void setRes(double res) {
        this.res = res;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
