package com.example.movieratingapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ReviewListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<Reviews> listOfReviews;

    public ReviewListAdapter(Context c, ArrayList<Reviews> ls)
    {
        //Passed to the class
        context=c;
        listOfReviews=ls;
    }

    @Override
    public int getCount() {
        return listOfReviews.size();
    }

    @Override
    public Object getItem(int i) {
        return listOfReviews.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent)
    {
        if(convertView==null)
        {
            LayoutInflater mInflater=(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=mInflater.inflate(R.layout.review_cell, null);
        }

        //find the GUI elements in my review_cell
        TextView username=convertView.findViewById(R.id.rc_tv_v_username);
        TextView score=convertView.findViewById(R.id.rc_tv_v_score);
        TextView comment=convertView.findViewById(R.id.rc_tv_v_comment);

        Reviews review = listOfReviews.get(i);

        //set the GUI for the user_cell.xml
        username.setText(review.getUserId());
        score.setText(review.getRating() + "/10");
        comment.setText(review.getComment());

        return convertView;
    }
}
