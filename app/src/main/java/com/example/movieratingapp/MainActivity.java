package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    Intent loginIntent;
    Intent signUpIntent;



    Button ma_btn_j_login;
    Button ma_btn_j_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ma_btn_j_login=findViewById(R.id.ma_btn_v_login);
        ma_btn_j_signUp=findViewById(R.id.ma_btn_v_signUp);

        loginIntent=new Intent(MainActivity.this, Login.class);
        signUpIntent=new Intent(MainActivity.this, SignUp.class);



        loginButtonEvent();
        signUpButtonEvent();
    }

    public void loginButtonEvent()
    {
        ma_btn_j_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(loginIntent);
            }
        });
    }

    public void signUpButtonEvent()
    {
        ma_btn_j_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(signUpIntent);
            }
        });
    }

}