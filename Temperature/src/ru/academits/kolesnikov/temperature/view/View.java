package ru.academits.kolesnikov.temperature.view;

import ru.academits.kolesnikov.temperature.controller.Controller;

public interface View {
    void start();

    void setController(Controller controller);

    void showTemperature(String scales, double temperature);
}