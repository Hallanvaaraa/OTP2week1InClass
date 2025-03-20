package CartApp;

public class Calculator {

    private double cost;
    private int quantity;

    public Calculator(double cost, int quantity) {
        this.cost = cost;
        this.quantity = quantity;
    }

    public double calculateTotal() {
        return cost * quantity;
    }
}

