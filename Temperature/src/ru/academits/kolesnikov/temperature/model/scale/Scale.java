package ru.academits.kolesnikov.temperature.model.scale;

public interface Scale {
    double convertToCelsius(double temperature);

    double convertFromCelsius(double temperature);
}
