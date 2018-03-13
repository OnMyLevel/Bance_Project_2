package com.example.christanismerilbanzouzi.bance_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class CaddyActivity extends AppCompatActivity {

    private Toolbar myToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caddy);
        myToolBar =findViewById(R.id.toolbar);
        setSupportActionBar(myToolBar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if( item.getItemId() == R.id.action_account){
            Log.i("Action_Account", "In action Account ");
            Intent sartItent = new Intent(getApplicationContext(),AccountActivity.class);
            startActivity(sartItent);

        }
        else if (item.getItemId() == R.id.action_caddy){
            Log.i("Action_Caddy", "In action Caddy ");
            Intent sartItent = new Intent(getApplicationContext(),CaddyActivity.class);
            startActivity(sartItent);
        }
        else if (item.getItemId() == R.id.action_shop){
            Log.i("Action_Shop", "In action Shop");
            Intent sartItent = new Intent(getApplicationContext(),ShopActivity.class);
            startActivity(sartItent);
        }
        else{
            Log.i("Action_Home", "In Other Options ");
            Intent sartItent = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(sartItent);

        }
        return true;
    }
}
