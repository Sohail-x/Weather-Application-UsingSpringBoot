package com.scx.Weather_App_bySpark.dto;

import java.util.ArrayList;

public class Forecast {
    private ArrayList<Forecastday> forecastday;

    public ArrayList<Forecastday> getForecastday() {
        return forecastday;
    }

    public void setForecastday(ArrayList<Forecastday> forecastday) {
        this.forecastday = forecastday;
    }

    
}
