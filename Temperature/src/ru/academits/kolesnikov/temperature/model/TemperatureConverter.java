package ru.academits.kolesnikov.temperature.model;

import java.util.HashMap;

public class TemperatureConverter implements Converter {
    HashMap<String, Scales> scalesMap = new HashMap<>();
    String[] scalesNames;

    public TemperatureConverter() {
        scalesMap.put("Цельсия", new Celsius());
        scalesMap.put("Кельвина", new Kelvin());
        scalesMap.put("Фаренгейта", new Fahrenheit());

        scalesNames = new String[]{"Цельсия", "Кельвина", "Фаренгейта"};
    }

    @Override
    public String[] getScalesNames() {
        return scalesNames;
    }

    @Override
    public double convertToCelsius(String convertFrom, double temperature) {
        if (!scalesMap.containsKey(convertFrom)) {
            throw new IllegalStateException("Не возможно перевести из шкалы " + convertFrom);
        }

        return scalesMap.get(convertFrom).convertFromCelsius(temperature);
    }

    @Override
    public double convertFromCelsius(String convertTo, double temperature) {
        if (!scalesMap.containsKey(convertTo)) {
            throw new IllegalStateException("Не возможно перевести в шкалу " + convertTo);
        }

        return scalesMap.get(convertTo).convertToCelsius(temperature);
    }

    @Override
    public double convert(String convertFrom, String convertTo, double temperature) {
        double celsiusTemperature = convertToCelsius(convertFrom, temperature);
        return convertFromCelsius(convertTo, celsiusTemperature);
    }
}