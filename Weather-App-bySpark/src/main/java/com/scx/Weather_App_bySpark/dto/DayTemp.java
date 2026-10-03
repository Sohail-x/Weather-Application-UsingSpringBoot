package com.scx.Weather_App_bySpark.dto;



public class DayTemp {


    private String date;
    private double mintemp_c;
    private double avgtemp_c;
    private double max_temp;
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public double getMintemp_c() {
        return mintemp_c;
    }
    public void setMintemp_c(double mintemp_c) {
        this.mintemp_c = mintemp_c;
    }
    public double getAvgtemp_c() {
        return avgtemp_c;
    }
    public void setAvgtemp_c(double avgtemp_c) {
        this.avgtemp_c = avgtemp_c;
    }
    public double getMax_temp() {
        return max_temp;
    }
    public void setMax_temp(double max_temp) {
        this.max_temp = max_temp;
    }



    
}
