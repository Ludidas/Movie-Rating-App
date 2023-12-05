//==========================
// Name: Matthew Palmer
// Date: 11/29/2023
// Desc: Movie Rating App
//==========================
package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
{
    DatabaseHelper dbHelper;

    Intent loginIntent;
    Intent signUpIntent;

    ImageView ma_iv_j_login;
    ImageView ma_iv_j_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ma_iv_j_login=findViewById(R.id.ma_iv_v_login);
        ma_iv_j_signUp=findViewById(R.id.ma_iv_v_signUp);

        loginIntent=new Intent(MainActivity.this, Login.class);
        signUpIntent=new Intent(MainActivity.this, SignUp.class);

        //Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);
        //call the initializeDB() function to fill the records into our table
        dbHelper.initializeDB();


        loginButtonEvent();
        signUpButtonEvent();
    }

    public void loginButtonEvent()
    {
        ma_iv_j_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(loginIntent);
            }
        });

    }

    public void signUpButtonEvent()
    {
        ma_iv_j_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(signUpIntent);
            }
        });
    }

}