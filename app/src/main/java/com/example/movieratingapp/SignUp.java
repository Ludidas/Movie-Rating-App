package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class SignUp extends AppCompatActivity
{
    Intent mainIntent;


    ImageView su_iv_j_submit;
    EditText su_et_j_uname;
    EditText su_et_j_pword;
    EditText su_et_j_cPword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        su_iv_j_submit=findViewById(R.id.su_iv_v_submit);
        su_et_j_uname=findViewById(R.id.su_et_v_uname);
        su_et_j_pword=findViewById(R.id.su_et_v_pword);
        su_et_j_cPword=findViewById(R.id.su_et_v_cPword);

        mainIntent=new Intent(SignUp.this, MainActivity.class);

        unameClickEvent();
        pwordClickEvent();
        cPwordClickEvent();
        signUpButtonEvent();
    }

    public void signUpButtonEvent()
    {
        su_iv_j_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(mainIntent);
            }
        });

    }

    //==================================================================================
    //These will empty the text in the boxes when pressed
    public void unameClickEvent()
    {
        su_et_j_uname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(su_et_j_uname.getText().toString().equals("Create Username"))
                {
                    su_et_j_uname.getText().clear();
                }
            }
        });
    }

    public void pwordClickEvent()
    {
        su_et_j_pword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(su_et_j_pword.getText().toString().equals("Create Password"))
                {
                    su_et_j_pword.getText().clear();
                }
            }
        });
    }

    public void cPwordClickEvent()
    {
        su_et_j_cPword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(su_et_j_cPword.getText().toString().equals("Confirm Password"))
                {
                    su_et_j_cPword.getText().clear();
                }
            }
        });
    }
}