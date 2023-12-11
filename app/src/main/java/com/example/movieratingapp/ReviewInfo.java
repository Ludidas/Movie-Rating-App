package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ReviewInfo extends AppCompatActivity
{
    DatabaseHelper dbHelper;
    ArrayAdapter<CharSequence> ratingAdapter;
    Reviews reviewPassed;
    ArrayList<Movies> movieList;
    //ArrayList<Movies> filteredMovies;
    int movieListPos;

    Intent profileIntent;

    ImageView ri_iv_j_back;
    ImageView ri_iv_j_delete;
    ImageView ri_iv_j_submit;
    TextView ri_tv_j_title;
    TextView ri_tv_j_error;
    Spinner ri_sp_j_rating;
    EditText ri_et_j_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_info);

        ri_iv_j_back=findViewById(R.id.ri_iv_v_back);
        ri_iv_j_delete=findViewById(R.id.ri_iv_v_delete);
        ri_iv_j_submit=findViewById(R.id.ri_iv_v_submit);
        ri_tv_j_title=findViewById(R.id.ri_tv_v_title);
        ri_tv_j_error=findViewById(R.id.ri_tv_v_error);
        ri_sp_j_rating=findViewById(R.id.ri_sp_v_rating);
        ri_et_j_comment=findViewById(R.id.ri_et_v_comment);

        profileIntent=new Intent(ReviewInfo.this, UserProfile.class);

        movieList=new ArrayList<Movies>();
        //filteredMovies=new ArrayList<Movies>();
        dbHelper=new DatabaseHelper(this);
        movieList = dbHelper.getAllMovies();

        //Set adapter for drop-down menu
        ratingAdapter=ArrayAdapter.createFromResource(this, R.array.rating, android.R.layout.simple_spinner_item);
        ratingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ri_sp_j_rating.setAdapter(ratingAdapter);


        //Get review from UserProfile
        Intent cameFrom = getIntent();
        reviewPassed = (Reviews) cameFrom.getSerializableExtra("REVIEW");

        //Find the specific movie by matching the ReviewId
        //movieListPos=dbHelper.getMovieFromReview(reviewPassed.getReviewId());



//        // Filter reviews based on the username
//        for (Reviews review : movieList)
//        {
//            if (review.getUserId().equals(AppData.getUsername()))
//            {
//                filteredReviews.add(review);
//            }
//        }

        fillTextInfo();
        backButtonEvent();
        deleteButtonEvent();
        submitButtonEvent();
    }

    public void fillTextInfo()
    {
        ri_et_j_comment.setText(reviewPassed.getComment());
//        if(movieListPos!=-1)
//        {
//            ri_tv_j_title.setText(movieList.get(movieListPos).getTitle());
//        }
//        else
//        {
//            ri_tv_j_title.setVisibility(View.INVISIBLE);
//        }
    }

    public void backButtonEvent()
    {
        ri_iv_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(profileIntent);
            }
        });
    }
    public void deleteButtonEvent()
    {
        ri_iv_j_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteReview(reviewPassed.getReviewId());
                startActivity(profileIntent);
            }
        });
    }

    public void submitButtonEvent()
    {
        ri_iv_j_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int reviewId=reviewPassed.getReviewId();
                String comments=ri_et_j_comment.getText().toString();
                int stars=Integer.parseInt(ri_sp_j_rating.getSelectedItem().toString());

                dbHelper.updateReview(reviewId,comments,stars);

                ri_tv_j_error.setText("Update was successful!");
            }
        });
    }

}