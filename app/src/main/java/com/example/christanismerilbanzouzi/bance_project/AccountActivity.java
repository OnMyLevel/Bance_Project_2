package com.example.christanismerilbanzouzi.bance_project;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.christanismerilbanzouzi.bance_project.Common.Common;
import com.example.christanismerilbanzouzi.bance_project.Fragments.AdresseFragment;
import com.example.christanismerilbanzouzi.bance_project.Fragments.CarteCBFragment;

public class AccountActivity extends AppCompatActivity {

    private Toolbar myToolBar;
    TextView currentUser;
    TextView commandeUser;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        myToolBar =findViewById(R.id.toolbar);
        setSupportActionBar(myToolBar);
        currentUser= (TextView) findViewById(R.id.currentuser);
        currentUser.setText(Common.currentUser.getName().toString());
        commandeUser = (TextView) findViewById(R.id.nbcommande);
        commandeUser.setText("Nombre de commande Actuellement:"+Common.currentUser.getNombreCmd());
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
            Intent sartItent = new Intent(getApplicationContext(),ShopActivity.class);
            startActivity(sartItent);
        }
        else if (item.getItemId() == R.id.action_home){
            Log.i("Action_Home", "In Other Options ");
            Intent sartItent = new Intent(getApplicationContext(),Authentification.class);
            startActivity(sartItent);
        }
        else if (item.getItemId() == R.id.action_droit){
            Log.i("Action_Droi", "In Droits");
            Intent sartItent = new Intent(getApplicationContext(),Droit.class);
            startActivity(sartItent);
        }
        else if (item.getItemId() == R.id.action_shop){
            Log.i("Action_Shop", "In action Shop");
            Intent sartItent = new Intent(getApplicationContext(), CaddyActivity.class);
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

    public  void ChangeFragment(View view){

        if(view == findViewById(R.id.adressactuel)){
            Log.i("fragment", "ChangeFragment: 1");
            fragment = new AdresseFragment();
            FragmentManager fm= getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_commande,fragment);
            ft.commit();
        }

        if(view == findViewById(R.id.cartebancaire)){
            Log.i("fragment", "ChangeFragment: 2");
            fragment = new CarteCBFragment();
            FragmentManager fm= getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.fragment_commande,fragment);
            ft.commit();
        }

    }

    @SuppressLint("ResourceType")
    public  void openActualCommandeFragment(String texte){
        Log.i("fragment", "ChangeFragment: 1");
        fragment = AdresseFragment.newInstance(texte);
        fragment = new CarteCBFragment();
        FragmentManager fm= getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.exi_to_right,R.anim.exi_to_right,R.anim.exi_to_right,R.anim.exi_to_right);

    }

    @SuppressLint("ResourceType")
    public  void openRecentCommandeFragment(String texte){
        Log.i("fragment", "ChangeFragment: 2");
        fragment = CarteCBFragment.newInstance(texte);
        fragment = new CarteCBFragment();
        FragmentManager fm= getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.exi_to_right,R.anim.exi_to_right,R.anim.exi_to_right,R.anim.exi_to_right);
        ft.addToBackStack(null);
        ft.replace(R.id.fragment_commande,fragment);
        ft.commit();
    }


}
