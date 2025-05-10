package util;

import model.Formula;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Map;

public class FormulaEvaluator {
    public static double evaluate(Formula formula, Map<String, Double> inputs) throws Exception {
        ExpressionBuilder builder = new ExpressionBuilder(formula.formula);

        for (String key : inputs.keySet()) {
            builder.variable(key);
        }

        if (formula.constants != null) {
            for (String key : formula.constants.keySet()) {
                builder.variable(key);
            }
        }

        Expression expression = builder.build();

        for (Map.Entry<String, Double> entry : inputs.entrySet()) {
            expression.setVariable(entry.getKey(), entry.getValue());
        }

        if (formula.constants != null) {
            for (Map.Entry<String, Double> entry : formula.constants.entrySet()) {
                expression.setVariable(entry.getKey(), entry.getValue());
            }
        }

        return expression.evaluate();
    }
}
