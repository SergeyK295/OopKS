package ru.academmits.kolesnikov.temperature;

import ru.academmits.kolesnikov.temperature.controller.ControllerTemperature;
import ru.academmits.kolesnikov.temperature.model.Converter;
import ru.academmits.kolesnikov.temperature.model.TemperatureConverter;
import ru.academmits.kolesnikov.temperature.view.DesktopView;
import ru.academmits.kolesnikov.temperature.view.View;

public class Main {
    public static void main(String[] args) {
        Converter converter = new TemperatureConverter();
        View view = new DesktopView();

        ControllerTemperature controller = new ControllerTemperature(converter, view);
        view.setController(controller);

        view.start();
    }
}
