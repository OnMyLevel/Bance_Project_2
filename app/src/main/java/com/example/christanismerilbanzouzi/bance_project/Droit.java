package com.example.christanismerilbanzouzi.bance_project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.christanismerilbanzouzi.bance_project.Common.Common;
import com.example.christanismerilbanzouzi.bance_project.service.GetDroit;

import static android.support.v4.content.LocalBroadcastManager.getInstance;
import static com.example.christanismerilbanzouzi.bance_project.R.id.toolbar;

public class Droit extends AppCompatActivity {

    public static final String DROITS_UPDATE="com.example.christanismerilbanzouzi.bance_project.action.FOO";
    IntentFilter intentFilter;
    TextView rv;
    String text;
    Intent intent;
    private Toolbar myToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i("Texte", "onCreate: DROIT");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_droit);
        myToolBar = findViewById(toolbar);
        setSupportActionBar(myToolBar);
        GetDroit.startActionGetDroit(this);
        this.intentFilter = new IntentFilter((DROITS_UPDATE));
        getInstance(Droit.this).registerReceiver(new DroitUpdate(), intentFilter);
    }

    public class DroitUpdate extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent){
            rv = findViewById(R.id.textDroit);
            if( Common.droits !=null &&  Common.droits!=""){
                rv.setText(Common.droits.toString());
            }
            Toast.makeText(Droit.this, "Téléchargement terminé", Toast.LENGTH_SHORT).show();
        }
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
        else if (item.getItemId() == R.id.action_shop){
            Log.i("Action_Caddy", "In action Caddy ");
            Intent sartItent = new Intent(getApplicationContext(),CaddyActivity.class);
            startActivity(sartItent);
        }
        else if (item.getItemId() == R.id.action_caddy){
            Log.i("Action_Caddy", "In action Caddy ");
            Intent sartItent = new Intent(getApplicationContext(),ShopActivity.class);
            startActivity(sartItent);
        }
        else if (item.getItemId() == R.id.action_droit){
            Log.i("Action_Droi", "In Droits");
            Intent sartItent = new Intent(getApplicationContext(),Droit.class);
            startActivity(sartItent);
        }
        else if (item.getItemId() == R.id.action_home){
            Log.i("Action_Home", "In Other Options ");
            Intent sartItent = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(sartItent);
        }
        else if (item.getItemId() == R.id.action_location){
            Log.i("Action_Home", " In Action Location ");
            String googleAdress = "https://www.google.fr/maps/search/pull+and/@48.848155,2.1814866,12z/data=!3m1!4b1";
            Uri webbadress = Uri.parse(googleAdress);
            Intent goToLocation = new Intent(Intent.ACTION_VIEW, webbadress);
            if(goToLocation.resolveActivity(getPackageManager())!=null){
                startActivity(goToLocation);
            }
        }
        return true;
    }
}
