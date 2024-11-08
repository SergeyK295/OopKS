package ru.academits.kolesnikov.temperature;

import ru.academits.kolesnikov.temperature.controller.TemperatureController;
import ru.academits.kolesnikov.temperature.model.*;
import ru.academits.kolesnikov.temperature.model.scale.CelsiusScale;
import ru.academits.kolesnikov.temperature.model.scale.FahrenheitScale;
import ru.academits.kolesnikov.temperature.model.scale.KelvinScale;
import ru.academits.kolesnikov.temperature.model.scale.Scale;
import ru.academits.kolesnikov.temperature.view.DesktopView;
import ru.academits.kolesnikov.temperature.view.View;

public class Main {
    public static void main(String[] args) {
        Scale[] scales = new Scale[]{new CelsiusScale(), new KelvinScale(), new FahrenheitScale()};

        Converter converter = new TemperatureConverter(scales);
        View view = new DesktopView();

        TemperatureController controller = new TemperatureController(converter, view);
        view.setController(controller);

        view.start();
    }
}
