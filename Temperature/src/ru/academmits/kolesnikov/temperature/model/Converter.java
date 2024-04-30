package ru.academmits.kolesnikov.temperature.model;

public interface Converter {
    double convertCelsiusToKelvin(double celsiusTemperature);

    double convertCelsiusToFahrenheit(double celsiusTemperature);

    double convertFahrenheitToKelvin(double celsiusTemperature);

    double convertFahrenheitToCelsius(double celsiusTemperature);

    double convertKelvinToFahrenheit(double celsiusTemperature);

    double convertKelvinToCelsius(double celsiusTemperature);
}
