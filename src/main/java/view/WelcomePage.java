// -----------------------------------------------------
// Mini Project: 2
// Question 1 :  WelcomePage runs as a first screen.
// Question 2 :  user will choose where to go .
// Written by: Wesam Al-Omari (ID: 2023801020)
// Written by: Jamal Mwaffak Zakaria (ID: 2023804002)
// -----------------------------------------------------


package view; 

import javax.swing.*; // Import all Swing components (JLabel, JButton, etc.)
import java.awt.*;    // Import AWT layout and design tools

public class WelcomePage extends JFrame { // GUI window class that extends JFrame
    public WelcomePage() { // Constructor: runs when this page is opened
        setTitle("Slovix Engine"); // Set the window title
        setSize(500, 400); // Set width and height of the window
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Exit the app when window is closed
        setLocationRelativeTo(null); // Center the window on screen

        JPanel panel = new JPanel(); // Main container panel
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Set vertical layout
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Add padding

        JLabel titleLabel = new JLabel("Welcome to Slovix Engine", JLabel.CENTER); // Main title
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); 
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 

        JLabel descLabel = new JLabel("Smart calculators for formulas and science", JLabel.CENTER); // Description text
        descLabel.setFont(new Font("Arial", Font.PLAIN, 14)); 
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 

        JButton generalBtn = new JButton("Open General Calculators"); // Button to open formula calculator
        generalBtn.setAlignmentX(Component.CENTER_ALIGNMENT); 
        generalBtn.addActionListener(e -> { // Action when button is clicked
            dispose(); // Close this window
            new CategoryPage(); // Open CategoryPage
        });

        JButton sciBtn = new JButton("Open Scientific Calculator"); // Button for scientific calculator
        sciBtn.setAlignmentX(Component.CENTER_ALIGNMENT); 
        sciBtn.addActionListener(e -> { // Action when clicked
            dispose(); // Close this window
            new ScientificCalculatorPage(); // Open ScientificCalculatorPage
        });

        panel.add(titleLabel); // Add title
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // (10 pixels vertical space) 
        panel.add(descLabel); // Add description
        panel.add(Box.createRigidArea(new Dimension(0, 30))); // (30 pixels vertical space) 
        panel.add(generalBtn); // Add general calculator button
        panel.add(Box.createRigidArea(new Dimension(0, 15))); //  (15 pixels vertical space) 
        panel.add(sciBtn); // Add scientific calculator button
        panel.add(Box.createRigidArea(new Dimension(0, 30))); //  (30 pixels vertical space) 

        JLabel name1 = new JLabel("Jamal Mwaffak Zakaria", JLabel.CENTER); 
        JLabel name2 = new JLabel("Wesam Sharaf Al-Deen Al-Omari", JLabel.CENTER); 
        JLabel name3 = new JLabel("Hashem Ehab Alrefai", JLabel.CENTER); 

        // Loop through all name labels: set alignment, font, and add them to the panel
        for (JLabel name : new JLabel[]{name1, name2, name3}) {
            name.setAlignmentX(Component.CENTER_ALIGNMENT);
            name.setFont(new Font("Arial", Font.PLAIN, 12));
            panel.add(name);
}

        add(panel); // Add the panel to the window , display the content    
        setVisible(true); // Display the window
    }
}
