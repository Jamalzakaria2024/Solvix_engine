package view;

import model.Formula; // importing fourmula class
import util.JsonLoader; // import a tool to load json file 

import javax.swing.*;
import java.awt.*;
//to work with lists and filter data
import java.util.List;
import java.util.stream.Collectors;

public class CategoryPage extends JFrame { // defines the CategoryPage class, which represents a new GUI window, It inherits from JFrame which means it can be displayed as a window.
    
    public CategoryPage() { // constructor that executes directly when the page is opened
        setTitle("Choose Category");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        List<Formula> formulas = JsonLoader.loadFormulas(); //Load all equations from a JSON file and convert them to Formula objects.
        List<String> categories = formulas.stream().map(f -> f.category).distinct().collect(Collectors.toList()); //Extract all categories from a list of equations , distinct() prevents duplication, and .collect converts it to a List.

        JPanel panel = new JPanel(new BorderLayout());
        JComboBox<String> categoryCombo = new JComboBox<>(categories.toArray(new String[0])); // create a dropdown list contain all the catefories extracted from JSON
        JButton nextBtn = new JButton("Show Formulas"); // button to show formulas
        JButton backBtn = new JButton("Back"); // button to go back

        
        // Create a subpanel (topPanel) containing: 1-title 'category' 2- dropdown menue 3- two butttons
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Category:"));
        topPanel.add(categoryCombo);
        topPanel.add(nextBtn);
        topPanel.add(backBtn);

        panel.add(topPanel, BorderLayout.NORTH);

        // when click on Back , the widow closed and opend welcomePage
        backBtn.addActionListener(e -> {
            dispose();
            new WelcomePage();
        });

        
        
        nextBtn.addActionListener(e -> {
            String selected = (String) categoryCombo.getSelectedItem(); // take the specifc category 
            List<Formula> filtered = formulas.stream()
                    .filter(f -> f.category.equals(selected))// filter equations by category
                    .collect(Collectors.toList());
            dispose(); // close the page
            new CalculatorPage(filtered); // open calculatorPage 
        });

        add(panel);
        setVisible(true);
    }
}
