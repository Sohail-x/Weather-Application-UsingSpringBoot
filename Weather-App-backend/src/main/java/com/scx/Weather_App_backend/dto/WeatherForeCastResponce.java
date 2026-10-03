package com.scx.Weather_App_backend.dto;

import java.util.List;

public class WeatherForeCastResponce {

    private WeatherResponce weatherResponce;

    private List<DayTemp> dayTemp;

    

    public WeatherResponce getWeatherResponce() {
        return weatherResponce;
    }

    public void setWeatherResponce(WeatherResponce weatherResponce) {
        this.weatherResponce = weatherResponce;
    }

    public List<DayTemp> getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(List<DayTemp> dayTemp) {
        this.dayTemp = dayTemp;
    }

    

}
