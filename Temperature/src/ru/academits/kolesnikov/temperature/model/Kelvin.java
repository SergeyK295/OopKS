package ru.academits.kolesnikov.temperature.model;

public class Kelvin implements Scales {
    @Override
    public double convertToCelsius(double temperature) {
        return temperature + 273.15;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature - 273.15;
    }
}
