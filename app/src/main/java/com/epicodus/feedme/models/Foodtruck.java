package com.epicodus.feedme.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;


@Parcel
public class Foodtruck {
     String name;
     String phone;
     String website;
     double rating;
     String imageUrl;
     ArrayList<String> address = new ArrayList<>();
     double latitude;
     double longitude;
     ArrayList<String> categories = new ArrayList<>();

    public Foodtruck() {}

    public Foodtruck(String name, String phone, String website,
                      double rating, String imageUrl, ArrayList<String> address,
                      double latitude, double longitude, ArrayList<String> categories) {
        this.name = name;
        this.phone = phone;
        this.website = website;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return  website;
    }

    public double getRating() {
        return rating;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public ArrayList<String> getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public String getLargeImageUrl(String imageUrl) {
        String largeImageUrl = imageUrl.substring(0, imageUrl.length() - 6).concat("o.jpg");
        return largeImageUrl;
    }


}