package com.josephair.sqlite;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import sqliteHelper.DatabaseAdapter;


public class MainActivity extends Activity {

    EditText userName, password;

    DatabaseAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Linking EditText to XML
         userName = (EditText)findViewById(R.id.userName);
         password =(EditText)findViewById(R.id.userPassword);
        helper = new DatabaseAdapter(this);

    }


    //Button onClick function in XML to Add user
    public  void AddUser(View view){

        String user = userName.getText().toString();
        String pass= password.getText().toString();
        long id = helper.insertData(user,pass);
        if(id <0){
            //Something went wrong
            Message.message(this, "Insertion was not successful");

        }else{
            //successful Insertion in to database
            Message.message(this, "Insertion was  successful");
        }

    }
    //Button Onclick to View Details
    public void viewDetails(View view) {
        //To display message in to
       String data= helper.getAllData();
      Message.message(this,data);


    }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
