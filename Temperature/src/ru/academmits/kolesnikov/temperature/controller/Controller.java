package ru.academmits.kolesnikov.temperature.controller;

import ru.academmits.kolesnikov.temperature.model.Converter;
import ru.academmits.kolesnikov.temperature.view.View;

public class Controller {
    private final Converter converter;
    private final View view;

    public Controller(Converter converter, View view) {
        this.converter = converter;
        this.view = view;
    }

    public void convertCelsiusToKelvin(double celsiusTemperature) {
        double kelvinTemperature = converter.convertCelsiusToKelvin(celsiusTemperature);
        view.showKelvinTemperature(kelvinTemperature);
    }

    public void convertCelsiusToFahrenheit(double celsiusTemperature) {
        double fahrenheitTemperature = converter.convertCelsiusToFahrenheit(celsiusTemperature);
        view.showFahrenheitTemperature(fahrenheitTemperature);
    }

    public void convertFahrenheitToKelvin(double celsiusTemperature) {
        double fahrenheitTemperature = converter.convertFahrenheitToKelvin(celsiusTemperature);
        view.showKelvinTemperature(fahrenheitTemperature);
    }

    public void convertFahrenheitToCelsius(double celsiusTemperature) {
        double fahrenheitTemperature = converter.convertFahrenheitToCelsius(celsiusTemperature);
        view.showCelsiusTemperature(fahrenheitTemperature);
    }

    public void convertKelvinToFahrenheit(double celsiusTemperature) {
        double fahrenheitTemperature = converter.convertKelvinToFahrenheit(celsiusTemperature);
        view.showFahrenheitTemperature(fahrenheitTemperature);
    }

    public void convertKelvinToCelsius(double celsiusTemperature) {
        double fahrenheitTemperature = converter.convertKelvinToCelsius(celsiusTemperature);
        view.showCelsiusTemperature(fahrenheitTemperature);
    }
}
