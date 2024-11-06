package ru.academits.kolesnikov.temperature.model.scale;

public class CelsiusScale implements Scale {
    @Override
    public String getScaleName() {
        return "Цельсия";
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature;
    }
}
