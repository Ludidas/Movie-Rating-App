package com.example.movieratingapp;

import java.io.Serializable;

public class Movies implements Serializable
{
    private Integer movieId;
    private String title;
    private String genre;
    private Integer releaseYear;
    private String ageRating;
    private String description;
    private String trailerUrl;


    public Movies(Integer movieId, String title, String genre, Integer releaseYear, String ageRating, String description, String trailerUrl)
    {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.ageRating = ageRating;
        this.description = description;
        this.trailerUrl = trailerUrl;
    }


    public Integer getMovieId()
    {
        return movieId;
    }
    public void setMovieId(Integer movieId)
    {
        this.movieId = movieId;
    }

    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getGenre()
    {
        return genre;
    }
    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public Integer getReleaseYear()
    {
        return releaseYear;
    }
    public void setReleaseYear(Integer releaseYear)
    {
        this.releaseYear = releaseYear;
    }

    public String getAgeRating()
    {
        return ageRating;
    }
    public void setAgeRating(String ageRating)
    {
        this.ageRating = ageRating;
    }

    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getTrailerUrl()
    {
        return trailerUrl;
    }
    public void setTrailerUrl(String trailerUrl)
    {
        this.trailerUrl = trailerUrl;
    }
}
