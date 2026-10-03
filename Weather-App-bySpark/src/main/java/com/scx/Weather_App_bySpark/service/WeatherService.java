package com.scx.Weather_App_bySpark.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.scx.Weather_App_bySpark.dto.DayTemp;
import com.scx.Weather_App_bySpark.dto.Forecast;
import com.scx.Weather_App_bySpark.dto.Forecastday;
import com.scx.Weather_App_bySpark.dto.Root;
import com.scx.Weather_App_bySpark.dto.WeatherForeCastResponce;
import com.scx.Weather_App_bySpark.dto.WeatherResponce;

@Service 
public class WeatherService {

    @Value ("${weather.api.key}")
    private String apiKey;


    @Value ("${weather.api.url}")
    private String apiUrl;

    
    @Value ("${forecast.api.url}")
    private String foreCastApiUrl;

    private RestTemplate template = new RestTemplate();


    public WeatherResponce getData(String city) {
       String url = apiUrl+"?key="+apiKey+"&q="+city;
       Root responce = template.getForObject(url, Root.class);
       
       WeatherResponce weatherResponce = new WeatherResponce();

       weatherResponce.setCity(responce.getLocation().name);
       weatherResponce.setCountry(responce.getLocation().country);
       weatherResponce.setLocaltime(responce.getLocation().localtime);
       weatherResponce.setTemp_c(responce.getCurrent().temp_c);
       weatherResponce.setHumidity(responce.getCurrent().humidity);
       weatherResponce.setWind_kph((responce.getCurrent().wind_kph));
       weatherResponce.setText(responce.getCurrent().getCondition().getText());
       weatherResponce.setRegion((responce.getLocation().region));

       return weatherResponce;
    }

    public WeatherForeCastResponce getForeCastData(String city,int days){
        
        WeatherForeCastResponce weatherForeCastResponce = new WeatherForeCastResponce();
        WeatherResponce weatherResponce = getData(city);
        weatherForeCastResponce.setWeatherResponce(weatherResponce);


        List<DayTemp> dayList = new ArrayList<>();
        String url1 = foreCastApiUrl+"?key="+apiKey+"&q="+city+"&days="+days;
        Root foreCastResponce = template.getForObject(url1, Root.class);

        Forecast foreCast = foreCastResponce.getForecast();
        
        for(Forecastday days1 : foreCast.getForecastday()){

            DayTemp d = new DayTemp();


            d.setMintemp_c(days1.getDay().mintemp_c);
            d.setAvgtemp_c(days1.getDay().avgtemp_c);
            d.setMax_temp(days1.getDay().maxtemp_c);
            d.setDate(days1.getDate());

            dayList.add(d);
        }

        weatherForeCastResponce.setDayTemp(dayList);
        
        return weatherForeCastResponce;
    }
    
}
