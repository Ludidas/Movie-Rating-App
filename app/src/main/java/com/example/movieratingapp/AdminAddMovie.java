package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AdminAddMovie extends AppCompatActivity
{
    DatabaseHelper dbHelper;
    ArrayAdapter<CharSequence> genreAdapter;
    ArrayAdapter<CharSequence> yearAdapter;
    ArrayAdapter<CharSequence> ageRatingAdapter;

    Intent adminMoviesIntent;

    ImageView aam_iv_j_back;
    ImageView aam_iv_j_submit;
    EditText aam_et_j_title;
    Spinner aam_sp_j_genre;
    Spinner aam_sp_j_year;
    Spinner aam_sp_j_ageRating;
    TextView aam_et_j_description;
    TextView aam_tv_j_error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_movie);

        aam_iv_j_back=findViewById(R.id.aam_iv_v_back);
        aam_iv_j_submit=findViewById(R.id.aam_iv_v_submit);
        aam_et_j_title=findViewById(R.id.aam_et_v_title);
        aam_sp_j_genre=findViewById(R.id.aam_sp_v_genre);
        aam_sp_j_year=findViewById(R.id.aam_sp_v_year);
        aam_sp_j_ageRating=findViewById(R.id.aam_sp_v_ageRating);
        aam_et_j_description=findViewById(R.id.aam_et_v_description);
        aam_tv_j_error=findViewById(R.id.aam_tv_v_error);

        adminMoviesIntent=new Intent(AdminAddMovie.this, AdminMovies.class);

        dbHelper=new DatabaseHelper(this);
        //Set adapters for drop-down menus
        genreAdapter=ArrayAdapter.createFromResource(this, R.array.genres, android.R.layout.simple_spinner_item);
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        aam_sp_j_genre.setAdapter(genreAdapter);

        yearAdapter=ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        aam_sp_j_year.setAdapter(yearAdapter);

        ageRatingAdapter=ArrayAdapter.createFromResource(this, R.array.ageRating, android.R.layout.simple_spinner_item);
        ageRatingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        aam_sp_j_ageRating.setAdapter(ageRatingAdapter);

        backButtonEvent();
        submitButtonEvent();
    }

    public void backButtonEvent()
    {
        aam_iv_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(adminMoviesIntent);
            }
        });
    }
    public void submitButtonEvent()
    {
        aam_iv_j_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!movieFieldsNull())
                {
                    //Get from boxes
                    String title = aam_et_j_title.getText().toString();
                    String genre = aam_sp_j_genre.getSelectedItem().toString();
                    int year=Integer.parseInt(aam_sp_j_year.getSelectedItem().toString());
                    String ageRating=aam_sp_j_ageRating.getSelectedItem().toString();
                    String description=aam_et_j_description.getText().toString();

                    //Add new movie in dbHelper
                    dbHelper.addMovie(title,genre,year,ageRating,description,"");

                    //Set success message
                    aam_tv_j_error.setVisibility(View.VISIBLE);
                    aam_tv_j_error.setTextColor(Color.YELLOW);
                    aam_tv_j_error.setText(title + " was successfully added!");
                }
                else
                {
                    //Set error message
                    aam_tv_j_error.setVisibility(View.VISIBLE);
                    aam_tv_j_error.setTextColor(Color.RED);
                    aam_tv_j_error.setText("Please enter all fields");
                }
            }
        });
    }

    public boolean movieFieldsNull()
    {
        String title = aam_et_j_title.getText().toString();
        String description=aam_et_j_description.getText().toString();

        if(title.equals("") || description.equals(""))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}