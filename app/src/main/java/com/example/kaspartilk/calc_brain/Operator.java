package com.example.kaspartilk.calc_brain;

/**
 * Created by KasparTilk on 10.04.2016.
 */
public class Operator {
    private long id;
    private String operator;
    private int lifetimeCounter = 0;

    public Operator(){}

    public Operator(String operator){
        this.operator = operator;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getLifetimeCounter() {
        return lifetimeCounter;
    }

    public void setLifetimeCounter(int lifetimeCounter) {
        this.lifetimeCounter = lifetimeCounter;
    }
}
