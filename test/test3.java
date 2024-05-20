package test;

import javax.swing.*;
import java.awt.*;

public class test3 {

    public static void main(final String args[]) {
        final String labels[] = { "A", "B", "C", "D", "E" };
        JFrame frame = new JFrame("Editable JComboBox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JComboBox<String> comboBox = new JComboBox(labels);

        comboBox.setEditable(false);
        frame.add(comboBox, BorderLayout.NORTH);

         String[] s = new String[2];

        comboBox.addActionListener(actionEvent -> {
            s[0] = (String) comboBox.getSelectedItem();
            System.out.println(s[0]);

        });

        frame.setSize(300, 200);
        frame.setVisible(true);

    }

}