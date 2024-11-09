package ru.academits.kolesnikov.temperature.controller;

import ru.academits.kolesnikov.temperature.model.scale.Scale;

public interface Controller {
    Scale[] getScales();

    void convert(Scale convertFromScale, Scale convertToScale, double temperature);
}
