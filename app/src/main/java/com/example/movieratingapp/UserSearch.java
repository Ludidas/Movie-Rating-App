package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class UserSearch extends AppCompatActivity
{
    Intent homePageIntent;
    Intent profileIntent;
    Intent userSearchIntent;

    Button us_btn_j_search;
    EditText us_et_j_searchBar;
    ImageView us_iv_j_movieSearch;
    ImageView us_iv_j_profile;
    ImageView us_iv_j_userSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);

        us_btn_j_search=findViewById(R.id.us_btn_v_search);
        us_et_j_searchBar=findViewById(R.id.us_et_v_searchBar);
        us_iv_j_movieSearch=findViewById(R.id.us_iv_v_movieSearch);
        us_iv_j_profile=findViewById(R.id.us_iv_v_profile);
        us_iv_j_userSearch=findViewById(R.id.us_iv_v_userSearch);


        homePageIntent=new Intent(UserSearch.this, HomePage.class);
        profileIntent=new Intent(UserSearch.this, UserProfile.class);
        userSearchIntent=new Intent(UserSearch.this, UserSearch.class);


        movieSearchButtonEvent();
        profileButtonEvent();
        userSearchButtonEvent();
    }

    public void movieSearchButtonEvent()
    {
        us_iv_j_movieSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(homePageIntent);
            }
        });
    }

    public void profileButtonEvent()
    {
        us_iv_j_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(profileIntent);
            }
        });
    }

    public void userSearchButtonEvent()
    {
        us_iv_j_userSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(userSearchIntent);
            }
        });
    }
}