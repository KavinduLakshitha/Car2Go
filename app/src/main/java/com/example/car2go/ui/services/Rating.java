package com.example.car2go.ui.services;

public class Rating {
    String ratingID;
    String customerID;
    String customerName;
    String rate ;
    String review;

    public Rating(){

    }

    public Rating(String ratingID, String customerID, String customerName, String rate, String review) {
        this.ratingID = ratingID;
        this.customerID = customerID;
        this.customerName = customerName;
        this.rate = rate;
        this.review = review;
    }

    public String getRatingID() {
        return ratingID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getRate() {
        return rate;
    }

    public String getReview() {
        return review;
    }
}
