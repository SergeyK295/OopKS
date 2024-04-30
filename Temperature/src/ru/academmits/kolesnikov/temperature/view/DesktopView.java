package ru.academmits.kolesnikov.temperature.view;

import ru.academmits.kolesnikov.temperature.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class DesktopView implements View, ItemListener {
    private Controller controller;
    private JLabel resultLabel;
    private static JPanel tabs;

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            JFrame frame = new JFrame("Конвертер температур");
            frame.setSize(450, 150);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JComboBox<String> comboBox = new JComboBox<>(new String[]{"Цельсия", "Кельвина", "Фаренгейта"});
            comboBox.setEditable(false);
            comboBox.addItemListener(this);

            JTextField temperatureField = new JTextField(10);

            JPanel startPanel = new JPanel();
            startPanel.add(new JLabel("Конвертировать температуру из градусов"));
            startPanel.add(comboBox);
            startPanel.add(temperatureField);

            JButton convertCelsiusToKelvin = new JButton("Кельвина");
            convertCelsiusToKelvin.addActionListener(e -> {
                try {
                    double celsiusTemperature = Double.parseDouble(temperatureField.getText());
                    controller.convertCelsiusToKelvin(celsiusTemperature);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Температура должна быть числом", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });

            JButton convertCelsiusToFahrenheit = new JButton("Фаренгейта");
            convertCelsiusToFahrenheit.addActionListener(e -> {
                try {
                    double celsiusTemperature = Double.parseDouble(temperatureField.getText());
                    controller.convertCelsiusToFahrenheit(celsiusTemperature);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Температура должна быть числом", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });

            JPanel tab1 = new JPanel();
            tab1.add(new JLabel("в градус"));
            tab1.add(convertCelsiusToKelvin);
            tab1.add(convertCelsiusToFahrenheit);

            JButton convertFahrenheitToKelvin = new JButton("Кельвина");
            convertFahrenheitToKelvin.addActionListener(e -> {
                try {
                    double celsiusTemperature = Double.parseDouble(temperatureField.getText());
                    controller.convertFahrenheitToKelvin(celsiusTemperature);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Температура должна быть числом", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });

            JButton convertFahrenheitToCelsius = new JButton("Цельсии");
            convertFahrenheitToCelsius.addActionListener(e -> {
                try {
                    double celsiusTemperature = Double.parseDouble(temperatureField.getText());
                    controller.convertFahrenheitToCelsius(celsiusTemperature);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Температура должна быть числом", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });

            JPanel tab2 = new JPanel();
            tab2.add(new JLabel("в градус"));
            tab2.add(convertFahrenheitToKelvin);
            tab2.add(convertFahrenheitToCelsius);

            JButton convertKelvinToFahrenheit = new JButton("Фаренгейта");
            convertKelvinToFahrenheit.addActionListener(e -> {
                try {
                    double celsiusTemperature = Double.parseDouble(temperatureField.getText());
                    controller.convertKelvinToFahrenheit(celsiusTemperature);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Температура должна быть числом", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });

            JButton convertKelvinToCelsius = new JButton("Цельсии");
            convertKelvinToCelsius.addActionListener(e -> {
                try {
                    double celsiusTemperature = Double.parseDouble(temperatureField.getText());
                    controller.convertKelvinToCelsius(celsiusTemperature);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Температура должна быть числом", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });

            JPanel tab3 = new JPanel();
            tab3.add(new JLabel("в градус"));
            tab3.add(convertKelvinToFahrenheit);
            tab3.add(convertKelvinToCelsius);

            tabs = new JPanel(new CardLayout());
            tabs.add(tab1);
            tabs.add(tab2);
            tabs.add(tab3);

            resultLabel = new JLabel();
            JPanel resultPanel = new JPanel();
            resultPanel.add(resultLabel);

            Container container = frame.getContentPane();
            container.add(startPanel, BorderLayout.PAGE_START);
            container.add(tabs, BorderLayout.CENTER);
            container.add(resultPanel, BorderLayout.PAGE_END);

            frame.setVisible(true);
        });
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showKelvinTemperature(double temperature) {
        resultLabel.setText("Температура в градусах Кельвина = " + temperature);
    }

    @Override
    public void showFahrenheitTemperature(double temperature) {
        resultLabel.setText("Температура в градусах Фаренгейта = " + temperature);
    }

    @Override
    public void showCelsiusTemperature(double temperature) {
        resultLabel.setText("Температура в градусах Цельсия = " + temperature);
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        CardLayout layout = (CardLayout) (tabs.getLayout());
        layout.show(tabs, (String) event.getItem());
    }
}