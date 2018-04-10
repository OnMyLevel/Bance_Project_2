package com.example.christanismerilbanzouzi.bance_project;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.christanismerilbanzouzi.bance_project.Adapter.ArticleLongAdapter;
import com.example.christanismerilbanzouzi.bance_project.Model.ArticleLong;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CaddyActivity extends AppCompatActivity implements ArticleLongAdapter.OnItemClickListener {

    public  static  final  String EXTRA_URL ="Image";
    public  static  final  String EXTRA_NAME ="Name";
    public  static  final  String EXTRA_PRICE ="Price";
    public  static  final  String EXTRA_DESC="Description";
    private Toolbar myToolBar;
    private RecyclerView recyclerView;
    private ArticleLongAdapter articleLongAdapter;
    private ArrayList<ArticleLong> myTable;
    private RequestQueue myRequest;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caddy);
        myToolBar =findViewById(R.id.toolbar);
        setSupportActionBar(myToolBar);

        recyclerView = (RecyclerView) findViewById(R.id.myrecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        myTable= new ArrayList<>();
        myRequest = Volley.newRequestQueue(this);
        parseJson();
        textView = (TextView) findViewById(R.id.text_gallerie);
        Typeface myCust= Typeface.createFromAsset(getAssets(),"fonts/A_Box_For.ttf");
        textView.setTypeface(myCust);

    }

    private void parseJson() {

        String url="http://banzouzicmeril.fr/FichierJson/RestJson.json";
        JsonArrayRequest request= new JsonArrayRequest(Request.Method.GET,url,null,
            new Response.Listener<JSONArray>(){

                @Override
                public void onResponse(JSONArray response) {
                    Log.i("REP", "onResponse: "+response);
                    try {
                        JSONArray JsonArray = (JSONArray)response;
                        for (int i=0; i < JsonArray.length();i++){

                            JSONObject article = JsonArray.getJSONObject(i);
                            String name = article.getString("Name");
                            String imageUrl= article.getString("Image");
                            String price =   article.getString("Price");
                            String description = article.getString("Description");
                            String  discount = article.getString("Discount");
                            String categorieId = article.getString("CategorieId");
                            myTable.add( new ArticleLong( name,imageUrl, price,description, categorieId,discount));
                        }
                        articleLongAdapter = new ArticleLongAdapter(CaddyActivity.this,myTable);
                        recyclerView.setAdapter(articleLongAdapter);
                        articleLongAdapter.setOnItemClickListener(CaddyActivity.this);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );
          myRequest.add(request);
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
            Intent sartItent = new Intent(getApplicationContext(),ShopActivity.class );
            startActivity(sartItent);
        }
        else if (item.getItemId() == R.id.action_shop){
            Log.i("Action_Shop", "In action Shop");
            Intent sartItent = new Intent(getApplicationContext(),CaddyActivity.class);
            startActivity(sartItent);
        }
        else if (item.getItemId() == R.id.action_droit){
            Log.i("Action_Droi", "In Droits");
            Intent sartItent = new Intent(getApplicationContext(),Droit.class);
            startActivity(sartItent);
        }
        else if (item.getItemId() == R.id.action_deconexion){
            Log.i("Action_Home", "In Other Options ");
            Intent sartItent = new Intent(getApplicationContext(),Authentification.class);
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

    @Override
    public void onItemClick(int position) {

        Intent detailsIntent = new Intent(this,Detailsctivity.class);
        ArticleLong clickitem =   myTable.get(position);
        detailsIntent.putExtra(EXTRA_URL,clickitem.getImage());
        detailsIntent.putExtra(EXTRA_NAME,clickitem.getName());
        detailsIntent.putExtra(EXTRA_PRICE,clickitem.getPrice());
        detailsIntent.putExtra(EXTRA_DESC,clickitem.getDescription());
        startActivity(detailsIntent);
    }
}
