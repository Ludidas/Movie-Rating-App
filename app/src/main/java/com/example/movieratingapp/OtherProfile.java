package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class OtherProfile extends AppCompatActivity
{
    DatabaseHelper dbHelper;
    ReviewListAdapter adapter;
    ArrayList<Reviews> reviewList;
    ArrayList<Reviews> filteredReviews;
    Users userPassed;

    Intent homePageIntent;
    Intent profileIntent;
    Intent userSearchIntent;

    TextView op_tv_j_username;
    ImageView op_iv_j_movieSearch;
    ImageView op_iv_j_profile;
    ImageView op_iv_j_userSearch;
    ListView op_lv_j_reviews;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);

        op_tv_j_username=findViewById(R.id.op_tv_v_username);
        op_iv_j_movieSearch=findViewById(R.id.op_iv_v_movieSearch);
        op_iv_j_profile=findViewById(R.id.op_iv_v_profile);
        op_iv_j_userSearch=findViewById(R.id.op_iv_v_userSearch);
        op_lv_j_reviews=findViewById(R.id.op_lv_v_reviews);

        homePageIntent=new Intent(OtherProfile.this, HomePage.class);
        profileIntent=new Intent(OtherProfile.this, UserProfile.class);
        userSearchIntent=new Intent(OtherProfile.this, UserSearch.class);

        dbHelper=new DatabaseHelper(this);

        //Get user from UserSearch
        Intent cameFrom = getIntent();
        userPassed = (Users) cameFrom.getSerializableExtra("USER");

        reviewList=new ArrayList<Reviews>();
        filteredReviews=new ArrayList<Reviews>();
        dbHelper=new DatabaseHelper(this);
        reviewList = dbHelper.getAllReviews();

        // Filter reviews based on the username
        for (Reviews review : reviewList)
        {
            if (review.getUserId().equals(userPassed.getUname()))
            {
                filteredReviews.add(review);
            }
        }


        fillListView();
        movieSearchButtonEvent();
        profileSearchButtonEvent();
        userSearchButtonEvent();
    }

    public void fillListView()
    {
        // Create and set the adapter with the filtered reviews
        adapter = new ReviewListAdapter(this, filteredReviews);
        op_lv_j_reviews.setAdapter(adapter);
    }

    //BUTTON EVENTS===============================================================================
    public void movieSearchButtonEvent()
    {
        op_iv_j_movieSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(homePageIntent);
            }
        });
    }

    public void profileSearchButtonEvent()
    {
        op_iv_j_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(profileIntent);
            }
        });
    }

    public void userSearchButtonEvent()
    {
        op_iv_j_userSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(userSearchIntent);
            }
        });
    }
}