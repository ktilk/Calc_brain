package com.example.kaspartilk.calc_brain;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        displayOperatorsListView();

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
        OperationsAdapter adapter = new OperationsAdapter(this, uow.operationRepo.getCursorAll(), uow);
        listView.setAdapter(adapter);
    }

    private void displayStatisticsListView(){
        String s = "Daily statistics";
        textView.setText(s);
        StatisticsAdapter adapter = new StatisticsAdapter(this, uow.statisticsRepo.getCursorAll(), uow);
        listView.setAdapter(adapter);
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
            displayOperationsListView();
        }else if (id == R.id.action_day_statistics){
            displayStatisticsListView();
        }else if (id == R.id.action_delete_statistics){
            deleteClicked();
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteClicked() {
        new AlertDialog.Builder(this)
                .setTitle("Emptying database")
                .setMessage("Really delete database?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        uow.dropCreateDatabase();
                        Toast.makeText(MainActivity.this, "Database emptied", Toast.LENGTH_SHORT).show();
                        //refreshActivity();
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    //code from https://github.com/sanathp/DatabaseManager_For_Android
    public void displayDatabase(){
        Intent dbmanager = new Intent(getApplicationContext(),AndroidDatabaseManager.class);
        startActivity(dbmanager);
    }
    @Override
    protected void onResume() {
        super.onResume();
        displayOperatorsListView();
    }
}
