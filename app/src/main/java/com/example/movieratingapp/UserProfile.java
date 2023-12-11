package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserProfile extends AppCompatActivity
{
    DatabaseHelper dbHelper;
    ReviewListAdapter adapter;
    ArrayList<Reviews> reviewList;
    ArrayList<Reviews> filteredReviews;

    Intent homePageIntent;
    Intent profileIntent;
    Intent userSearchIntent;
    Intent mainIntent;
    Intent reviewInfoIntent;


    TextView up_tv_j_username;
    ImageView up_iv_j_settings;
    ImageView up_iv_j_settingsBox;
    ImageView up_iv_j_signOut;
    ImageView up_iv_j_deleteAcc;
    ImageView up_iv_j_deletePrompt;
    ImageView up_iv_j_yesDelete;
    ImageView up_iv_j_noDelete;
    ImageView up_iv_j_movieSearch;
    ImageView up_iv_j_profile;
    ImageView up_iv_j_userSearch;
    ListView up_lv_j_reviews;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        up_tv_j_username=findViewById(R.id.up_tv_v_username);
        up_iv_j_settings=findViewById(R.id.up_iv_v_settings);
        up_iv_j_settingsBox=findViewById(R.id.up_iv_v_settingsBox);
        up_iv_j_signOut=findViewById(R.id.up_iv_v_signOut);
        up_iv_j_deleteAcc=findViewById(R.id.up_iv_v_deleteAcc);
        up_iv_j_deletePrompt=findViewById(R.id.up_iv_v_deletePrompt);
        up_iv_j_yesDelete=findViewById(R.id.up_iv_v_yesDelete);
        up_iv_j_noDelete=findViewById(R.id.up_iv_v_noDelete);
        up_iv_j_movieSearch=findViewById(R.id.up_iv_v_movieSearch);
        up_iv_j_profile=findViewById(R.id.up_iv_v_profile);
        up_iv_j_userSearch=findViewById(R.id.up_iv_v_userSearch);
        up_lv_j_reviews=findViewById(R.id.up_lv_v_reviews);

        homePageIntent=new Intent(UserProfile.this, HomePage.class);
        profileIntent=new Intent(UserProfile.this, UserProfile.class);
        userSearchIntent=new Intent(UserProfile.this, UserSearch.class);
        mainIntent=new Intent(UserProfile.this, MainActivity.class);
        reviewInfoIntent=new Intent(UserProfile.this, ReviewInfo.class);

        reviewList=new ArrayList<Reviews>();
        filteredReviews=new ArrayList<Reviews>();
        dbHelper=new DatabaseHelper(this);
        reviewList = dbHelper.getAllReviews();


        // Filter reviews based on the username
        for (Reviews review : reviewList)
        {
            if (review.getUserId().equals(AppData.getUsername()))
            {
                filteredReviews.add(review);
            }
        }


        setUsernameText();
        fillListView();
        reviewSelectedEvent();
        settingsButtonEvent();
        deleteAccountButtonEvent();
        yesDeleteButtonEvent();
        noDeleteButtonEvent();
        signOutButtonEvent();
        movieSearchButtonEvent();
        profileButtonEvent();
        userSearchButtonEvent();
    }

    //=====================================================================================
    public void setUsernameText()
    {
        up_tv_j_username.setText(AppData.getUsername());
    }
    public void fillListView()
    {
        // Create and set the adapter with the filtered reviews
        adapter = new ReviewListAdapter(this, filteredReviews);
        up_lv_j_reviews.setAdapter(adapter);
    }

    //BUTTON EVENTS===============================================================================

    public void reviewSelectedEvent()
    {
        up_lv_j_reviews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id)
            {
                reviewInfoIntent.putExtra("REVIEW", filteredReviews.get(i));
                startActivity(reviewInfoIntent);
            }
        });
    }

    public void settingsButtonEvent()
    {
        up_iv_j_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //If settings box is visible, set everything as "GONE" when pressed, otherwise set it visible.
                if(up_iv_j_settingsBox.getVisibility()==View.VISIBLE)
                {
                    clearSettings();
                }
                else
                {
                    up_iv_j_settingsBox.setVisibility(View.VISIBLE);
                    up_iv_j_signOut.setVisibility(View.VISIBLE);
                    up_iv_j_deleteAcc.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void deleteAccountButtonEvent()
    {
        up_iv_j_deleteAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Set first-layer settings buttons to GONE so they can't be pressed, set the ones relating to deleting account to visible
                up_iv_j_signOut.setVisibility(View.GONE);
                up_iv_j_deleteAcc.setVisibility(View.GONE);
                up_iv_j_deletePrompt.setVisibility(View.VISIBLE);
                up_iv_j_yesDelete.setVisibility(View.VISIBLE);
                up_iv_j_noDelete.setVisibility(View.VISIBLE);
            }
        });
    }

    public void yesDeleteButtonEvent()
    {
        up_iv_j_yesDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                dbHelper.deleteUser(AppData.getUsername());
                Log.d("User Deleted",AppData.getUsername() + " was deleted.");
                startActivity(mainIntent);
            }
        });
    }

    public void noDeleteButtonEvent()
    {
        up_iv_j_noDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //If no to delete, clear settings
                clearSettings();
            }
        });
    }

    public void clearSettings()
    {
        //Clears all settings boxes
        up_iv_j_settingsBox.setVisibility(View.GONE);
        up_iv_j_signOut.setVisibility(View.GONE);
        up_iv_j_deleteAcc.setVisibility(View.GONE);
        up_iv_j_deletePrompt.setVisibility(View.GONE);
        up_iv_j_yesDelete.setVisibility(View.GONE);
        up_iv_j_noDelete.setVisibility(View.GONE);
    }

    public void signOutButtonEvent()
    {
        up_iv_j_signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(mainIntent);
            }
        });
    }

    public void movieSearchButtonEvent()
    {
        up_iv_j_movieSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(homePageIntent);
            }
        });
    }

    public void profileButtonEvent()
    {
        up_iv_j_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(profileIntent);
            }
        });
    }

    public void userSearchButtonEvent()
    {
        up_iv_j_userSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(userSearchIntent);
            }
        });
    }
}