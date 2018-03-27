package com.example.christanismerilbanzouzi.bance_project;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.storage.StorageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.christanismerilbanzouzi.bance_project.Model.Article;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import static com.example.christanismerilbanzouzi.bance_project.CaddyActivity.EXTRA_DESC;
import static com.example.christanismerilbanzouzi.bance_project.CaddyActivity.EXTRA_NAME;
import static com.example.christanismerilbanzouzi.bance_project.CaddyActivity.EXTRA_PRICE;
import static com.example.christanismerilbanzouzi.bance_project.CaddyActivity.EXTRA_URL;

public class Detailsctivity extends AppCompatActivity {

    ImageView imageView;
    TextView prix;
    TextView nameAcrticle;
    TextView desc;
    TextView textView;
    Button btn_ajouter;
    String imageUri;

    private Toolbar myToolBar;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsctivity);

        myToolBar =findViewById(R.id.toolbar);
        setSupportActionBar(myToolBar);

        Intent intent = getIntent();
        String image = intent.getStringExtra(EXTRA_URL);
        String name = intent.getStringExtra(EXTRA_NAME);
        String price = intent.getStringExtra(EXTRA_PRICE);
        String descString = intent.getStringExtra(EXTRA_DESC);

        imageView = findViewById(R.id.image_details);
        prix = (TextView) findViewById(R.id.price_article);
        desc= (TextView) findViewById(R.id.description_article);
        nameAcrticle = (TextView) findViewById(R.id.name_article);

        mStorageRef = FirebaseStorage.getInstance().getReference("Article");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Article");

        imageUri = image;

        prix.setText(price+"$");
        nameAcrticle.setText(name);
        desc.setText(descString);

        Picasso.with(this).load(image).fit().centerInside().into(imageView);
        textView = (TextView) findViewById(R.id.text_titleId);
        Typeface myCust= Typeface.createFromAsset(getAssets(),"fonts/A_Box_For.ttf");
        textView.setTypeface(myCust);

        btn_ajouter = (Button) findViewById(R.id.btn_ajouter);

        btn_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("IN ONCLIECKLISTENER", "onClick: ");
                inserDataArtcile();
            }
        });



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

    private  String getFileExtension(Uri uri){
        Log.i("GETFILEENTENSION", "getFileExtension: ");
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void inserDataArtcile(){

        if( (nameAcrticle.getText() != null || nameAcrticle.getText() != "") &&
                (prix.getText() != null || prix.getText() != "") &&
                (imageUri !=null || imageUri!="") ){
            Log.i("IN THE IF", "inserDataArtcile: ");
            Article upload = new Article(nameAcrticle.getText().toString(),imageUri.toString(),prix.toString());
            String uploadId = mDatabaseRef.push().getKey();
            mDatabaseRef.child(uploadId).setValue(upload);
            Toast.makeText(this," Ajouter au Pannier ",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this,"no file for image ",Toast.LENGTH_SHORT).show();
        }

    }
}
