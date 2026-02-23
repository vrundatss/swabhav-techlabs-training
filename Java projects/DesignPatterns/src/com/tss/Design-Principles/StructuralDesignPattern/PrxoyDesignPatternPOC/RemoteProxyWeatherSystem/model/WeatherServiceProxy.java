package com.tss.StructuralDesignPattern.PrxoyDesignPatternPOC.RemoteProxyWeatherSystem.model;

public class WeatherServiceProxy implements WeatherService {
    private RealWeatherService remoteServer;

    @Override
    public String getWeather(String city) {

        System.out.println("Connecting to remote weather service...");
        if(remoteServer == null){
            remoteServer  = new RealWeatherService();
        }

        String weather = remoteServer.getWeather(city);
        System.out.println("Response retrieved from remote service.");
        return  weather;
    }
}

