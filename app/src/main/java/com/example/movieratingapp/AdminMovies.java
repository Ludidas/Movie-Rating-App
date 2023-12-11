package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class AdminMovies extends AppCompatActivity
{
    DatabaseHelper dbHelper;
    MovieListAdapter adapter;
    ArrayList<Movies> movieList;

    Intent addMovieIntent;
    Intent adminHomeIntent;
    Intent movieInfoIntent;

    ImageView am_iv_j_back;
    ImageView am_iv_j_addMovie;
    ListView am_lv_j_movies;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_movies);

        am_iv_j_back=findViewById(R.id.am_iv_v_back);
        am_iv_j_addMovie=findViewById(R.id.am_iv_v_addMovie);
        am_lv_j_movies=findViewById(R.id.am_lv_v_movies);

        addMovieIntent=new Intent(AdminMovies.this, AdminAddMovie.class);
        adminHomeIntent=new Intent(AdminMovies.this, AdminHomePage.class);
        movieInfoIntent=new Intent(AdminMovies.this, MovieInfo.class);

        movieList=new ArrayList<Movies>();
        dbHelper=new DatabaseHelper(this);
        movieList = dbHelper.getAllMovies();

        fillListView();
        selectMovieEvent();
        backButtonEvent();
        addMovieButtonEvent();
    }

    public void fillListView()
    {
        adapter=new MovieListAdapter(this, movieList);
        //set the listview's adapter
        am_lv_j_movies.setAdapter(adapter);
    }

    public void selectMovieEvent()
    {
        am_lv_j_movies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                movieInfoIntent.putExtra("MOVIE", movieList.get(i));
                startActivity(movieInfoIntent);
            }
        });
    }

    public void backButtonEvent()
    {
        am_iv_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(adminHomeIntent);
            }
        });
    }
    public void addMovieButtonEvent()
    {
        am_iv_j_addMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(addMovieIntent);
            }
        });
    }


}