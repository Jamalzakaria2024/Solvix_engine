// -----------------------------------------------------
// Mini Project: 8
// Question 1 : Create a scientific calculator with support for sin, cos, sqrt, and π.
// Question 2 : Display results clearly and handle invalid inputs gracefully.
// Written by: Hashem Ehab Alrefai  (ID: 2023904130)
// -----------------------------------------------------

package view; // This class belongs to the 'view' package (UI layer)

import net.objecthunter.exp4j.Expression; // Expression class from exp4j to evaluate math expressions
import net.objecthunter.exp4j.ExpressionBuilder; // Builds the math expression from a string
import net.objecthunter.exp4j.function.Function; // Used to define custom math functions

import javax.swing.*; // Swing components like JFrame, JTextField, JButton, etc.
import java.awt.*; // Layout managers and UI positioning tools

public class ScientificCalculatorPage extends JFrame { // GUI window class
    public ScientificCalculatorPage() {
        setTitle("Scientific Calculator"); // Set window title
        setSize(400, 580); // Set window size (width x height)
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Exit the application on close
        setLocationRelativeTo(null); // Center the window on the screen

        JTextField inputField = new JTextField(); // Input field for typing expressions
        inputField.setFont(new Font("Arial", Font.PLAIN, 20)); // Set font for input

        JTextField resultField = new JTextField("Result:"); // Output field to show result
        resultField.setFont(new Font("Arial", Font.BOLD, 18)); // Set font for result
        resultField.setEditable(false); // Make result field read-only
        resultField.setHorizontalAlignment(JTextField.CENTER); // Center-align result text

        JPanel buttonPanel = new JPanel(new GridLayout(7, 4, 5, 5)); // Panel with grid layout for buttons

        // Define all calculator buttons
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "^", "+",
            "(", ")", "sqrt", "=",
            "sin", "cos", "π"
        };

        // Create each button and define its behavior
        for (String label : buttons) {
            JButton btn = new JButton(label); // Create a button with the current label
            btn.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font

            btn.addActionListener(e -> { // What happens when a button is clicked
                String text = inputField.getText(); // Get current input text

                if (label.equals("=")) { // If "=" is clicked, evaluate the expression
                    try {
                        // Count open and close parentheses
                        int open = 0, close = 0;
                        for (char c : text.toCharArray()) {
                            if (c == '(') open++;
                            if (c == ')') close++;
                        }
                        // Auto-close unbalanced parentheses
                        while (open > close) {
                            text += ")";
                            close++;
                        }

                        // Define sin, cos, and sqrt functions (convert to radians)
                        Function sin = new Function("sin", 1) {
                            public double apply(double... args) {
                                return Math.sin(Math.toRadians(args[0]));
                            }
                        };
                        Function cos = new Function("cos", 1) {
                            public double apply(double... args) {
                                return Math.cos(Math.toRadians(args[0]));
                            }
                        };
                        Function sqrt = new Function("sqrt", 1) {
                            public double apply(double... args) {
                                return Math.sqrt(args[0]);
                            }
                        };

                        // Build the expression with the functions
                        Expression expr = new ExpressionBuilder(text)
                                .function(sin)
                                .function(cos)
                                .function(sqrt)
                                .build();

                        // Evaluate and display the result
                        double result = expr.evaluate();
                        resultField.setText("Result: " + result);
                    } catch (Exception ex) {
                        resultField.setText("Error"); // If any error occurs
                    }
                } else if (label.equals("sin") || label.equals("cos") || label.equals("sqrt")) {
                    inputField.setText(text + label + "("); // Add function call
                } else if (label.equals("π")) {
                    inputField.setText(text + "3.1415926535"); // Insert value of Pi
                } else {
                    inputField.setText(text + label); // Add number or operator to input
                }
            });

            buttonPanel.add(btn); // Add button to the button panel
        }

        JButton clearBtn = new JButton("Clear"); // Button to clear input field
        clearBtn.addActionListener(e -> inputField.setText("")); // Clear action

        JButton backBtn = new JButton("Back"); // Button to go back to welcome screen
        backBtn.addActionListener(e -> {
            dispose(); // Close current window
            new WelcomePage(); // Open welcome page
        });

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Panel for clear and back buttons
        controlPanel.add(clearBtn); // Add clear button
        controlPanel.add(backBtn); // Add back button

        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10)); // Panel for result + control
        bottomPanel.add(resultField, BorderLayout.NORTH); // Result at the top
        bottomPanel.add(controlPanel, BorderLayout.SOUTH); // Control buttons at the bottom

        setLayout(new BorderLayout(10, 10)); // Layout manager for the window
        add(inputField, BorderLayout.NORTH); // Input field at the top
        add(buttonPanel, BorderLayout.CENTER); // Buttons in the center
        add(bottomPanel, BorderLayout.SOUTH); // Result and control panel at the bottom

        setVisible(true); // Show the calculator window
    }
}
