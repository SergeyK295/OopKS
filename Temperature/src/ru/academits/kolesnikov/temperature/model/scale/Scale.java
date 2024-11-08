package ru.academits.kolesnikov.temperature.model.scale;

public interface Scale {
    String getScaleName();

    double convertToCelsius(double temperature);

    double convertFromCelsius(double temperature);
}
