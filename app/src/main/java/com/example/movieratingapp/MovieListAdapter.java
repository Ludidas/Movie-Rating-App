package com.example.movieratingapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<Movies> listOfMovies;

    public MovieListAdapter(Context c, ArrayList<Movies> ls)
    {
        //Passed to the class
        context=c;
        listOfMovies=ls;
    }

    @Override
    public int getCount() {
        return listOfMovies.size();
    }

    @Override
    public Object getItem(int i) {
        return listOfMovies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if (convertView==null)
        {
            LayoutInflater mInflater=(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=mInflater.inflate(R.layout.movie_cell, null);
        }

        //find the GUI elements in my movie_cell
        TextView title=convertView.findViewById(R.id.mc_tv_v_title);
        TextView genre=convertView.findViewById(R.id.mc_tv_v_genre);

        Movies movie=listOfMovies.get(i);

        //set the GUI for the custom_cell.xml
        title.setText(movie.getTitle());
        genre.setText(movie.getGenre());

        return convertView;
    }
}
