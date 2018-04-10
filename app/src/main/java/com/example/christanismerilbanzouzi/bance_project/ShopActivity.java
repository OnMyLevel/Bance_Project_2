package com.example.christanismerilbanzouzi.bance_project;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.christanismerilbanzouzi.bance_project.Common.Common;
import com.example.christanismerilbanzouzi.bance_project.Interface.ItemClickListener;
import com.example.christanismerilbanzouzi.bance_project.Model.Article;
import com.example.christanismerilbanzouzi.bance_project.ViewHolder.ArticleViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import static com.example.christanismerilbanzouzi.bance_project.R.id.action_account;
import static com.example.christanismerilbanzouzi.bance_project.R.id.action_caddy;
import static com.example.christanismerilbanzouzi.bance_project.R.id.action_home;
import static com.example.christanismerilbanzouzi.bance_project.R.id.action_location;
import static com.example.christanismerilbanzouzi.bance_project.R.id.action_shop;
import static com.example.christanismerilbanzouzi.bance_project.R.id.myrecyclerView;
import static com.example.christanismerilbanzouzi.bance_project.R.id.recycler_article_image;
import static com.example.christanismerilbanzouzi.bance_project.R.id.recycler_article_name;
import static com.example.christanismerilbanzouzi.bance_project.R.id.recycler_article_price;
import static com.example.christanismerilbanzouzi.bance_project.R.id.toolbar;

public class ShopActivity extends AppCompatActivity {

    private Toolbar myToolBar;
    DatabaseReference article;
    RecyclerView recycler_article;
    RecyclerView.LayoutManager layoutManager;
    TextView titrePannier,prixTotal;
    Button  commander;
    private NotificationManager notifManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        myToolBar = findViewById(toolbar);
        setSupportActionBar(myToolBar);

        // Init dataBase
        article = FirebaseDatabase.getInstance().getReference().child("Article");
        Log.i("Test", "onCreate: " + article.toString());
        article.keepSynced(true);

        //Load Menu
        recycler_article = (RecyclerView) findViewById(myrecyclerView);
        recycler_article.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_article.setLayoutManager(layoutManager);
        titrePannier = (TextView) findViewById(R.id.titrePannier);
        prixTotal = (TextView) findViewById(R.id.totalPrice);
        prixTotal.setText("Prix Total:"+Common.total+"$");
        Log.i("TOTAL", "TOTAL"+Common.total);
        Typeface myCust = Typeface.createFromAsset(getAssets(), "fonts/A_Box_For.ttf");
        titrePannier.setTypeface(myCust);
        commander = (Button) findViewById(R.id.commander);
        commander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(ShopActivity.this)
                        .setTitle("Comfirmation ?")
                        .setMessage(" Vouslez vou comfirmer votre comande ? ")
                        .setPositiveButton("OUI ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Common.currentUser.setNombreCmd(Common.currentUser.getNombreCmd()+1);
                                notificationSet();
                                finish();
                            }
                        })
                        .setNegativeButton("NON", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(ShopActivity.this, " Votre Commande a été Annuler ", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .show();
            }
        });
        /*onLoad();*/
    }


    private void notificationSet(){

        final int NOTIFY_ID = 1002;
        // There are hardcoding only for show it's just strings
        String name = "my_package_channel";
        String id = "my_package_channel_1"; // The user-visible name of the channel.
        String description = "my_package_first_channel"; // The user-visible description of the channel.
        Intent intent;
        PendingIntent pendingIntent;
        NotificationCompat.Builder builder;

        if (notifManager == null) {
            notifManager =
                    (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        }

        String aMessage=" VOTRE COMMANDE  A ETE ENVOYER ";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = notifManager.getNotificationChannel(id);
            if (mChannel == null) {
                mChannel = new NotificationChannel(id, name, importance);
                mChannel.setDescription(description);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                notifManager.createNotificationChannel(mChannel);
            }
            builder = new NotificationCompat.Builder(this, id);
            intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            builder.setContentTitle(aMessage)  // required
                    .setSmallIcon(R.drawable.caddy) // required
                    .setContentText(this.getString(R.string.app_name))  // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(aMessage)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        } else {

            builder = new NotificationCompat.Builder(this);
            intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            builder.setContentTitle(aMessage)                           // required
                    .setSmallIcon(R.drawable.caddy) // required
                    .setContentText(this.getString(R.string.app_name))  // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(aMessage)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                    .setPriority(Notification.PRIORITY_HIGH);
        }
        Notification notification = builder.build();
        notifManager.notify(NOTIFY_ID, notification);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main,menu);
        return true;
    }

    public static  class ArticleViewHolders extends  RecyclerView.ViewHolder{
        View mView;
        public ArticleViewHolders(View itemView) {
            super(itemView);
            mView=itemView;
        }
        public void setName(String name){
            TextView article_name= (TextView) mView.findViewById(recycler_article_name);
            article_name.setText(name);
        }
        public void setImage(Context ctx,String image){
            ImageView article_image= (ImageView) mView.findViewById(recycler_article_image);
            Picasso.with(ctx).load(image).into(article_image);
        }
        public void setPrice(String price){
            TextView article_price= (TextView) mView.findViewById(recycler_article_price);
            article_price.setText(price);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if( item.getItemId() == action_account){
            Log.i("Action_Account", "In action Account ");
            Intent sartItent = new Intent(getApplicationContext(),AccountActivity.class);
            startActivity(sartItent);
        }
        else if (item.getItemId() == action_caddy){
            Log.i("Action_Caddy", "In action Caddy ");
            Intent sartItent = new Intent(getApplicationContext(),ShopActivity.class);
            startActivity(sartItent);
        }
        else if (item.getItemId() == R.id.action_droit){
            Log.i("Action_Droi", "In Droits");
            Intent sartItent = new Intent(getApplicationContext(),Droit.class);
            startActivity(sartItent);
        }
        else if (item.getItemId() == action_shop){
            Log.i("Action_Shop", "In action Shop");
            Intent sartItent = new Intent(getApplicationContext(),CaddyActivity.class);
            startActivity(sartItent);
        }
        else if (item.getItemId() == R.id.action_deconexion){
            Log.i("Action_Home", "In Other Options ");
            Intent sartItent = new Intent(getApplicationContext(),Authentification.class);
            startActivity(sartItent);
        }
        else if (item.getItemId() == action_home){
            Log.i("Action_Home", "In Other Options ");
            Intent sartItent = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(sartItent);
        }
        else if (item.getItemId() == action_location){
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
    protected void onStart() {
        super.onStart();
        Log.i("LOG", "onStart: DEBUT ");
        FirebaseRecyclerAdapter<Article,ArticleViewHolders> adapter = new FirebaseRecyclerAdapter<Article,ArticleViewHolders>
                (Article.class,R.layout.article_item,ArticleViewHolders.class,article){
                    @Override
                    protected void populateViewHolder(ArticleViewHolders viewHolder, Article model, int position) {
                        Log.i("TEST", "populateViewHolder: "+model.toString());
                        viewHolder.setName(model.getName());
                        viewHolder.setPrice(String.valueOf(model.getPrice()));
                        Common.total+=Integer.valueOf(model.getPrice());
                        viewHolder.setImage(getApplicationContext(),model.getImage());
                    }
                };
        Log.i("TEST", "onStart: END");
        recycler_article.setAdapter(adapter);
    }

    public void onLoad(){
        Log.i("LOG", "loadArticle: DEBUT ");
        FirebaseRecyclerAdapter<Article,ArticleViewHolder> adapter =
                new FirebaseRecyclerAdapter<Article,ArticleViewHolder>(Article.class,R.layout.article_item,
                        ArticleViewHolder.class,article){
                    @Override
                    protected void populateViewHolder(ArticleViewHolder viewHolder, Article model, int position) {
                        Log.i("TEST", "populateViewHolder: ");
                        viewHolder.textArticleView.setText(model.getName());
                        Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.imageView);
                        final Article clickItem = model;
                        viewHolder.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onClick(View view, int position, boolean isLongClick) {
                                Log.i("TEST", "onClick: ");
                                Toast.makeText(ShopActivity.this,""+clickItem.getName(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                };
        Log.i("TEST", "loadArticle: END"+adapter.toString());
        recycler_article.setAdapter(adapter);
    }
}
