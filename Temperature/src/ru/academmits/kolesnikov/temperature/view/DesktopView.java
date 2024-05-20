package ru.academmits.kolesnikov.temperature.view;

import ru.academmits.kolesnikov.temperature.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class DesktopView implements View, ItemListener {
    private Controller controller;
    private JLabel resultLabel;
    private static JPanel startPanel;
    String[] scales = new String[2];

    @Override
    public void start() {
        String[] temperatureScale = new String[]{"Кельвина", "Фаренгейта", "Цельсия"};

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            JFrame frame = new JFrame("Конвертер температур");
            frame.setLocationRelativeTo(null);
            frame.setMinimumSize(new Dimension(700, 200));
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            startPanel = new JPanel();
            startPanel.add(new JLabel("Конвертировать температуру из градусов"));

            JComboBox<String> comboBox = new JComboBox<>(temperatureScale);
            comboBox.setEditable(false);
            scales[0] = temperatureScale[0];
            comboBox.addActionListener(e -> {
                scales[0] = (String) comboBox.getSelectedItem();
            });
            startPanel.add(comboBox);

            JTextField temperatureField = new JTextField(10);
            startPanel.add(temperatureField);

            JPanel centerPanel = new JPanel();
            centerPanel.add(new JLabel("в градусы"));

            JComboBox<String> comboBox2 = new JComboBox<>(temperatureScale);
            comboBox2.setEditable(false);
            scales[1] = temperatureScale[0];
            comboBox2.addActionListener(e -> {
                scales[1] = (String) comboBox2.getSelectedItem();
            });

            centerPanel.add(comboBox2);

            JButton startButton = new JButton("Старт");
            startButton.addActionListener(e -> {
                try {
                    double temperature = Double.parseDouble(temperatureField.getText());
                    controller.convert(scales, temperature);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Температура должна быть числом", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });

            centerPanel.add(startButton);

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
    public void showTemperature(String scales, double temperature) {
        resultLabel.setText("Температура в градусах " + scales + " = " + temperature);
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        CardLayout layout = (CardLayout) (startPanel.getLayout());
        layout.show(startPanel, (String) event.getItem());
    }
}