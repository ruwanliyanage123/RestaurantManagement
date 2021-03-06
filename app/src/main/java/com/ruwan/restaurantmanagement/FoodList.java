package com.ruwan.restaurantmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ruwan.restaurantmanagement.Interface.ItemClickListener;
import com.ruwan.restaurantmanagement.Model.Food;
import com.ruwan.restaurantmanagement.ViewHolder.FoodViewHolder;
import com.squareup.picasso.Picasso;

public class FoodList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference foodList;
    String categoryId = "";
    FirebaseRecyclerAdapter<Food,FoodViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        //firebase
        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Food");

        recyclerView =(RecyclerView)findViewById(R.id.recycler_food);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //get intent here
        if(getIntent()!= null){
            categoryId = getIntent().getStringExtra("CategoryId");
//            final ProgressDialog mDialog = new ProgressDialog(FoodList.this);
//            mDialog.setMessage("category is........"+categoryId);
//            mDialog.show();

        }
        if((!categoryId.isEmpty())&&(categoryId!=null)){
//            final ProgressDialog mDialog = new ProgressDialog(FoodList.this);
//            mDialog.setMessage("this is the second way");
//            mDialog.show();
            loadListFood(categoryId);
        }
    }

    private void loadListFood(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,
                R.layout.food_item,
                FoodViewHolder.class,
                foodList.orderByChild("MenuId").equalTo(categoryId)
                /**
                    this like
                 select*from Foods
                 where MenuId = categoryId


                 */

                ) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, Food model, int position) {
                viewHolder.food_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.food_image);
                final Food local = model;
                viewHolder.setItemClickListener(new ItemClickListener(){
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        //start new activity
                        Intent foodDetail = new Intent(FoodList.this, FoodDetails.class);
                        foodDetail.putExtra("MenuId",adapter.getRef(position).getKey());//send food id to new activity
                        startActivity(foodDetail);
                    }
                });
            }
        };
        //set adapter
        //Log.d("TAG",""+adapter.getItemCount());
        recyclerView.setAdapter(adapter);
    }
}
