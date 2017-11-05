package com.epicodus.feedme.util;

import com.epicodus.feedme.models.Foodtruck;

import java.util.ArrayList;

public interface OnFoodtruckSelectedListener {
    public void onFoodtruckSelected(Integer position, ArrayList<Foodtruck> foodtrucks, String source);
}
