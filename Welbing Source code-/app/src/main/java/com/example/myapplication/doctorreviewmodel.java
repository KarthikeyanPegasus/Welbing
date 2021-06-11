package com.example.myapplication;

public class doctorreviewmodel {
    String reviewername;
    String reviews;

    public doctorreviewmodel(String reviewername, String reviews) {
        this.reviewername = reviewername;
        this.reviews = reviews;
    }


    public String getReviewername() {
        return reviewername;
    }

    public void setReviewername(String reviewername) {
        this.reviewername = reviewername;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }
}
