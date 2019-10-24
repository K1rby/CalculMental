package model;

import java.io.Serializable;
import java.util.List;
import java.util.Stack;

public class ExpressionBean implements Serializable {

    private String[] array;
    private Stack<Double> stack = new Stack<Double>();
    private Double resultat;
    
    public Double calculExpression() {
        String expression = "35 7 + 8 * rac";

        array = expression.split(" ", 6);

        Double intA = 0.0;
        Double intB = 0.0;
        Double c;
        for (String a : array) {
            switch (a){
                case "rac":
                    intA = stack.peek();
                    stack.pop();
                    c = Math.sqrt(intA);
                    stack.push(c);
                    break;
                case "inv":
                    intA = stack.peek();
                    stack.pop();
                    intA *= -1;
                    stack.push(intA);
                    break;
                case "+":
                    c = intA + intB;
                    stack.push(intA);
                    break;
                case "-":
                    c = intA - intB;
                    stack.push(intA);
                    break;
                case "*":
                    c = intA * intB;
                    stack.push(intA);
                    break;
                case "/":
                    c = intA / intB;
                    stack.push(intA);
                    break;
                default:
                    stack.push(Double.parseDouble(a));
                    break;
            }
        }
        resultat = stack.peek();
        return resultat;
    }
}
