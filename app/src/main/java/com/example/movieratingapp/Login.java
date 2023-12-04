package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Login extends AppCompatActivity
{
    Intent mainIntent;
    Intent homePageIntent;


    ImageView l_iv_j_submit;
    EditText l_et_j_uname;
    EditText l_et_j_pword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        l_iv_j_submit=findViewById(R.id.l_iv_v_submit);
        l_et_j_uname=findViewById(R.id.l_et_v_uname);
        l_et_j_pword=findViewById(R.id.l_et_v_pword);


        mainIntent=new Intent(Login.this, MainActivity.class);
        homePageIntent=new Intent(Login.this, HomePage.class);



        loginButtonEvent();
        unameClickEvent();
        pwordClickEvent();
    }

    public void loginButtonEvent()
    {
        l_iv_j_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(homePageIntent);
            }
        });
    }

    public void unameClickEvent()
    {
        l_et_j_uname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(l_et_j_uname.getText().toString().equals("Username"))
                {
                    l_et_j_uname.getText().clear();
                }
            }
        });
    }

    public void pwordClickEvent()
    {
        l_et_j_pword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(l_et_j_pword.getText().toString().equals("Password"))
                {
                    l_et_j_pword.getText().clear();
                }
            }
        });
    }
}