package ru.academmits.kolesnikov.temperature.model;

public class TemperatureConverter implements Converter {
    @Override
    public double convertCelsiusToKelvin(double temperature) {
        return temperature + 273.15;
    }

    @Override
    public double convertCelsiusToFahrenheit(double temperature) {
        return (temperature * 1.8) + 32;
    }

    @Override
    public double convertFahrenheitToKelvin(double temperature) {
        return (temperature + 459.67) / 1.8;
    }

    @Override
    public double convertFahrenheitToCelsius(double temperature) {
        return (temperature - 32) / 1.8;
    }

    @Override
    public double convertKelvinToFahrenheit(double temperature) {
        return temperature * 1.8 - 459.67;
    }

    @Override
    public double convertKelvinToCelsius(double temperature) {
        return temperature - 273.15;
    }
}