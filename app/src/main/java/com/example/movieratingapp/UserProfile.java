package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity
{
    Intent homePageIntent;
    Intent profileIntent;
    Intent userSearchIntent;
    Intent mainIntent;


    TextView up_tv_j_username;
    ImageView up_iv_j_settings;
    ImageView up_iv_j_settingsBox;
    ImageView up_iv_j_signOut;
    ImageView up_iv_j_deleteAcc;
    ImageView up_iv_j_movieSearch;
    ImageView up_iv_j_profile;
    ImageView up_iv_j_userSearch;

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
        up_iv_j_movieSearch=findViewById(R.id.up_iv_v_movieSearch);
        up_iv_j_profile=findViewById(R.id.up_iv_v_profile);
        up_iv_j_userSearch=findViewById(R.id.up_iv_v_userSearch);

        homePageIntent=new Intent(UserProfile.this, HomePage.class);
        profileIntent=new Intent(UserProfile.this, UserProfile.class);
        userSearchIntent=new Intent(UserProfile.this, UserSearch.class);
        mainIntent=new Intent(UserProfile.this, MainActivity.class);


        setUsernameText();
        settingsButtonEvent();
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


    //BUTTON EVENTS==========================================================================
    public void settingsButtonEvent()
    {
        up_iv_j_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //If settings box is visible, set everything as "GONE" when pressed, otherwise set it visible.
                if(up_iv_j_settingsBox.getVisibility()==View.VISIBLE)
                {
                    up_iv_j_settingsBox.setVisibility(View.GONE);
                    up_iv_j_signOut.setVisibility(View.GONE);
                    up_iv_j_deleteAcc.setVisibility(View.GONE);
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