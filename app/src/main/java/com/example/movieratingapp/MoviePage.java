package com.example.movieratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MoviePage extends AppCompatActivity {

    DatabaseHelper dbHelper;
    ReviewListAdapter adapter;
    ArrayList<Reviews> reviewList;
    ArrayList<Reviews> filteredReviews;
    Movies moviePassed;
    int totalReviewNum;

    Intent movieSearchIntent;
    Intent profileIntent;
    Intent userSearchIntent;
    Intent createReviewIntent;

    TextView mp_tv_j_title;
    TextView mp_tv_j_movieInfo;
    TextView mp_tv_j_description;
    TextView mp_tv_j_totalRating;
    ImageView mp_iv_j_movieSearch;
    ImageView mp_iv_j_profile;
    ImageView mp_iv_j_userSearch;
    ImageView mp_iv_j_addReview;
    ListView mp_lv_j_reviews;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_page);

        mp_tv_j_title=findViewById(R.id.mp_tv_v_title);
        mp_tv_j_movieInfo=findViewById(R.id.mp_tv_v_movieInfo);
        mp_tv_j_description=findViewById(R.id.mp_tv_v_description);
        mp_tv_j_totalRating=findViewById(R.id.mp_tv_v_totalRating);
        mp_iv_j_movieSearch=findViewById(R.id.mp_iv_v_movieSearch);
        mp_iv_j_profile=findViewById(R.id.mp_iv_v_profile);
        mp_iv_j_userSearch=findViewById(R.id.mp_iv_v_userSearch);
        mp_iv_j_addReview=findViewById(R.id.mp_iv_v_addReview);
        mp_lv_j_reviews=findViewById(R.id.mp_lv_v_reviews);

        movieSearchIntent=new Intent(MoviePage.this, HomePage.class);
        profileIntent=new Intent(MoviePage.this, UserProfile.class);
        userSearchIntent=new Intent(MoviePage.this, UserSearch.class);
        createReviewIntent=new Intent(MoviePage.this, CreateReview.class);

        reviewList=new ArrayList<Reviews>();
        filteredReviews=new ArrayList<Reviews>();
        dbHelper=new DatabaseHelper(this);
        reviewList = dbHelper.getAllReviews();

        //Get movie from Movie List Page or Create review page
        Intent cameFrom = getIntent();
        moviePassed = (Movies) cameFrom.getSerializableExtra("MOVIE");


        // Filter reviews based on the selected movieId
        for (Reviews review : reviewList)
        {
            if (review.getMovieId().equals(moviePassed.getMovieId()))
            {
                filteredReviews.add(review);
            }
        }

        //Variable totalReviewNum will be used to calculate the total added number of every score in filteredReviews
        //This will then be divided by the total size of filteredReviews to find an average
        for(int i=0; i<filteredReviews.size();i++)
        {
            totalReviewNum+=filteredReviews.get(i).getRating();
        }

        if (filteredReviews.size()>0)
        {
            totalReviewNum=totalReviewNum/filteredReviews.size();
        }



        fillTextViews();
        fillListView();
        addReviewButtonEvent();
        movieSearchButtonEvent();
        profileButtonEvent();
        userSearchButtonEvent();
    }

    public void fillTextViews()
    {
        mp_tv_j_title.setText(moviePassed.getTitle());
        mp_tv_j_movieInfo.setText(moviePassed.getReleaseYear() + ", " + moviePassed.getGenre());
        mp_tv_j_description.setText(moviePassed.getDescription());
        mp_tv_j_totalRating.setText(totalReviewNum + "/10");
    }

    public void fillListView()
    {
        // Create and set the adapter with the filtered reviews
        adapter = new ReviewListAdapter(this, filteredReviews);
        mp_lv_j_reviews.setAdapter(adapter);
    }

    //BUTTON EVENTS=========================================
    public void addReviewButtonEvent()
    {
        mp_iv_j_addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createReviewIntent.putExtra("MOVIE", moviePassed);
                startActivity(createReviewIntent);
            }
        });
    }
    public void movieSearchButtonEvent()
    {
        mp_iv_j_movieSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(movieSearchIntent);
            }
        });
    }

    public void profileButtonEvent()
    {
        mp_iv_j_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(profileIntent);
            }
        });
    }

    public void userSearchButtonEvent()
    {
        mp_iv_j_userSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(userSearchIntent);
            }
        });
    }

}