package com.scx.Weather_App_backend.dto;

public class Root {
    
    private  Location location;
    private Current current;
    private Forecast forecast;


    public Root(Location location, Current current, Forecast forecast) {
        this.location = location;
        this.current = current;
        this.forecast = forecast;
    }


    public Root() {
    }


    public Location getLocation() {
        return location;
    }


    public void setLocation(Location location) {
        this.location = location;
    }


    public Current getCurrent() {
        return current;
    }


    public void setCurrent(Current current) {
        this.current = current;
    }


    public Forecast getForecast() {
        return forecast;
    }


    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    } 
    
    

    

    
}
