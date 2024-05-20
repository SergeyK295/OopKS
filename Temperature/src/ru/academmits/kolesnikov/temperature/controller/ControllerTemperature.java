package ru.academmits.kolesnikov.temperature.controller;

import ru.academmits.kolesnikov.temperature.model.Converter;
import ru.academmits.kolesnikov.temperature.view.View;

public class ControllerTemperature implements Controller {
    private final Converter converter;
    private final View view;

    public ControllerTemperature(Converter converter, View view) {
        this.converter = converter;
        this.view = view;
    }

    @Override
    public void convert(String[] scales, double temperature) {
        double finalTemperature;

        if (scales[0].equals("Кельвина")) {
            finalTemperature = converter.convertKelvinToCelsius(temperature);
        } else if (scales[0].equals("Фаренгейта")) {
            finalTemperature = converter.convertFahrenheitToCelsius(temperature);
        } else {
            finalTemperature = temperature;
        }

        if (scales[1].equals("Фаренгейта")) {
            finalTemperature = converter.convertCelsiusToFahrenheit(finalTemperature);
        } else if (scales[1].equals("Кельвина")) {
            finalTemperature = converter.convertCelsiusToKelvin(finalTemperature);
        }

        view.showTemperature(scales[1], finalTemperature);
    }
}
