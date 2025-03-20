package CartApp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {


    @Test
    void calculateTotal() {
        Calculator calc = new Calculator(10, 2);
        assertEquals(20, calc.calculateTotal());
    }

    @Test
    void calculateTotal2() {
        Calculator calc = new Calculator(10, 3);
        assertEquals(30, calc.calculateTotal());
        Calculator calc2 = new Calculator(10.5, 4);
        assertEquals(42, calc2.calculateTotal());
        double total = calc.calculateTotal() + calc2.calculateTotal();
        assertEquals(72, total);
    }
}