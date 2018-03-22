package com.example.christanismerilbanzouzi.bance_project;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
     private static int SPLASH_TIME_OUT=3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
                );
        setContentView(R.layout.activity_main);
         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 //Intent homreIntent = new Intent(MainActivity.this,Authentification.class);
                 Intent homreIntent = new Intent(MainActivity.this,HomeActivity.class);
                 startActivity(homreIntent);
                 finish();
             }
         },SPLASH_TIME_OUT);


    }
}
