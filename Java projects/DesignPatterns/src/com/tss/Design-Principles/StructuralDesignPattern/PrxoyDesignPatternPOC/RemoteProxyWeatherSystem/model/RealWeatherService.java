package com.tss.StructuralDesignPattern.PrxoyDesignPatternPOC.RemoteProxyWeatherSystem.model;

public class RealWeatherService implements WeatherService {
    @Override
    public String getWeather(String city) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Weather in " + city + " is Sunny, 30°C.";
    }
}
