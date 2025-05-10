package view;

import javax.swing.*;
import java.awt.*;

public class WelcomePage extends JFrame {
    public WelcomePage() {
        setTitle("Slovix Engine");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with vertical layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Title
        JLabel titleLabel = new JLabel("Welcome to Slovix Engine", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Description
        JLabel descLabel = new JLabel("Smart calculators for formulas and science", JLabel.CENTER);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button: Open General Calculators
        JButton generalBtn = new JButton("Open General Calculators");
        generalBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        generalBtn.addActionListener(e -> {
            dispose();
            new CategoryPage();
        });

        // Button: Open Scientific Calculator
        JButton sciBtn = new JButton("Open Scientific Calculator");
        sciBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        sciBtn.addActionListener(e -> {
            dispose();
            new ScientificCalculatorPage();
        });

        // Spacer + Buttons
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(descLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(generalBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(sciBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Names (centered)
       
        JLabel name1 = new JLabel("Jamal Mwaffak Zakaria", JLabel.CENTER);
        JLabel name2 = new JLabel("Wesam Sharaf Al-Deen Al-Omari", JLabel.CENTER);
        JLabel name3 = new JLabel("Hashem Ehab Alrefai", JLabel.CENTER);

        name1.setAlignmentX(Component.CENTER_ALIGNMENT);
        name2.setAlignmentX(Component.CENTER_ALIGNMENT);
        name3.setAlignmentX(Component.CENTER_ALIGNMENT);

        name1.setFont(new Font("Arial", Font.PLAIN, 12));
        name2.setFont(new Font("Arial", Font.PLAIN, 12));
        name3.setFont(new Font("Arial", Font.PLAIN, 12));

        panel.add(name1);
        panel.add(name2);
        panel.add(name3);

        add(panel);
        setVisible(true);
    }
}
