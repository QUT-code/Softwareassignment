package running;

import org.junit.Test;
import static org.junit.Assert.*;

public class SimpleMathTest {
    private SimpleMath math = new SimpleMath();

    @Test
    public void testAddPositiveNumbers() {
        assertEquals(5, math.add(2, 3));
    }

    @Test
    public void testAddNegativeNumbers() {
        assertEquals(-1, math.add(-2, 1));
    }

    @Test
    public void testSubtractPositiveNumbers() {
        assertEquals(1, math.subtract(3, 2));
    }

    @Test
    public void testSubtractNegativeNumbers() {
        assertEquals(-5, math.subtract(-2, 3));
    }

    @Test
    public void testMultiplyPositiveNumbers() {
        assertEquals(6, math.multiply(2, 3));
    }

    @Test
    public void testMultiplyNegativeNumbers() {
        assertEquals(6, math.multiply(-2, -3));
    }

    @Test
    public void testDividePositiveNumbers() {
        assertEquals(2.0, math.divide(6, 3), 0.001);
    }

    @Test
    public void testDivideNegativeNumbers() {
        assertEquals(2.0, math.divide(-6, -3), 0.001);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() {
        math.divide(1, 0);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideZeroByZero() {
        math.divide(0, 0);
    }
}