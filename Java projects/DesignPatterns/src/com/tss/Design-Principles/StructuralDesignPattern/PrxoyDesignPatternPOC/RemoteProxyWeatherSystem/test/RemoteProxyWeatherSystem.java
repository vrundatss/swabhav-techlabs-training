package com.tss.StructuralDesignPattern.PrxoyDesignPatternPOC.RemoteProxyWeatherSystem.test;

import com.tss.StructuralDesignPattern.PrxoyDesignPatternPOC.RemoteProxyWeatherSystem.model.WeatherService;
import com.tss.StructuralDesignPattern.PrxoyDesignPatternPOC.RemoteProxyWeatherSystem.model.WeatherServiceProxy;

public class RemoteProxyWeatherSystem {
    public static void main(String[] args) {
        WeatherService weatherService = new WeatherServiceProxy();

        System.out.println("\nRequesting weather of Rajkot...\n");

        System.out.println(weatherService.getWeather("Rajkot"));

        System.out.println("\nRequesting weather of Mumbai...\n");

        System.out.println(weatherService.getWeather("Mumbai"));

    }
}
