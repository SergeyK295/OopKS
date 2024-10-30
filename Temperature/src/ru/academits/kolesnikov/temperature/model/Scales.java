package ru.academits.kolesnikov.temperature.model;

public interface Scales {
    double convertToCelsius(double temperature);

    double convertFromCelsius(double temperature);
}
