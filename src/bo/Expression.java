package bo;

import java.io.Serializable;

public class Expression implements Serializable {
    private int id;
    private String expression;
    private int id_partie;

    public Expression() {}

    public Expression(int id, String expression, int id_partie) {
        this.id = id;
        this.expression = expression;
        this.id_partie = id_partie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public int getId_partie() {
        return id_partie;
    }

    public void setId_partie(int id_partie) {
        this.id_partie = id_partie;
    }
}
