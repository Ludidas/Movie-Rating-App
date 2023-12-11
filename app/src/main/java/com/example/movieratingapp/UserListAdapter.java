package com.example.movieratingapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<Users> listOfUsers;

    public UserListAdapter(Context c, ArrayList<Users> ls)
    {
        //Passed to the class
        context=c;
        listOfUsers=ls;
    }
    @Override
    public int getCount() {
        return listOfUsers.size();
    }

    @Override
    public Object getItem(int i) {
        return listOfUsers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater mInflater=(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=mInflater.inflate(R.layout.user_cell, null);
        }

        //find the GUI elements in my user_cell
        TextView username=convertView.findViewById(R.id.uc_tv_v_uname);

        Users user=listOfUsers.get(i);

        //set the GUI for the user_cell.xml
        username.setText(user.getUname());

        return convertView;
    }
}
