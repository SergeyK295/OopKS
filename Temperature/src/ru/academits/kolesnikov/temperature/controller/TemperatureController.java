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
    public void convert(String convertFrom, String convertTo, double temperature) {
        temperature = converter.convert(convertFrom, convertTo, temperature);
        view.showTemperature(convertTo, temperature);
    }

    public String[] getScalesNames() {
        return converter.getScalesNames();
    }
}
