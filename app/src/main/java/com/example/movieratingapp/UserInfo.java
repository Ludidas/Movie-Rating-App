package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UserInfo extends AppCompatActivity
{
    DatabaseHelper dbHelper;
    Users userPassed;

    Intent adminUsersIntent;

    ImageView ui_iv_j_back;
    ImageView ui_iv_j_submit;
    ImageView ui_iv_j_deleteUser;
    EditText ui_et_j_password;
    CheckBox ui_cb_j_isAdmin;
    TextView ui_tv_j_success;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        ui_iv_j_back=findViewById(R.id.ui_iv_v_back);
        ui_iv_j_submit=findViewById(R.id.ui_iv_v_submit);
        ui_iv_j_deleteUser=findViewById(R.id.ui_iv_v_deleteUser);
        ui_et_j_password=findViewById(R.id.ui_et_v_password);
        ui_cb_j_isAdmin=findViewById(R.id.ui_cb_v_isAdmin);
        ui_tv_j_success=findViewById(R.id.ui_tv_v_success);

        adminUsersIntent=new Intent(UserInfo.this, AdminUsers.class);

        dbHelper=new DatabaseHelper(this);

        //Get user from AdminUsers
        Intent cameFrom = getIntent();
        userPassed = (Users) cameFrom.getSerializableExtra("USER");

        fillView();
        backButtonEvent();
        deleteUserButtonEvent();
        submitButtonEvent();
    }

    public void fillView()
    {
        ui_et_j_password.setText(userPassed.getPword());

        //Sets the admin check box to true if user is admin
        if(userPassed.isAdmin())
        {
            Log.d("ADMIN", userPassed.getUname() + " IS AN ADMIN");
            ui_cb_j_isAdmin.setChecked(true);
        }
    }

    public void backButtonEvent()
    {
        ui_iv_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(adminUsersIntent);
            }
        });
    }

    public void submitButtonEvent()
    {
        ui_iv_j_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Update user
                String username=userPassed.getUname();
                String password=ui_et_j_password.getText().toString();
                int isAdmin=0;

                if(ui_cb_j_isAdmin.isChecked())
                {
                    isAdmin=1;
                }


                Log.d("isAdmin CHECK", username + " is " + isAdmin);

                dbHelper.updateUser(username, password, isAdmin);

                //Set success message
                ui_tv_j_success.setText(username+ " was successfully updated!");
                ui_tv_j_success.setVisibility(View.VISIBLE);
            }
        });
    }

    public void deleteUserButtonEvent()
    {
        ui_iv_j_deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteUser(userPassed.getUname());
                startActivity(adminUsersIntent);
            }
        });
    }


}