package ru.academits.kolesnikov.temperature.model.scale;

public class FahrenheitScale implements Scale {
    @Override
    public double convertToCelsius(double temperature) {
        return (temperature * 1.8) + 32;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return (temperature - 32) / 1.8;
    }

    @Override
    public String toString() {
        return "Фаренгейта";
    }
}
