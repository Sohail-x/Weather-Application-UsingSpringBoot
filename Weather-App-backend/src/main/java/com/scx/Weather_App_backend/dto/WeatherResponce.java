package com.scx.Weather_App_backend.dto;



public class WeatherResponce {
    

    private String country;
    private String region;
    private String city;
    private String localtime;
    private double temp_c;
    private double humidity;
    private double wind_kph;
    private String text;



    public WeatherResponce(String country, String region, String city, String localtime, double temp_c, double humidity,
            double wind_kph, String text) {
        this.country = country;
        this.region = region;
        this.city = city;
        this.localtime = localtime;
        this.temp_c = temp_c;
        this.humidity = humidity;
        this.wind_kph = wind_kph;
        this.text = text;
    }



    public WeatherResponce() {
    }



    public String getCountry() {
        return country;
    }



    public void setCountry(String country) {
        this.country = country;
    }



    public String getRegion() {
        return region;
    }



    public void setRegion(String region) {
        this.region = region;
    }



    public String getCity() {
        return city;
    }



    public void setCity(String city) {
        this.city = city;
    }



    public String getLocaltime() {
        return localtime;
    }



    public void setLocaltime(String localtime) {
        this.localtime = localtime;
    }



    public double getTemp_c() {
        return temp_c;
    }



    public void setTemp_c(double temp_c) {
        this.temp_c = temp_c;
    }



    public double getHumidity() {
        return humidity;
    }



    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }



    public double getWind_kph() {
        return wind_kph;
    }



    public void setWind_kph(double wind_kph) {
        this.wind_kph = wind_kph;
    }



    public String getText() {
        return text;
    }



    public void setText(String text) {
        this.text = text;
    }

    
    
    

    

    

}
