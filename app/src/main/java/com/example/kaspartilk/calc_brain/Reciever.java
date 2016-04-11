package com.example.kaspartilk.calc_brain;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by KasparTilk on 11.04.2016.
 */
public class Reciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (isOrderedBroadcast()){
            Bundle ex = intent.getExtras(); // Any additional information, used to provide more information to the component
            if (ex != null){
                UOW uow = new UOW(context);
                // Take values from extras
                Double num1 = ex.getDouble("numero1");
                Double num2 = ex.getDouble("numero2");
                String op = ex.getString("operand");
                // Calculate answer
                Double answer = calculate(num1, num2, op);
                //operation succeeded
                //setResult(Activity.RESULT_OK, answer.toString());
                setResultCode(Activity.RESULT_OK);
                setResultData(answer.toString());
                //Add data to db
                //TODO baasi lisamine
            }

        }
    }
    public static  Double calculate(Double num1, Double num2, String op){
        Double answer = 0.0;
        try{
            switch (op){
                case "+":
                    answer = num1 + num2;
                    break;
                case "-":
                    answer = num1 - num2;
                    break;
                case "*":
                    answer = num1 * num2;
                    break;
                case "/":
                    answer = num1 / num2;
                    break;
            }
            //if calculation cannot be done
        } catch (ArithmeticException a){
            return Double.NaN;
        } catch (NumberFormatException n){
            return Double.NaN;
        }
        return answer;
    }
}
