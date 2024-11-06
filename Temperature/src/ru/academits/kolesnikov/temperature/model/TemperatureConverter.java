package ru.academits.kolesnikov.temperature.model;

import ru.academits.kolesnikov.temperature.model.scale.Scale;

import java.util.ArrayList;
import java.util.HashMap;

public class TemperatureConverter implements Converter {
    private final HashMap<String, Scale> scalesMap = new HashMap<>();
    private final String[] scalesNames;

    public TemperatureConverter(ArrayList<Scale> scales) {
        int scalesSize = scales.size();
        scalesNames = new String[scalesSize];

        for (int i = 0; i < scalesSize; i++) {
            Scale scale = scales.get(i);
            String scaleName = scale.getScaleName();

            scalesMap.put(scaleName, scale);
            scalesNames[i] = scaleName;
        }
    }

    @Override
    public String[] getScalesNames() {
        return scalesNames;
    }

    @Override
    public double convertToCelsius(String convertFromScale, double temperature) {
        if (!scalesMap.containsKey(convertFromScale)) {
            throw new IllegalArgumentException("Невозможно перевести из шкалы " + convertFromScale);
        }

        return scalesMap.get(convertFromScale).convertFromCelsius(temperature);
    }

    @Override
    public double convertFromCelsius(String convertToScale, double temperature) {
        if (!scalesMap.containsKey(convertToScale)) {
            throw new IllegalArgumentException("Невозможно перевести в шкалу " + convertToScale);
        }

        return scalesMap.get(convertToScale).convertToCelsius(temperature);
    }

    @Override
    public double convert(String convertFromScale, String convertToScale, double temperature) {
        double celsiusTemperature = convertToCelsius(convertFromScale, temperature);
        return convertFromCelsius(convertToScale, celsiusTemperature);
    }
}