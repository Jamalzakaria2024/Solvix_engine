package view;

import model.Formula;
import util.FormulaEvaluator;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import view.CategoryPage;

public class CalculatorPage extends JFrame {
    
    public CalculatorPage(List<Formula> formulas) {
        // إعداد النافذة الأساسية
        setTitle("آلة حاسبة");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // لوحة رئيسية
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        // حقل البحث
        JTextField searchField = new JTextField(20);
        JButton backButton = new JButton("رجوع");
        
        // لوحة البحث العلوية
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("بحث:"));
        searchPanel.add(searchField);
        searchPanel.add(backButton);
        
        // منطقة النتائج
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        
        // إضافة المكونات للوحة الرئيسية
        mainPanel.add(searchPanel);
        mainPanel.add(scrollPane);
        add(mainPanel);
        
        // وظيفة لعرض الصيغ
        Runnable showFormulas = () -> {
            resultsPanel.removeAll();
            String keyword = searchField.getText().toLowerCase();
            
            for (Formula formula : formulas) {
                if (!formula.name.toLowerCase().contains(keyword)) continue;
                
                JPanel formulaPanel = new JPanel();
                formulaPanel.setLayout(new BoxLayout(formulaPanel, BoxLayout.Y_AXIS));
                formulaPanel.setBorder(BorderFactory.createTitledBorder(formula.name));
                
                formulaPanel.add(new JLabel("description: " + formula.description));
                formulaPanel.add(new JLabel("Equation: " + formula.equation));
                
                Map<String, JTextField> inputFields = new HashMap<>();
                for (String param : formula.parameters) {
                    JPanel paramPanel = new JPanel();
                    paramPanel.add(new JLabel(param + ":"));
                    JTextField field = new JTextField(10);
                    inputFields.put(param, field);
                    paramPanel.add(field);
                    formulaPanel.add(paramPanel);
                }
                
                JButton calculateButton = new JButton("calc");
                JLabel resultLabel = new JLabel("result: ");
                
                calculateButton.addActionListener(e -> {
                    try {
                        Map<String, Double> values = new HashMap<>();
                        for (Map.Entry<String, JTextField> entry : inputFields.entrySet()) {
                            values.put(entry.getKey(), Double.parseDouble(entry.getValue().getText()));
                        }
                        double result = FormulaEvaluator.evaluate(formula, values);
                        resultLabel.setText("Result: " + result + " " + formula.unit);
                    } catch (Exception ex) {
                        resultLabel.setText("error : " + ex.getMessage());
                    }
                });
                
                formulaPanel.add(calculateButton);
                formulaPanel.add(resultLabel);
                resultsPanel.add(formulaPanel);
            }
            
            resultsPanel.revalidate();
            resultsPanel.repaint();
        };
        
        // إضافة مستمع لحقل البحث
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { showFormulas.run(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { showFormulas.run(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { showFormulas.run(); }
        });
        
        // زر الرجوع
        backButton.addActionListener(e -> {
            dispose();
            new CategoryPage();
        });
        
        showFormulas.run();
        setVisible(true);
    }
}