package ru.academits.kolesnikov.temperature.controller;

import ru.academits.kolesnikov.temperature.model.Converter;
import ru.academits.kolesnikov.temperature.view.View;

public class TemperatureController implements Controller {
    private final Converter converter;
    private final View view;

    public TemperatureController(Converter converter, View view) {
        this.converter = converter;
        this.view = view;
    }

    @Override
    public void convert(String[] scales, double temperature) {
        double celsiusTemperature;

        if (scales[0].equals("Кельвина")) {
            celsiusTemperature = converter.convertKelvinToCelsius(temperature);
        } else if (scales[0].equals("Фаренгейта")) {
            celsiusTemperature = converter.convertFahrenheitToCelsius(temperature);
        } else {
            celsiusTemperature = temperature;
        }

        double resultTemperature = celsiusTemperature;

        if (scales[1].equals("Фаренгейта")) {
            resultTemperature = converter.convertCelsiusToFahrenheit(resultTemperature);
        } else if (scales[1].equals("Кельвина")) {
            resultTemperature = converter.convertCelsiusToKelvin(resultTemperature);
        }

        view.showTemperature(scales[1], resultTemperature);
    }

    public String[] getScaleNames(){
        return converter.getScaleNames();
    }
}
