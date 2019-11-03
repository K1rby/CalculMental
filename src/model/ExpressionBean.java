package model;

import bo.Expression;
import bo.Operateur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExpressionBean implements Serializable {

    private List<Expression> expressions = new ArrayList<>();
    private List<Double> reponses = new ArrayList<>();
    private List<Double> resultats = new ArrayList<>();
    private String currentExpression;
    private Operateur operateur = new Operateur();

    public ExpressionBean() {}

    public Operateur getOperateur() {
        return operateur;
    }

    public void setOperateur(Operateur operateur) {
        this.operateur = operateur;
    }

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

    public void clearResultats(){
        resultats.clear();
    }

    public void clearReponses(){
        reponses.clear();
    }

    public void clearExpressions(){
        expressions.clear();
    }

    public String generationExpression(){
        String expression = "";
        String[] op1arg = {"rac", "inv"};
        String[] op2arg = {"+", "-", "*", "/"};
        int count = 0;
        int i;
        int choice;
        int flag;
        Double add;
        for (i = 0; i < 5; i++) {
            flag = (int)(Math.random() * 2);
            if (count == 0) {
                add = (double) Math.round((Math.random() * 100) * 100d) / 100d;
                count++;
                expression = expression + add.toString() + " ";
            } else if (count == 1) {
                if (flag == 0) {
                    choice = (int)(Math.random() * 2);
                    expression = expression + op1arg[choice] + " ";
                    if (i < 4) {
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
        if (count == 2) {

        }
        expression = expression.substring(0, expression.length() -1);
        setCurrentExpression(expression);
        return expression;
    }

    public Double calculExpression() {
        String expression = getCurrentExpression();
        String []array = expression.split(" ", 6);
        Double resultat;

        for (String a : array) {
            operateur.TestOperateur(a);
        }
        resultat = operateur.getStack().peek();
        return resultat;
    }
}
