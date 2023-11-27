package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity
{

    Button hp_btn_j_movieSearch;
    Button hp_btn_j_profile;
    Button hp_btn_j_userSearch;


    Intent movieSearchIntent;
    Intent profileIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        hp_btn_j_movieSearch=findViewById(R.id.hp_btn_v_movieSearch);
        hp_btn_j_profile=findViewById(R.id.hp_btn_v_profile);
        hp_btn_j_userSearch=findViewById(R.id.hp_btn_v_userSearch);

        movieSearchIntent=new Intent(HomePage.this, MovieSearch.class);
        profileIntent=new Intent(HomePage.this, UserProfile.class);


        movieSearchButtonEvent();
        profileButtonEvent();
        userSearchButtonEvent();
    }

    public void movieSearchButtonEvent()
    {
        hp_btn_j_movieSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(movieSearchIntent);
            }
        });
    }

    public void profileButtonEvent()
    {
        hp_btn_j_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(profileIntent);
            }
        });
    }

    public void userSearchButtonEvent()
    {

    }

}