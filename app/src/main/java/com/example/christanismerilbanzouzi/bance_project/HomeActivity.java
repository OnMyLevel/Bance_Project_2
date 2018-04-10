package com.example.christanismerilbanzouzi.bance_project;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.christanismerilbanzouzi.bance_project.Common.Common;
import com.example.christanismerilbanzouzi.bance_project.Popup.Popup;

import static com.example.christanismerilbanzouzi.bance_project.Authentification.EXTRA_USER_NAME;

public class HomeActivity extends AppCompatActivity {

    private Toolbar myToolBar;
    //private DatabaseHelper mysDb;
    private TextView currentUser;
    private VideoView videoView;
    private  TextView textNews;
    public  static  final  String EXTRA_USERNAME="Image";

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myToolBar =findViewById(R.id.toolbar);
        setSupportActionBar(myToolBar);
        myToolBar.setTitle("Home");
        currentUser = (TextView) findViewById(R.id.currentuser);
        Intent intent = getIntent();
        String name = intent.getStringExtra(EXTRA_USER_NAME);

        if(Common.Pop == true) {

            Log.i("NAME USER", "USER NAME"+name.toString());
            Intent popIntent = new Intent(this,Popup.class);
            popIntent.putExtra(EXTRA_USER_NAME,name);
            startActivity(popIntent);
        }
        textNews = (TextView) findViewById(R.id.titleNews);
        Typeface myCust= Typeface.createFromAsset(getAssets(),"fonts/A_Box_For.ttf");
        textNews.setTypeface(myCust);

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
        else if (item.getItemId() == R.id.action_deconexion){
            Log.i("Action_Home", "In Other Options ");
            Intent sartItent = new Intent(getApplicationContext(),Authentification.class);
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
