package ru.academmits.kolesnikov.temperature.view;

import ru.academmits.kolesnikov.temperature.controller.Controller;
public interface View {
    void start();

    void setController(Controller controller);

    void showTemperature(String scales, double temperature);
}