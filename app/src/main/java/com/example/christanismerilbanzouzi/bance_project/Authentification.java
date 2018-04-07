package com.example.christanismerilbanzouzi.bance_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.christanismerilbanzouzi.bance_project.Common.Common;
import com.example.christanismerilbanzouzi.bance_project.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Authentification extends AppCompatActivity {

    Button btnSignIn,btnSignUp;
    EditText editId,editPassword;
    public  static  final  String EXTRA_USER_NAME ="Image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        editId = (EditText) findViewById(R.id.editId);
        editPassword = (EditText) findViewById(R.id.editPassword);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);


        //initFireBase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference("User");
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("cliker", "onClick: ");
                table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        //Check if user not exits in database
                        Log.i("test BDD", ""+dataSnapshot.child(editId.getText().toString()).exists() );

                        if(dataSnapshot.child(editId.getText().toString()).exists()){
                            //get user infotmation
                            User userTmp = dataSnapshot.child(editId.getText().toString()).getValue(User.class);
                            if (userTmp.getPassword().equals(editPassword.getText().toString())) {

                                Toast.makeText(Authentification.this, "Sign in successfully",
                                        Toast.LENGTH_SHORT).show();
                                startHomeIntent(userTmp);
                                finish();
                            } else {
                                Toast.makeText(Authentification.this, "Wrong password !!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Authentification.this, "User not exit in DataBase",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    private void startHomeIntent(User userTmp) {
        // si l'user c'est connecté à l'application:
        Intent detailsIntent = new Intent(this,HomeActivity.class);
        detailsIntent.putExtra(EXTRA_USER_NAME,userTmp.getName());
        //Intent sartItent = new Intent(getApplicationContext(),HomeActivity.class);
        Common.currentUser= userTmp;
        startActivity(detailsIntent);
    }


}
