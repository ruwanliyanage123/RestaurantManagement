package com.ruwan.restaurantmanagement;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ruwan.restaurantmanagement.Model.User;

import java.util.jar.Attributes;

public class SignIn extends AppCompatActivity {

    EditText Username, Password;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Username = (EditText)findViewById(R.id.Username);
        Password = (EditText)findViewById(R.id.Password);
        signIn   = (Button)findViewById(R.id.signIn);


        //init firebase

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");



        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("witing...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //check user if exists in database

                        if(dataSnapshot.child(Username.getText().toString()).exists()) {
                            //ger user information

                            mDialog.dismiss();
                            User user = dataSnapshot.child(Username.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(Password.getText().toString())) {
                                Toast.makeText(SignIn.this, "congratulations", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignIn.this, "sorry try again", Toast.LENGTH_SHORT).show();

                            }
                        }

                        else {
                            Toast.makeText(SignIn.this, "user not registered", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }

}









