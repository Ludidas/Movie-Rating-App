package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class AdminUsers extends AppCompatActivity {
    DatabaseHelper dbHelper;
    UserListAdapter adapter;
    ArrayList<Users> userList;

    Intent adminHomeIntent;

    ImageView au_iv_j_back;
    ImageView au_iv_j_addUser;
    ListView au_lv_j_users;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_users);

        au_iv_j_back=findViewById(R.id.au_iv_v_back);
        au_iv_j_addUser=findViewById(R.id.au_iv_v_addUser);
        au_lv_j_users=findViewById(R.id.au_lv_v_users);

        adminHomeIntent=new Intent(AdminUsers.this, AdminHomePage.class);

        userList=new ArrayList<Users>();
        dbHelper=new DatabaseHelper(this);
        userList = dbHelper.getAllUsers();

        backButtonEvent();
        fillListView();
        addUserButtonEvent();
    }

    public void fillListView()
    {
        adapter=new UserListAdapter(this, userList);
        //set the listview's adapter
        au_lv_j_users.setAdapter(adapter);
    }

    public void backButtonEvent()
    {
        au_iv_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(adminHomeIntent);
            }
        });
    }

    public void addUserButtonEvent()
    {
        au_iv_j_addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

            }
        });
    }
}