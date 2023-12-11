package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class recommendedMovies extends AppCompatActivity
{
    DatabaseHelper dbHelper;
    MovieListAdapter adapter;
    ArrayList<Movies> movieList;

    Intent movieSearchIntent;
    Intent profileIntent;
    Intent userSearchIntent;
    Intent moviePageIntent;

    ListView rm_lv_j_movies;
    ImageView rm_iv_j_movieSearch;
    ImageView rm_iv_j_profile;
    ImageView rm_iv_j_userSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_movies);

        rm_lv_j_movies=findViewById(R.id.rm_lv_v_movies);
        rm_iv_j_movieSearch=findViewById(R.id.rm_iv_v_movieSearch);
        rm_iv_j_profile=findViewById(R.id.rm_iv_v_profile);
        rm_iv_j_userSearch=findViewById(R.id.rm_iv_v_userSearch);


        movieSearchIntent=new Intent(recommendedMovies.this, HomePage.class);
        profileIntent=new Intent(recommendedMovies.this, UserProfile.class);
        userSearchIntent=new Intent(recommendedMovies.this, UserSearch.class);
        moviePageIntent=new Intent(recommendedMovies.this, MoviePage.class);


        movieList=new ArrayList<Movies>();
        dbHelper=new DatabaseHelper(this);
        movieList = dbHelper.getAllMovies();

        rm_lv_j_movies.setAdapter(adapter);

        fillListView();
        selectMovieEvent();
        movieSearchButtonEvent();
        profileButtonEvent();
        userSearchButtonEvent();
    }

    //Button Events==================================================================

    public void selectMovieEvent()
    {
        rm_lv_j_movies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                moviePageIntent.putExtra("MOVIE", movieList.get(i));
                startActivity(moviePageIntent);
            }
        });
    }
    public void movieSearchButtonEvent()
    {
        rm_iv_j_movieSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(movieSearchIntent);
            }
        });
    }

    public void profileButtonEvent()
    {
        rm_iv_j_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(profileIntent);
            }
        });
    }

    public void userSearchButtonEvent()
    {
        rm_iv_j_userSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(userSearchIntent);
            }
        });
    }


    //Miscellaneous Functions=======================================================================

    public void fillListView()
    {
        adapter=new MovieListAdapter(this, movieList);
        //set the listview's adapter
        rm_lv_j_movies.setAdapter(adapter);
    }

}