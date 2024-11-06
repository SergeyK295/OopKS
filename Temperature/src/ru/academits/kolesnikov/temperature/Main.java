package ru.academits.kolesnikov.temperature;

import ru.academits.kolesnikov.temperature.controller.TemperatureController;
import ru.academits.kolesnikov.temperature.model.*;
import ru.academits.kolesnikov.temperature.model.scale.CelsiusScale;
import ru.academits.kolesnikov.temperature.model.scale.FahrenheitScale;
import ru.academits.kolesnikov.temperature.model.scale.KelvinScale;
import ru.academits.kolesnikov.temperature.model.scale.Scale;
import ru.academits.kolesnikov.temperature.view.DesktopView;
import ru.academits.kolesnikov.temperature.view.View;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Scale> scales = new ArrayList<>();
        scales.addFirst(new KelvinScale());
        scales.addFirst(new FahrenheitScale());
        scales.addFirst(new CelsiusScale());

        Converter converter = new TemperatureConverter(scales);
        View view = new DesktopView();

        TemperatureController controller = new TemperatureController(converter, view);
        view.setController(controller);

        view.start();
    }
}
