package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomePage extends AppCompatActivity
{
    Intent movieSearchIntent;
    Intent profileIntent;


    ImageView hp_iv_j_movieSearch;
    ImageView hp_iv_j_profile;
    ImageView hp_iv_j_userSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        hp_iv_j_movieSearch=findViewById(R.id.hp_iv_v_movieSearch);
        hp_iv_j_profile=findViewById(R.id.hp_iv_v_profile);
        hp_iv_j_userSearch=findViewById(R.id.hp_iv_v_userSearch);


        movieSearchIntent=new Intent(HomePage.this, MovieSearch.class);
        profileIntent=new Intent(HomePage.this, UserProfile.class);


        movieSearchButtonEvent();
        profileButtonEvent();
        userSearchButtonEvent();
    }

    public void movieSearchButtonEvent()
    {
        hp_iv_j_movieSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(movieSearchIntent);
            }
        });
    }

    public void profileButtonEvent()
    {
        hp_iv_j_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(profileIntent);
            }
        });
    }

    public void userSearchButtonEvent()
    {

    }

}