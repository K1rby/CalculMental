package bo;

import java.io.Serializable;
import java.util.Stack;

public class Operateur implements Serializable {

    private Stack<Double> stack = new Stack<Double>();
    private Double intA = 0.0;
    private Double intB = 0.0;
    private Double stock;

    public Stack<Double> getStack() {
        return stack;
    }

    public void setStack(Stack<Double> stack) {
        this.stack = stack;
    }

    public Double getIntA() {
        return intA;
    }

    public void setIntA(Double intA) {
        this.intA = intA;
    }

    public Double getIntB() {
        return intB;
    }

    public void setIntB(Double intB) {
        this.intB = intB;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public void TestOperateur(String operateur) {
        switch (operateur) {
            case "rac":
                operateurRacine();
                break;
            case "inv":
               operateurInverse();
                break;
            case "+":
                operateurPlus();
                break;
            case "-":
                operateurMoins();
                break;
            case "*":
               operateurMulti();
                break;
            case "/":
               operateurDiv();
                break;
            default:
                stack.push(Double.parseDouble(operateur));
                break;
        }
    }
    private void operateurPlus() {

        intA = stack.peek();
        stack.pop();
        intB = stack.peek();
        stack.pop();
        stock = intA + intB;
        stack.push(stock);
    }
    private void operateurMoins() {
        intA = stack.peek();
        stack.pop();
        intB = stack.peek();
        stack.pop();
        stock = intA - intB;
        stack.push(stock);
    }
    private void operateurMulti() {
        intA = stack.peek();
        stack.pop();
        intB = stack.peek();
        stack.pop();
        stock = intA * intB;
        stack.push(stock);
    }
    private void operateurDiv() {
        intA = stack.peek();
        stack.pop();
        intB = stack.peek();
        stack.pop();
        stock = intA / intB;
        stack.push(stock);
    }
    private void operateurInverse() {
        intA = stack.peek();
        stack.pop();
        intA *= -1;
        stack.push(intA);
    }
    private void operateurRacine() {
        intA = stack.peek();
        stack.pop();
        stock = Math.sqrt(intA);
        stack.push(stock);
    }


}
