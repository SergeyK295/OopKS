package ru.academits.kolesnikov.temperature.model;

import ru.academits.kolesnikov.temperature.model.scale.Scale;

public record TemperatureConverter(Scale[] scales) implements Converter {
    @Override
    public double convert(Scale convertFromScale, Scale convertToScale, double temperature) {
        double celsiusTemperature = convertFromScale.convertFromCelsius(temperature);
        return convertToScale.convertToCelsius(celsiusTemperature);
    }
}