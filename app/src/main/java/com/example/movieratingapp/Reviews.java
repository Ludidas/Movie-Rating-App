package com.example.movieratingapp;

import java.io.Serializable;

public class Reviews implements Serializable
{
    private Integer reviewId;
    private Integer movieId;
    private Integer userId;
    private String comment;
    private Integer rating;


    public Reviews(Integer reviewId, Integer movieId, Integer userId, String comment, Integer rating)
    {
        this.reviewId = reviewId;
        this.movieId = movieId;
        this.userId = userId;
        this.comment = comment;
        this.rating = rating;
    }

    public Integer getReviewId()
    {
        return reviewId;
    }
    public void setReviewId(Integer reviewId)
    {
        this.reviewId = reviewId;
    }

    public Integer getMovieId()
    {
        return movieId;
    }
    public void setMovieId(Integer movieId)
    {
        this.movieId = movieId;
    }

    public Integer getUserId()
    {
        return userId;
    }
    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getComment()
    {
        return comment;
    }
    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public Integer getRating()
    {
        return rating;
    }
    public void setRating(Integer rating)
    {
        this.rating = rating;
    }
}
