package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminHomePage extends AppCompatActivity {
    Intent adminMoviesIntent;
    Intent adminUsersIntent;
    Intent mainIntent;

    ImageView ah_iv_j_home;
    ImageView ah_iv_j_movies;
    ImageView ah_iv_j_users;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        ah_iv_j_home=findViewById(R.id.ah_iv_v_home);
        ah_iv_j_movies=findViewById(R.id.ah_iv_v_movies);
        ah_iv_j_users=findViewById(R.id.ah_iv_v_users);

        adminMoviesIntent=new Intent(AdminHomePage.this, AdminMovies.class);
        adminUsersIntent=new Intent(AdminHomePage.this, AdminUsers.class);
        mainIntent=new Intent(AdminHomePage.this, MainActivity.class);

        homeButtonEvent();
        moviesButtonEvent();
        usersButtonEvent();
    }

    public void homeButtonEvent()
    {
        ah_iv_j_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(mainIntent);
            }
        });
    }

    public void moviesButtonEvent()
    {
        ah_iv_j_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(adminMoviesIntent);
            }
        });
    }

    public void usersButtonEvent()
    {
        ah_iv_j_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(adminUsersIntent);
            }
        });
    }
}