package ru.academits.kolesnikov.temperature.model;

import ru.academits.kolesnikov.temperature.model.scale.Scale;

public interface Converter {
    Scale[] scales();

    double convert(Scale convertFromScale, Scale convertToScale, double temperature);
}
