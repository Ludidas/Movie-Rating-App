package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class HomePage extends AppCompatActivity
{
    Intent movieSearchIntent;
    Intent profileIntent;
    Intent userSearchIntent;
    Intent recommendedMoviesIntent;


    Button hp_btn_j_search;
    EditText hp_et_j_searchBar;
    ImageView hp_iv_j_movieSearch;
    ImageView hp_iv_j_profile;
    ImageView hp_iv_j_userSearch;
    ImageView hp_iv_j_recommendedArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        hp_btn_j_search=findViewById(R.id.hp_btn_v_search);
        hp_et_j_searchBar=findViewById(R.id.hp_et_v_searchBar);
        hp_iv_j_movieSearch=findViewById(R.id.hp_iv_v_movieSearch);
        hp_iv_j_profile=findViewById(R.id.hp_iv_v_profile);
        hp_iv_j_userSearch=findViewById(R.id.hp_iv_v_userSearch);
        hp_iv_j_recommendedArrow=findViewById(R.id.hp_iv_v_recommendedArrow);


        movieSearchIntent=new Intent(HomePage.this, MovieSearch.class);
        profileIntent=new Intent(HomePage.this, UserProfile.class);
        userSearchIntent=new Intent(HomePage.this, UserSearch.class);
        recommendedMoviesIntent=new Intent(HomePage.this, recommendedMovies.class);


        movieSearchButtonEvent();
        profileButtonEvent();
        userSearchButtonEvent();
        recommendedButtonEvent();
    }

    public void movieSearchButtonEvent()
    {
        hp_iv_j_movieSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        hp_iv_j_userSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(userSearchIntent);
            }
        });
    }

    public void recommendedButtonEvent()
    {
        hp_iv_j_recommendedArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(recommendedMoviesIntent);
            }
        });
    }
}