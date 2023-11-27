package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserProfile extends AppCompatActivity
{
    Button up_btn_j_back;


    Intent homePageIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        up_btn_j_back=findViewById(R.id.up_btn_v_back);

        homePageIntent=new Intent(UserProfile.this, HomePage.class);

        backButtonEvent();
    }

    public void backButtonEvent()
    {
        up_btn_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(homePageIntent);
            }
        });
    }
}