package com.ruwan.restaurantmanagement;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ruwan.restaurantmanagement.Model.Food;
import com.ruwan.restaurantmanagement.ViewHolder.FoodViewHolder;
import com.squareup.picasso.Picasso;

/**/public class FoodDetails extends AppCompatActivity {

    TextView food_name, food_price, food_description;
    ImageView food_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton  btnCart;
    Button numberButton;
    /**/RecyclerView recyclerView;
    /***/RecyclerView.LayoutManager layoutManager;


    FirebaseDatabase database;
    DatabaseReference foods;
    String foodId = "";
    FirebaseRecyclerAdapter<Food,FoodViewHolder> adapter;


    /**/@Override
    /**/protected void onCreate(Bundle savedInstanceState){
    /**/super.onCreate(savedInstanceState);
    /**/setContentView(R.layout.activity_food_details);


        //firebase
        database = FirebaseDatabase.getInstance();
        foods = database.getReference("Food");

        //init view
        numberButton = (Button) findViewById(R.id.number_button);
        btnCart  = (FloatingActionButton)findViewById(R.id.btnCart);
        food_description = (TextView)findViewById(R.id.food_description);
        food_name = (TextView)findViewById(R.id.food_name);
        food_price= (TextView)findViewById(R.id.food_price);
        food_image = (ImageView) findViewById(R.id.img_food);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);
        
        
        //get food id from intent
        if(getIntent()!=null){
            foodId  = getIntent().getStringExtra("MenuId");
//            final ProgressDialog mDialog = new ProgressDialog(FoodDetails.this);
//            mDialog.setMessage("category is........"+foodId);
//            mDialog.show();

        }
        
        if(!foodId.isEmpty()){
            getDetailFood(foodId);
        }

    /**/}

    private void getDetailFood(String foodId){
        foods.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Food food = dataSnapshot.getValue(Food.class);

                //set image
                Picasso.with(getBaseContext()).load(food.getImage()).into(food_image);

                collapsingToolbarLayout.setTitle(food.getName());
                food_price.setText(food.getPrice());
                food_name.setText(food.getName());
                food_description.setText(food.getDescription());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

/**/}

