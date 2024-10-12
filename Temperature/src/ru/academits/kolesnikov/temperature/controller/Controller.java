package ru.academits.kolesnikov.temperature.controller;

public interface Controller {
    void convert(String[] scales, double temperature);

    public String[] getScaleNames();
}
