package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserSearch extends AppCompatActivity
{
    Button us_btn_j_back;


    Intent homePageIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);

        us_btn_j_back=findViewById(R.id.us_btn_v_back);

        homePageIntent=new Intent(UserSearch.this, HomePage.class);

        backButtonEvent();
    }

    public void backButtonEvent()
    {
        us_btn_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(homePageIntent);
            }
        });
    }
}