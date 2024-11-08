package ru.academits.kolesnikov.temperature.controller;

import ru.academits.kolesnikov.temperature.model.scale.Scale;

import java.util.ArrayList;

public interface Controller {
    Scale[] getScales();

    void convert(Scale convertFromScale, Scale convertToScale, double temperature);
}
