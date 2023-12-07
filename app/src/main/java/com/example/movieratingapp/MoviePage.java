package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;

public class MoviePage extends AppCompatActivity {

    Intent cameFrom;
    Intent movieSearchIntent;
    Intent profileIntent;
    Intent userSearchIntent;

    ImageView mp_iv_j_movieSearch;
    ImageView mp_iv_j_profile;
    ImageView mp_iv_j_userSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_page);

        mp_iv_j_movieSearch=findViewById(R.id.mp_iv_v_movieSearch);
        mp_iv_j_profile=findViewById(R.id.mp_iv_v_profile);
        mp_iv_j_userSearch=findViewById(R.id.mp_iv_v_userSearch);

        movieSearchIntent=new Intent(MoviePage.this, HomePage.class);
        profileIntent=new Intent(MoviePage.this, UserProfile.class);
        userSearchIntent=new Intent(MoviePage.this, UserSearch.class);


        movieSearchButtonEvent();
        profileButtonEvent();
        userSearchButtonEvent();
    }





    //BUTTON EVENTS=========================================
    public void movieSearchButtonEvent()
    {
        mp_iv_j_movieSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(movieSearchIntent);
            }
        });
    }

    public void profileButtonEvent()
    {
        mp_iv_j_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(profileIntent);
            }
        });
    }

    public void userSearchButtonEvent()
    {
        mp_iv_j_userSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(userSearchIntent);
            }
        });
    }

}