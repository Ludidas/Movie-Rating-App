package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class UserSearch extends AppCompatActivity
{
    DatabaseHelper dbHelper;
    UserListAdapter adapter;
    ArrayList<Users> userList;

    Intent homePageIntent;
    Intent profileIntent;
    Intent userSearchIntent;
    Intent otherProfileIntent;

    Button us_btn_j_search;
    EditText us_et_j_searchBar;
    ImageView us_iv_j_movieSearch;
    ImageView us_iv_j_profile;
    ImageView us_iv_j_userSearch;
    ListView us_lv_j_users;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);

        us_btn_j_search=findViewById(R.id.us_btn_v_search);
        us_et_j_searchBar=findViewById(R.id.us_et_v_searchBar);
        us_iv_j_movieSearch=findViewById(R.id.us_iv_v_movieSearch);
        us_iv_j_profile=findViewById(R.id.us_iv_v_profile);
        us_iv_j_userSearch=findViewById(R.id.us_iv_v_userSearch);
        us_lv_j_users=findViewById(R.id.us_lv_v_users);

        homePageIntent=new Intent(UserSearch.this, HomePage.class);
        profileIntent=new Intent(UserSearch.this, UserProfile.class);
        userSearchIntent=new Intent(UserSearch.this, UserSearch.class);
        otherProfileIntent=new Intent(UserSearch.this, OtherProfile.class);

        userList=new ArrayList<Users>();
        dbHelper=new DatabaseHelper(this);
        userList = dbHelper.getAllUsers();


        fillListView();
        userSelectEvent();
        movieSearchButtonEvent();
        profileButtonEvent();
        userSearchButtonEvent();
    }

    public void fillListView()
    {
        adapter=new UserListAdapter(this, userList);
        //set the listview's adapter
        us_lv_j_users.setAdapter(adapter);
    }

    public void userSelectEvent()
    {
        us_lv_j_users.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id)
            {
                otherProfileIntent.putExtra("USER", userList.get(i));
                startActivity(otherProfileIntent);
            }
        });
    }

    public void movieSearchButtonEvent()
    {
        us_iv_j_movieSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(homePageIntent);
            }
        });
    }

    public void profileButtonEvent()
    {
        us_iv_j_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(profileIntent);
            }
        });
    }

    public void userSearchButtonEvent()
    {
        us_iv_j_userSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(userSearchIntent);
            }
        });
    }
}