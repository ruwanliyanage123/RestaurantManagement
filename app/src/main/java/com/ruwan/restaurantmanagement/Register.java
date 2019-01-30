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

public class Register extends AppCompatActivity {

    EditText Username, Name, Password;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Username = (EditText)findViewById(R.id.Username);
        Password = (EditText)findViewById(R.id.Password);
        Name = (EditText)findViewById(R.id.Name);

        signUp   = (Button)findViewById(R.id.signUp);

        //init firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(Register.this);
                mDialog.setMessage("witing...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //check if  already exists

                        if(dataSnapshot.child(Username.getText().toString()).exists()){

                            mDialog.dismiss();
                            Toast.makeText(Register.this, "already exitss", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            mDialog.dismiss();

                            User user = new User(Username.getText().toString(), Password.getText().toString());
                            table_user.child(Name.getText().toString()).setValue(user);

                            Toast.makeText(Register.this, "CONGRATULATIONS!", Toast.LENGTH_SHORT).show();
                            finish();
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
