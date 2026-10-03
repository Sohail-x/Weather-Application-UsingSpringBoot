package com.scx.Weather_App_backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scx.Weather_App_backend.dto.WeatherForeCastResponce;
import com.scx.Weather_App_backend.dto.WeatherResponce;
import com.scx.Weather_App_backend.service.WeatherService;

@RestController 
@CrossOrigin (origins = "*")
@RequestMapping ("/weather")
public class WeatherController {
    
    private WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @GetMapping ("/get/{city}")
    public WeatherResponce getWeather(@PathVariable String city){
        return service.getData(city);
    }

    @RequestMapping ("/forecast")
    public WeatherForeCastResponce getForeCast(@RequestParam String city, @RequestParam int days){
        return service.getForeCastData(city,days);
    }
}
