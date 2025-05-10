package view;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;

import javax.swing.*;
import java.awt.*;

public class ScientificCalculatorPage extends JFrame {
    public ScientificCalculatorPage() {
        setTitle("Scientific Calculator");
        setSize(400, 580);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Input field
        JTextField inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 20));

        // Result field
        JTextField resultField = new JTextField("Result:");
        resultField.setFont(new Font("Arial", Font.BOLD, 18));
        resultField.setEditable(false);
        resultField.setHorizontalAlignment(JTextField.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(7, 4, 5, 5));
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "^", "+",
            "(", ")", "sqrt", "=",
            "sin", "cos", "π"
        };

        for (String label : buttons) {
            JButton btn = new JButton(label);
            btn.setFont(new Font("Arial", Font.PLAIN, 16));

            btn.addActionListener(e -> {
                String text = inputField.getText();

                if (label.equals("=")) {
                    try {
                        // Auto-close unbalanced parentheses
                        int open = 0, close = 0;
                        for (char c : text.toCharArray()) {
                            if (c == '(') open++;
                            if (c == ')') close++;
                        }
                        while (open > close) {
                            text += ")";
                            close++;
                        }

                        // Custom functions
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

                        Expression expr = new ExpressionBuilder(text)
                                .function(sin)
                                .function(cos)
                                .function(sqrt)
                                .build();

                        double result = expr.evaluate();
                        resultField.setText("Result: " + result);
                    } catch (Exception ex) {
                        resultField.setText("Error");
                    }
                } else if (label.equals("sin") || label.equals("cos") || label.equals("sqrt")) {
                    inputField.setText(text + label + "(");
                } else if (label.equals("π")) {
                    inputField.setText(text + "3.1415926535");
                } else {
                    inputField.setText(text + label);
                }
            });

            buttonPanel.add(btn);
        }

        // Control buttons
        JButton clearBtn = new JButton("Clear");
        clearBtn.addActionListener(e -> inputField.setText(""));

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> {
            dispose();
            new WelcomePage();
        });

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        controlPanel.add(clearBtn);
        controlPanel.add(backBtn);

        // Bottom panel contains result + controls
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.add(resultField, BorderLayout.NORTH);
        bottomPanel.add(controlPanel, BorderLayout.SOUTH);

        // Layout
        setLayout(new BorderLayout(10, 10));
        add(inputField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
