package ru.academits.kolesnikov.temperature.model;

public class Fahrenheit implements Scales {
    @Override
    public double convertToCelsius(double temperature) {
        return (temperature * 1.8) + 32;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return (temperature - 32) / 1.8;
    }
}
