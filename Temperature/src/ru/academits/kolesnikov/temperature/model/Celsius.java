package ru.academits.kolesnikov.temperature.model;

public class Celsius implements Scales {
    @Override
    public double convertToCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature;
    }
}
