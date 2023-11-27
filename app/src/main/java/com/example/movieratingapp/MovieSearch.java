package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MovieSearch extends AppCompatActivity
{
    Button ms_btn_v_back;


    Intent homePageIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search);

        ms_btn_v_back=findViewById(R.id.ms_btn_v_back);

        homePageIntent=new Intent(MovieSearch.this, HomePage.class);

        backButtonEvent();
    }

    public void backButtonEvent()
    {
        ms_btn_v_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(homePageIntent);
            }
        });
    }
}