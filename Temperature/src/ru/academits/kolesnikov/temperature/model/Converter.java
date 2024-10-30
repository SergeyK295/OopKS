package ru.academits.kolesnikov.temperature.model;

public interface Converter {
    String[] getScalesNames();

    double convertToCelsius(String convertFrom, double temperature);

    double convertFromCelsius(String convertTo, double temperature);

    double convert (String convertFrom, String convertTo, double temperature);
}
