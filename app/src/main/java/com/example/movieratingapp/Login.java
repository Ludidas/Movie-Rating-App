package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity
{
    Button l_btn_j_login;
    Button l_btn_j_back;

    Intent mainIntent;
    Intent homePageIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        l_btn_j_login=findViewById(R.id.l_btn_v_login);
        l_btn_j_back=findViewById(R.id.l_btn_v_back);


        mainIntent=new Intent(Login.this, MainActivity.class);
        homePageIntent=new Intent(Login.this, HomePage.class);


        loginButtonEvent();
        backButtonEvent();
    }

    public void loginButtonEvent()
    {
        l_btn_j_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(homePageIntent);
            }
        });
    }

    public void backButtonEvent()
    {
        l_btn_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(mainIntent);
            }
        });
    }

}