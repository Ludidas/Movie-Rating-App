package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SignUp extends AppCompatActivity
{
    Intent mainIntent;

    DatabaseHelper dbHelper;

    ImageView su_iv_j_back;
    ImageView su_iv_j_submit;
    EditText su_et_j_uname;
    EditText su_et_j_pword;
    EditText su_et_j_cPword;
    TextView su_tv_j_error;
    TextView su_tv_j_success;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        su_iv_j_back=findViewById(R.id.su_iv_v_back);
        su_iv_j_submit=findViewById(R.id.su_iv_v_submit);
        su_et_j_uname=findViewById(R.id.su_et_v_uname);
        su_et_j_pword=findViewById(R.id.su_et_v_pword);
        su_et_j_cPword=findViewById(R.id.su_et_v_cPword);
        su_tv_j_error=findViewById(R.id.su_tv_v_error);
        su_tv_j_success=findViewById(R.id.su_tv_v_success);

        mainIntent=new Intent(SignUp.this, MainActivity.class);

        dbHelper = new DatabaseHelper(this);

        unameClickEvent();
        pwordClickEvent();
        cPwordClickEvent();
        signUpButtonEvent();
        backButtonEvent();
    }

    //Button Events=============================================================
    public void signUpButtonEvent()
    {
        su_iv_j_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Catches if data is entered into fields
                if(!usernamePasswordNull())
                {
                    //Checks if both passwords are the same
                    if(passwordsMatch())
                    {
                        //call dbHelper and pass the username and password.
                        String username = su_et_j_uname.getText().toString();
                        String password = su_et_j_pword.getText().toString();
                        String cPassword = su_et_j_cPword.getText().toString();

                        //If username does not exist in database, add new user
                        if(!dbHelper.usernameExists(username))
                        {
                            su_tv_j_error.setVisibility(View.INVISIBLE);
                            su_tv_j_success.setVisibility(View.VISIBLE);
                            su_tv_j_success.setText(username + " was successfully added!");

                            //Add the user to the database, admin is set to false by default
                            Users user=new Users(username,password,false);
                            dbHelper.addNewUser(user);

                            //startActivity(mainIntent);
                        }
                        else
                        {
                            su_tv_j_success.setVisibility(View.INVISIBLE);
                            su_tv_j_error.setVisibility(View.VISIBLE);
                            su_tv_j_error.setText("Username is already taken");
                            su_et_j_uname.setText("Create Username");
                            su_et_j_pword.setText("Create Password");
                            su_et_j_cPword.setText("Confirm Password");
                        }
                    }
                    else
                    {
                        su_tv_j_success.setVisibility(View.INVISIBLE);
                        su_tv_j_error.setVisibility(View.VISIBLE);
                        su_tv_j_error.setText("Please enter the same password in both fields");
                    }
                }
                else
                {
                    su_tv_j_success.setVisibility(View.INVISIBLE);
                    su_tv_j_error.setVisibility(View.VISIBLE);
                    su_tv_j_error.setText("Please enter all fields");
                }
            }
        });

    }

    public void backButtonEvent()
    {
        su_iv_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mainIntent);
            }
        });
    }

    //Miscellaneous Functions====================================================
    private boolean usernamePasswordNull()
    {
        String username = su_et_j_uname.getText().toString();
        String password = su_et_j_pword.getText().toString();
        String cPassword= su_et_j_cPword.getText().toString();
        if(username.equals("Create Username") && password.equals("Create Password") && cPassword.equals("Confirm Password"))
        {
            return true;
        }
        else if(username.equals("") && password.equals("") && cPassword.equals(""))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean passwordsMatch()
    {
        String password = su_et_j_pword.getText().toString();
        String cPassword= su_et_j_cPword.getText().toString();

        if(password.equals(cPassword))
        {
            return true;
        }
        else
        {
            return false;
        }
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