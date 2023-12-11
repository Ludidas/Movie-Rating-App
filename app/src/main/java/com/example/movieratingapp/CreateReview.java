package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateReview extends AppCompatActivity
{
    DatabaseHelper dbHelper;
    ArrayAdapter<CharSequence> ratingAdapter;
    Movies moviePassed;

    Intent moviePageIntent;

    ImageView cr_iv_j_back;
    ImageView cr_iv_j_submit;
    Spinner cr_sp_j_rating;
    EditText cr_et_j_comment;
    TextView cr_tv_j_success;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_review);

        cr_iv_j_back=findViewById(R.id.cr_iv_v_back);
        cr_iv_j_submit=findViewById(R.id.cr_iv_v_submit);
        cr_sp_j_rating=findViewById(R.id.cr_sp_v_rating);
        cr_et_j_comment=findViewById(R.id.cr_et_v_comment);
        cr_tv_j_success=findViewById(R.id.cr_tv_v_success);

        moviePageIntent= new Intent(CreateReview.this, MoviePage.class);

        dbHelper=new DatabaseHelper(this);

        //Get movie from Movie List Page
        Intent cameFrom = getIntent();
        moviePassed = (Movies) cameFrom.getSerializableExtra("MOVIE");

        //Set adapter for drop-down menu
        ratingAdapter=ArrayAdapter.createFromResource(this, R.array.rating, android.R.layout.simple_spinner_item);
        ratingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        cr_sp_j_rating.setAdapter(ratingAdapter);


        backButtonEvent();
        submitButtonEvent();
    }

    public void backButtonEvent()
    {
        cr_iv_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviePageIntent.putExtra("MOVIE", moviePassed);
                startActivity(moviePageIntent);
            }
        });
    }

    public void submitButtonEvent()
    {
        cr_iv_j_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Get info for adding review
                int movieId = moviePassed.getMovieId();
                String username=AppData.getUsername();
                String comment=cr_et_j_comment.getText().toString();
                int stars=Integer.parseInt(cr_sp_j_rating.getSelectedItem().toString());

                if(!dbHelper.reviewExists(movieId, username))
                {
                    //Add review
                    dbHelper.addReview(movieId,username,comment,stars);

                    //Set success message
                    cr_tv_j_success.setText("Review was successfully created for " + moviePassed.getTitle());
                    cr_tv_j_success.setTextColor(Color.YELLOW);
                    cr_tv_j_success.setVisibility(View.VISIBLE);
                }
                else
                {
                    cr_tv_j_success.setText(username+ " already has a review created.");
                    cr_tv_j_success.setTextColor(Color.RED);
                    cr_tv_j_success.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}