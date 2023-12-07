package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminAddMovie extends AppCompatActivity
{
    DatabaseHelper dbHelper;

    Intent adminMoviesIntent;

    ImageView aam_iv_j_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_movie);

        aam_iv_j_back=findViewById(R.id.aam_iv_v_back);

        adminMoviesIntent=new Intent(AdminAddMovie.this, AdminMovies.class);

        dbHelper=new DatabaseHelper(this);
    }



    //EVENT LISTENERS========================================================
    public void backButtonEvent()
    {
        aam_iv_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(adminMoviesIntent);
            }
        });
    }
}