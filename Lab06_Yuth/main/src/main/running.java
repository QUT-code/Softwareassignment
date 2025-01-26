package main;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class running {
    @Test @DisplayName("Test add between two positive numbers")

    //Add
    public void testAddPositive(){
        assertEquals(25, SimpleMath.addition(10, 15));
    }

    @Test
    public void testAddNegative(){
        assertEquals(-5, SimpleMath.addition(10, -15));
    }

    //Substract
    @Test
    public void TestsubtractPositive(){
        assertEquals(5, SimpleMath.subtraction(10, 5));
    }

    @Test
    public void TestsubtractNegative(){
        assertEquals(-1, SimpleMath.subtraction(9, 10));
    }

    //Multiply
    @Test
    public void testmultiplyPositive(){
        assertEquals(6, SimpleMath.multiply(2, 3));
    }

    @Test void testmultiplyNegative(){
        assertEquals(-10, SimpleMath.multiply(5, -2));
    }

    //Divide
    @Test
    public void testDivideNonzeroByZero(){
        Exception exc = assertThrows(IllegalArgumentException.class, ()->SimpleMath.divide(2,0));
        assertEquals("Cannot divide by zero", exc.getMessage());
    }

    @Test 
    public void testDivideZeroByZero(){
        Exception exc = assertThrows(IllegalArgumentException.class, ()->SimpleMath.divide(0, 0));
        assertEquals("infinite", exc.getMessage());
    }

    @Test
    public void testDivideNonzeroByNonzeroButPositive(){
        assertEquals(5, SimpleMath.divide(25,5));
    }

    @Test
    public void testDivideNonzeroByNonzeroButNegative(){
        assertEquals(-2, SimpleMath.divide(10, -5));
    }
}

