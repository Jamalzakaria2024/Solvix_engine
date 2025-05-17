// -----------------------------------------------------
// Mini Project: 6
// Question 1 : Evaluate a formula expression using exp4j.
// Question 2 : Automatically substitute user inputs and constants.
// Question 3 : Return the final computed result.
// Written by: Jamal Mwaffak Zakaria (ID: 2023804002)
// -----------------------------------------------------

package util; 

import model.Formula; // import the Formula class (contains formula details)
import net.objecthunter.exp4j.*; // Import exp4j for expression parsing and evaluation
import java.util.Map; // Import Map to receive input variables and constants

public class FormulaEvaluator { // Class responsible for evaluating formulas

    // Static method to evaluate a formula based on user inputs
    public static double evaluate(Formula formula, Map<String, Double> inputs) {
        
        // Create an expression builder using the formula string ("a + b")
        ExpressionBuilder builder = new ExpressionBuilder(formula.formula);

        // Register input variables into the builder
        inputs.keySet().forEach(builder::variable);

        // If there are constants ( pi = 3.14), register them too
        if (formula.constants != null) formula.constants.keySet().forEach(builder::variable);

        Expression expr = builder.build();// Build the actual expression object
        inputs.forEach(expr::setVariable); // Set user input values into the expression

        // Set constant values (if any) into the expression
        if (formula.constants != null) formula.constants.forEach(expr::setVariable);

        return expr.evaluate();// Evaluate the expression and return the result

    }
}
