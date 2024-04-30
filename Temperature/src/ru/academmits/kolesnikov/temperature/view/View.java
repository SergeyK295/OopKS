package ru.academmits.kolesnikov.temperature.view;

import ru.academmits.kolesnikov.temperature.controller.Controller;

public interface View {
    void start();

    void setController(Controller controller);

    void showKelvinTemperature(double temperature);

    void showFahrenheitTemperature(double temperature);

    void showCelsiusTemperature(double temperature);
}
