package ru.academits.kolesnikov.temperature.model;

public interface Converter {
    double convertCelsiusToKelvin(double temperature);

    double convertCelsiusToFahrenheit(double temperature);

    double convertFahrenheitToKelvin(double temperature);

    double convertFahrenheitToCelsius(double temperature);

    double convertKelvinToFahrenheit(double temperature);

    double convertKelvinToCelsius(double temperature);

    String[] getScaleNames();
}
