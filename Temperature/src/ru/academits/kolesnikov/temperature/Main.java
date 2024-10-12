package ru.academits.kolesnikov.temperature;

import ru.academits.kolesnikov.temperature.controller.TemperatureController;
import ru.academits.kolesnikov.temperature.model.Converter;
import ru.academits.kolesnikov.temperature.model.TemperatureConverter;
import ru.academits.kolesnikov.temperature.view.DesktopView;
import ru.academits.kolesnikov.temperature.view.View;

public class Main {
    public static void main(String[] args) {
        Converter converter = new TemperatureConverter();
        View view = new DesktopView();

        TemperatureController controller = new TemperatureController(converter, view);
        view.setController(controller);

        view.start();
    }
}
