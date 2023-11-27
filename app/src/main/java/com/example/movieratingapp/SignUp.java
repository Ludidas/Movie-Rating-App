package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUp extends AppCompatActivity
{
    Intent mainIntent;


    Button su_btn_j_signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        su_btn_j_signUp=findViewById(R.id.su_btn_v_signUp);

        mainIntent=new Intent(SignUp.this, MainActivity.class);

        signUpButtonEvent();
    }

    public void signUpButtonEvent()
    {
        su_btn_j_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(mainIntent);
            }
        });
    }
}