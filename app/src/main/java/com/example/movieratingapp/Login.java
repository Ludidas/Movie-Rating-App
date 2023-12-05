package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Login extends AppCompatActivity
{
    DatabaseHelper dbHelper;

    Intent mainIntent;
    Intent homePageIntent;

    TextView l_tv_j_error;
    ImageView l_iv_j_submit;
    EditText l_et_j_uname;
    EditText l_et_j_pword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        l_tv_j_error=findViewById(R.id.l_tv_v_error);
        l_iv_j_submit=findViewById(R.id.l_iv_v_submit);
        l_et_j_uname=findViewById(R.id.l_et_v_uname);
        l_et_j_pword=findViewById(R.id.l_et_v_pword);

        mainIntent=new Intent(Login.this, MainActivity.class);
        homePageIntent=new Intent(Login.this, HomePage.class);

        dbHelper = new DatabaseHelper(this);


        loginButtonEvent();
        unameClickEvent();
        pwordClickEvent();
    }

    //USERNAME-PASSWORD CHECKING===================================================================
    public void loginButtonEvent()
    {
        l_iv_j_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!usernamePasswordNull())
                {
                    //call dbHelper and pass the username and password.
                    String username = l_et_j_uname.getText().toString();
                    String password = l_et_j_pword.getText().toString();

                    if(dbHelper.validUsernamePasswordCombo(username, password))
                    {
                        l_tv_j_error.setVisibility(View.INVISIBLE);


                        //Send to different intents depending on whether they are a user or admin


                        //Set username as static string
                        AppData.setUsername(username);

                        startActivity(homePageIntent);
                    }
                    else
                    {
                        l_tv_j_error.setVisibility(View.VISIBLE);
                        l_tv_j_error.setText("Username and/or Password was incorrect");
                    }
                }
                else
                {
                    l_tv_j_error.setVisibility(View.VISIBLE);
                    l_tv_j_error.setText("Please enter a username & password");
                }

            }

        });
    }

    private boolean usernamePasswordNull()
    {
        String username = l_et_j_uname.getText().toString();
        String password = l_et_j_pword.getText().toString();

        if(!username.equals("") && !password.toString().equals("") || !username.equals("Username") && !password.toString().equals("Password"))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    //UI===========================================================================================
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