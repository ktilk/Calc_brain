package com.example.kaspartilk.calc_brain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private TextView textView;
    private UOW uow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.list);
        textView = (TextView) findViewById(R.id.text);
        uow = new UOW(getApplicationContext());
        uow.dropCreateDatabase();
        //uow.seedDatabase();
        //displayOperatorsListView();

    }
    //display how many times an operator has been used
    private void displayOperatorsListView(){
        String s = "Operator statistics";
        textView.setText(s);
        OperatorsAdapter adapter = new OperatorsAdapter(this, uow.operatorRepo.getCursorAll(), uow); //TODO adapter lõpuni teha
        listView.setAdapter(adapter);
    }

    private void displayOperationsListView(){
        String s = "All operations";
        textView.setText(s);
    }

    private void displayStatisticsListView(){

        String s = "Daily statistics";
        textView.setText(s);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_operator_statistics) {
            displayOperatorsListView();
        }else if (id == R.id.action_database_manager){
            displayDatabase();
        }else if (id == R.id.action_operations){
            displayDatabase();
        }else if (id == R.id.action_day_statistics){
            displayDatabase();
        }else if (id == R.id.action_delete_statistics){
            displayDatabase();
        }

        return super.onOptionsItemSelected(item);
    }
    public void displayDatabase(){
        Intent dbmanager = new Intent(getApplicationContext(),AndroidDatabaseManager.class);
        startActivity(dbmanager);
    }
}
