package model;

import bo.Expression;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ExpressionBean implements Serializable {

    private List<Expression> expressions = new ArrayList<>();
    private List<Double> reponses = new ArrayList<>();
    private List<Double> resultats = new ArrayList<>();
    private String currentExpression;

    public ExpressionBean() {}

    public String getCurrentExpression() {
        return currentExpression;
    }

    public void setCurrentExpression(String currentExpression) {
        this.currentExpression = currentExpression;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<Expression> expressions) {
        this.expressions = expressions;
    }

    public List<Double> getReponses() {
        return reponses;
    }

    public void setReponses(List<Double> reponses) {
        this.reponses = reponses;
    }

    public List<Double> getResultats() {
        return resultats;
    }

    public void setResultats(List<Double> resultats) {
        this.resultats = resultats;
    }

    public void addReponse(Double reponse){
        reponses.add(reponse);
    }

    public void addExpression(Expression expression){
        expressions.add(expression);
    }

    public void addResultats(Double resultat){
        resultats.add(resultat);
    }

    public String generationExpression(){
        String expression = "";
        String[] op1arg = {"rac", "inv"};
        String[] op2arg = {"+", "-", "*", "/"};
        int count = 0;
        int i;
        int choice;
        int flag = (int)(Math.random() * 2);
        Double add;
        for (i = 0; i < 5; i++) {
            if (count == 0) {
                add = (double) Math.round((Math.random() * 100) * 100d) / 100d;
                count++;
                expression = expression + add.toString() + " ";
            } else if (count == 1) {
                if (flag == 0) {
                    choice = (int)(Math.random() * 2);
                    expression = expression + op1arg[choice] + " ";
                    if (i < 3) {
                        flag = 1;
                    }
                } else {
                    add = (double) Math.round((Math.random() * 100) * 100d) / 100d;
                    count++;
                    expression = expression + add.toString() + " ";
                    flag = 0;
                }
            } else {
                choice = (int)(Math.random() * 4);
                expression = expression + op2arg[choice] + " ";
                count--;
            }
        }
        expression = expression.substring(0, expression.length() -1);
        setCurrentExpression(expression);
        return expression;
    }

    public Double calculExpression() {
        String expression = getCurrentExpression();
        String []array = expression.split(" ", 6);
        Stack<Double> stack = new Stack<Double>();
        Double resultat;

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
                    intA = stack.peek();
                    stack.pop();
                    intB = stack.peek();
                    stack.pop();
                    c = intA + intB;
                    stack.push(c);
                    break;
                case "-":
                    intA = stack.peek();
                    stack.pop();
                    intB = stack.peek();
                    stack.pop();
                    c = intA - intB;
                    stack.push(c);
                    break;
                case "*":
                    intA = stack.peek();
                    stack.pop();
                    intB = stack.peek();
                    stack.pop();
                    c = intA * intB;
                    stack.push(c);
                    break;
                case "/":
                    intA = stack.peek();
                    stack.pop();
                    intB = stack.peek();
                    stack.pop();
                    c = intA / intB;
                    stack.push(c);
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
