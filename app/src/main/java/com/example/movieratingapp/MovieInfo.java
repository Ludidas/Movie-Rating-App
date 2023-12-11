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

public class MovieInfo extends AppCompatActivity {

    DatabaseHelper dbHelper;
    ArrayAdapter<CharSequence> genreAdapter;
    ArrayAdapter<CharSequence> yearAdapter;
    ArrayAdapter<CharSequence> ageRatingAdapter;
    Movies moviePassed;

    Intent adminMoviesIntent;

    ImageView mi_iv_j_back;
    ImageView mi_iv_j_submit;
    ImageView mi_iv_j_delete;
    EditText mi_et_j_title;
    Spinner mi_sp_j_genre;
    Spinner mi_sp_j_year;
    Spinner mi_sp_j_ageRating;
    TextView mi_et_j_description;
    TextView mi_tv_j_error;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        mi_iv_j_back=findViewById(R.id.mi_iv_v_back);
        mi_iv_j_submit=findViewById(R.id.mi_iv_v_submit);
        mi_iv_j_delete=findViewById(R.id.mi_iv_v_delete);
        mi_et_j_title=findViewById(R.id.mi_et_v_title);
        mi_sp_j_genre=findViewById(R.id.mi_sp_v_genre);
        mi_sp_j_year=findViewById(R.id.mi_sp_v_year);
        mi_sp_j_ageRating=findViewById(R.id.mi_sp_v_ageRating);
        mi_et_j_description=findViewById(R.id.mi_et_v_description);
        mi_tv_j_error=findViewById(R.id.mi_tv_v_error);

        adminMoviesIntent=new Intent(MovieInfo.this, AdminMovies.class);

        dbHelper=new DatabaseHelper(this);

        //Get movie from AdminMovies
        Intent cameFrom = getIntent();
        moviePassed = (Movies) cameFrom.getSerializableExtra("MOVIE");

        //Set adapters for drop-down menus
        genreAdapter=ArrayAdapter.createFromResource(this, R.array.genres, android.R.layout.simple_spinner_item);
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mi_sp_j_genre.setAdapter(genreAdapter);

        yearAdapter=ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mi_sp_j_year.setAdapter(yearAdapter);

        ageRatingAdapter=ArrayAdapter.createFromResource(this, R.array.ageRating, android.R.layout.simple_spinner_item);
        ageRatingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mi_sp_j_ageRating.setAdapter(ageRatingAdapter);


        fillView();
        backButtonEvent();
        submitButtonEvent();
        deleteButtonEvent();
    }

    public void fillView()
    {
        mi_et_j_title.setText(moviePassed.getTitle());
        mi_et_j_description.setText(moviePassed.getDescription());
    }

    public boolean movieFieldsNull()
    {
        String title = mi_et_j_title.getText().toString();
        String description=mi_et_j_description.getText().toString();

        if(title.equals("") || description.equals(""))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void backButtonEvent()
    {
        mi_iv_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(adminMoviesIntent);
            }
        });
    }

    public void submitButtonEvent()
    {
        mi_iv_j_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!movieFieldsNull())
                {
                    //Get from boxes
                    String title = mi_et_j_title.getText().toString();
                    String genre = mi_sp_j_genre.getSelectedItem().toString();
                    int year=Integer.parseInt(mi_sp_j_year.getSelectedItem().toString());
                    String ageRating=mi_sp_j_ageRating.getSelectedItem().toString();
                    String description=mi_et_j_description.getText().toString();

                    //Add new movie in dbHelper
                    dbHelper.updateMovie(moviePassed.getMovieId(),title,genre,year,ageRating,description,"");

                    //Set success message
                    mi_tv_j_error.setVisibility(View.VISIBLE);
                    mi_tv_j_error.setTextColor(Color.YELLOW);
                    mi_tv_j_error.setText(title + " was successfully updated!");
                }
                else
                {
                    //Set error message
                    mi_tv_j_error.setVisibility(View.VISIBLE);
                    mi_tv_j_error.setTextColor(Color.RED);
                    mi_tv_j_error.setText("Please enter all fields");
                }
            }
        });

    }

    public void deleteButtonEvent()
    {
        mi_iv_j_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteMovie(moviePassed.getMovieId());
                startActivity(adminMoviesIntent);
            }
        });
    }


}