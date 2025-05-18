// -----------------------------------------------------
// Mini Project: 4
// Question 1 : Display and search categorized formulas.
// Question 2 : Generate dynamic input fields for each formula.
// Question 3 : Evaluate results based on user input using FormulaEvaluator.
// Written by: Wesam Al-Omari (ID: 2023801020)
// -----------------------------------------------------

package view;  

import model.Formula; // Import the Formula data model
import util.FormulaEvaluator; // Import the utility for evaluating formulas

import javax.swing.*; 
import java.awt.*; 
import java.util.*; 
import java.util.List; // Specific List usage
import view.CategoryPage; // Import to return to category screen

public class CalculatorPage extends JFrame { // Main calculator screen
    public CalculatorPage(List<Formula> formulas) { // Constructor receives list of formulas
        setTitle("Select a category"); 
        setSize(700, 600); 
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Exit on close
        setLocationRelativeTo(null); // Center the window

        JPanel mainPanel = new JPanel(); // Main container panel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Vertical layout

        JTextField searchField = new JTextField(20); // Text field for keyword search
        JButton backButton = new JButton("Back"); // button to go back

        JPanel searchPanel = new JPanel(); // top search bar
        searchPanel.add(new JLabel("Search")); // Search label
        searchPanel.add(searchField); // add text field
        searchPanel.add(backButton); // add back button

        JPanel resultsPanel = new JPanel(); // container to hold formulas
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS)); // vertical layout
        JScrollPane scrollPane = new JScrollPane(resultsPanel); // scrollable panel

        mainPanel.add(searchPanel); // add search bar to main panel
        mainPanel.add(scrollPane); // add scrollable results panel
        add(mainPanel); // add main panel to JFrame

        // function to filter and display matching formulas
        Runnable showFormulas = () -> {
            resultsPanel.removeAll(); // Clear previous results
            String keyword = searchField.getText().toLowerCase(); // Get search input

            for (Formula formula : formulas) {
                if (!formula.name.toLowerCase().contains(keyword)) continue; // Skip non-matching formulas

                JPanel formulaPanel = new JPanel(); // Panel for each formula
                formulaPanel.setLayout(new BoxLayout(formulaPanel, BoxLayout.Y_AXIS)); // Vertical layout
                formulaPanel.setBorder(BorderFactory.createTitledBorder(formula.name)); // Border title

                formulaPanel.add(new JLabel("description: " + formula.description)); // Show description
                formulaPanel.add(new JLabel("Equation: " + formula.equation)); // Show equation

                Map<String, JTextField> inputFields = new HashMap<>(); // Store input fields

                for (String param : formula.parameters) {
                    JPanel paramPanel = new JPanel(); // Row for each parameter
                    paramPanel.add(new JLabel(param + ":")); // Label
                    JTextField field = new JTextField(10); // Input field
                    inputFields.put(param, field); // Save field by parameter name
                    paramPanel.add(field); // Add input to row
                    formulaPanel.add(paramPanel); // Add row to formula panel
                }

                JButton calculateButton = new JButton("calc"); 
                JLabel resultLabel = new JLabel("result: "); 

                // Action when user clicks "calc"
                calculateButton.addActionListener(e -> {
                    try {
                        Map<String, Double> values = new HashMap<>();
                        for (Map.Entry<String, JTextField> entry : inputFields.entrySet()) {
                            values.put(entry.getKey(), Double.parseDouble(entry.getValue().getText()));
                        }
                        double result = FormulaEvaluator.evaluate(formula, values); // Calculate result
                        resultLabel.setText("Result: " + result + " " + formula.unit); // Show result
                    } catch (Exception ex) {
                        resultLabel.setText("error : " + ex.getMessage()); // Show error if input invalid
                    }
                });

                formulaPanel.add(calculateButton); // Add calculate button
                formulaPanel.add(resultLabel); // Add result display
                resultsPanel.add(formulaPanel); // Add formula panel to results
            }

            resultsPanel.revalidate(); // Refresh layout
            resultsPanel.repaint(); // Redraw panel
        };

        // Listen for text changes in the search field
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { showFormulas.run(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { showFormulas.run(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { showFormulas.run(); }
        });

        // Action for back button
        backButton.addActionListener(e -> {
            dispose(); // Close current window
            new CategoryPage(); // Open previous category page
        });

        showFormulas.run(); // Load formulas initially
        setVisible(true); // Show the calculator window
    }
}
