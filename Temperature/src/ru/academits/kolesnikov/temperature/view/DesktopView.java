package ru.academits.kolesnikov.temperature.view;

import ru.academits.kolesnikov.temperature.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class DesktopView implements View, ItemListener {
    private Controller controller;
    private JLabel resultLabel;
    private JPanel startPanel;
    private boolean isStarted;
    private String convertFromScale;
    private String convertToScale;

    @Override
    public void start() {
        if (isStarted) {
            throw new IllegalStateException("Метод start уже был вызван.");
        }

        isStarted = true;

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            JFrame frame = new JFrame("Конвертер температур");
            frame.setLocationRelativeTo(null);
            frame.setMinimumSize(new Dimension(450, 120));
            frame.setResizable(false);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            startPanel = new JPanel();
            startPanel.add(new JLabel("Конвертировать температуру из градусов"));

            String[] scalesNames = controller.getScalesNames();

            JComboBox<String> convertFromScalesComboBox = new JComboBox<>(scalesNames);
            convertFromScalesComboBox.setEditable(false);
            convertFromScale = scalesNames[0];
            convertFromScalesComboBox.addActionListener(e -> convertFromScale = (String) convertFromScalesComboBox.getSelectedItem());
            startPanel.add(convertFromScalesComboBox);

            JTextField temperatureField = new JTextField(10);
            startPanel.add(temperatureField);

            JPanel centerPanel = new JPanel();
            centerPanel.add(new JLabel("в градусы"));

            String temp = scalesNames[0];
            scalesNames[0] = scalesNames[1];
            scalesNames[1] = temp;

            JComboBox<String> convertToScalesComboBox = new JComboBox<>(scalesNames);
            convertToScalesComboBox.setEditable(false);
            convertToScale = scalesNames[0];
            convertToScalesComboBox.addActionListener(e -> convertToScale = (String) convertToScalesComboBox.getSelectedItem());

            centerPanel.add(convertToScalesComboBox);

            JButton converterButton = new JButton("Конвертировать");
            converterButton.addActionListener(e -> {
                try {
                    double temperature = Double.parseDouble(temperatureField.getText());
                    controller.convert(convertFromScale, convertToScale, temperature);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Температура должна быть числом", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });

            centerPanel.add(converterButton);

            resultLabel = new JLabel();
            JPanel resultPanel = new JPanel();
            resultPanel.add(resultLabel);

            Container container = frame.getContentPane();
            container.add(startPanel, BorderLayout.PAGE_START);
            container.add(centerPanel, BorderLayout.CENTER);
            container.add(resultPanel, BorderLayout.PAGE_END);

            frame.setVisible(true);
        });
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showTemperature(String name, double temperature) {
        resultLabel.setText("Температура в градусах " + name + " = " + temperature);
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        CardLayout layout = (CardLayout) startPanel.getLayout();
        layout.show(startPanel, (String) event.getItem());
    }
}