package com.example.mobileappdevpro2;

public class movie {


    private final String title;
    private final String year;
    private final String director;
    private final String actors;
    private final String rating;
    private final String review;


    public movie(String title, String year, String director, String actors, String rating, String review) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.actors = actors;
        this.rating = rating;
        this.review = review;
    }



    public String getTitle() {
        return title;
    }


    public String getYear() {
        return year;
    }


    public String getDirector() {
        return director;
    }


    public String getActors() {
        return actors;
    }


    public String getRating() {
        return rating;
    }


    public String getReview() {
        return review;
    }

}
