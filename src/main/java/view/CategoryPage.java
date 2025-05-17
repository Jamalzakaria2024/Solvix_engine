// -----------------------------------------------------
// Mini Project: 3
// Question 1 : Load all formulas from the JSON file.
// Question 2 : Display unique categories in a dropdown menu.
// Question 3 : Open CalculatorPage based on selected category.
// Written by: Jamal Mwaffak Zakaria (ID: 2023804002)
// Written by: Wesam Al-Omari (ID: 2023801020)
// -----------------------------------------------------

package view; 

import model.Formula; // Import the Formula class 
import util.JsonLoader; // Import the class responsible for loading json data

import javax.swing.*; 
import java.awt.*;    
import java.util.List; //  work with lists
import java.util.stream.Collectors; // for filtering and collecting lists

public class CategoryPage extends JFrame { 

    public CategoryPage() { // Constructor runs when the page is open
        setTitle("Choose Category"); //  window title
        setSize(500, 400); // Set window size
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setLocationRelativeTo(null); // Center the window on the screen

        List<Formula> formulas = JsonLoader.loadFormulas(); // Load all formulas from JSON

        // Extract unique category names from formulas
        List<String> categories = formulas.stream()
                .map(f -> f.category) // Get category from each formula
                .distinct() // Remove duplicates
                .collect(Collectors.toList()); // Collect into a list

        JPanel panel = new JPanel(new BorderLayout()); // Create main panel with border layout

        // Create dropdown list for categories ، convert it to a String[] using toArray().
        JComboBox<String> categoryBox = new JComboBox<>(categories.toArray(new String[0]));

        JButton showBtn = new JButton("Show Formulas"); // Button to show formulas of selected category
        JButton backBtn = new JButton("Back"); // Button to go back to welcome screen

        // Top panel contains label + dropdown + buttons
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Category:")); // Label text
        topPanel.add(categoryBox); // Add dropdown menu
        topPanel.add(showBtn); // Add show button
        topPanel.add(backBtn); // Add back button
        panel.add(topPanel, BorderLayout.NORTH); // Place top panel at the top of main panel

        // Action for show button
        showBtn.addActionListener(e -> {
            String selected = (String) categoryBox.getSelectedItem(); // Get selected category
            List<Formula> filtered = formulas.stream()
                    .filter(f -> f.category.equals(selected)) // Keep formulas with that category
                    .collect(Collectors.toList()); // Make it a list
            dispose(); // Close current page
            new CalculatorPage(filtered); // Open calculator page with filtered formulas as parameter
        });

        // Action for back button
        backBtn.addActionListener(e -> {
            dispose(); // Close current window
            new WelcomePage(); // Go back to welcome screen
        });

        add(panel); // Add main panel to the window
        setVisible(true); // Show the window
    }
}
