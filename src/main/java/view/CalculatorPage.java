// -----------------------------------------------------
// Mini Project: 4
// Question 1 : Search formulas by name using keyword input.
// Question 2 : Dynamically display inputs and equations for selected formulas.
// Question 3 : Evaluate user inputs and show result using FormulaEvaluator.
// Written by: Wesam Al-Omari (ID: 2023801020)
// -----------------------------------------------------

package view;

import model.Formula; // import an object that contain a details for each formula
import util.FormulaEvaluator; // import a class the evaluate the result 

// import all GUI components
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.CategoryPage;

/*
This class represents a user interface window for
displaying and executing formulas.
It inherits from JFrame, meaning it represents a standalone window.
*/
public class CalculatorPage extends JFrame {
    // constructor: it is called when the page opens and takes the list of equations to display.
    public CalculatorPage(List<Formula> formulas) {
        //window settings
        setTitle("Calculator"); // Set window title
        setSize(700, 600); // Set window size
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Exit app on close
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel(); // Main panel
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Vertical layout

        
        JTextField searchField = new JTextField(); // Search input
        JButton backBtn = new JButton("Back"); // Back button

        // Top bar contain : search word , input , back button
        JPanel topPanel = new JPanel(new BorderLayout()); // Top bar
        topPanel.add(new JLabel("Search:"), BorderLayout.WEST); // Label on left
        topPanel.add(searchField, BorderLayout.CENTER); // Search in center
        topPanel.add(backBtn, BorderLayout.EAST); // Back on right

        // an area to display formulas result and make the page scrollable
        JPanel resultsPanel = new JPanel(); // Container for formula cards
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS)); // Vertical
        JScrollPane scroll = new JScrollPane(resultsPanel); // Make scrollable

        panel.add(topPanel); // Add search and back topPanel object declared above 
        panel.add(scroll); // Add results area

        // Function to show formulas matching search
        Runnable render = () -> { // a function execute evey refresh display the formulas that matches search result
            resultsPanel.removeAll(); // Clear previous items
            String keyword = searchField.getText().toLowerCase(); // Get new text

            // Filter by name to display
            List<Formula> filtered = formulas.stream()
                    .filter(f -> f.name.toLowerCase().contains(keyword))
                    .collect(Collectors.toList());

            
            for (Formula formula : filtered) {
                JPanel fPanel = new JPanel(new GridBagLayout()); // One formula panel
                fPanel.setBorder(BorderFactory.createTitledBorder(formula.name)); // Title border
                GridBagConstraints gbc = new GridBagConstraints(); // Layout settings
                gbc.insets = new Insets(4, 4, 4, 4); // Padding
                gbc.fill = GridBagConstraints.HORIZONTAL; // Stretch inputs
                gbc.weightx = 1.0;
                gbc.gridy = 0;

                // Show description
                gbc.gridx = 0;
                gbc.gridwidth = 2;
                fPanel.add(new JLabel("Description: " + formula.description), gbc);
                gbc.gridy++;

                // Show equation
                fPanel.add(new JLabel("Equation: " + formula.equation), gbc);
                gbc.gridy++;

                Map<String, JTextField> inputs = new HashMap<>(); // Store inputs
                for (String param : formula.parameters) {
                    gbc.gridx = 0;
                    gbc.gridwidth = 1;
                    gbc.weightx = 0;
                    fPanel.add(new JLabel(param + ":"), gbc); // Label

                    gbc.gridx = 1;
                    gbc.weightx = 1.0;
                    JTextField field = new JTextField(15); // Input
                    inputs.put(param, field);
                    fPanel.add(field, gbc); // Add to panel

                    gbc.gridy++; // Next row
                }

                JButton calcBtn = new JButton("Calculate"); // Calculate button
                JLabel resultLabel = new JLabel("Result: "); // Show result

                gbc.gridx = 0;
                gbc.gridwidth = 2;
                gbc.gridy++;
                fPanel.add(calcBtn, gbc); // Add button

                gbc.gridy++;
                fPanel.add(resultLabel, gbc); // Add result

                // When button is clicked
                calcBtn.addActionListener(e -> {
                    try {
                        Map<String, Double> values = new HashMap<>();
                        for (Map.Entry<String, JTextField> entry : inputs.entrySet()) {
                            values.put(entry.getKey(), Double.parseDouble(entry.getValue().getText()));
                        }
                        double result = FormulaEvaluator.evaluate(formula, values); // Calculate
                        resultLabel.setText("Result: " + result + " " + formula.unit); // Show result
                    } catch (Exception ex) {
                        resultLabel.setText("Error: " + ex.getMessage()); // Show error
                    }
                });

                resultsPanel.add(fPanel); // Add formula to screen
            }

            resultsPanel.revalidate(); // Refresh
            resultsPanel.repaint(); // Redraw
        };

        // Search listener
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { render.run(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { render.run(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { render.run(); }
        });

        // Back button to CategoryPage
        backBtn.addActionListener(e -> {
            dispose();
            new CategoryPage();
        });

        render.run(); // Initial load
        add(panel); // Add to frame
        setVisible(true); // Show window
    }
}
