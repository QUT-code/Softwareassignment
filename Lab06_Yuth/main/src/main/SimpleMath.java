package main;

public class SimpleMath {
    public static int addition(int a, int b){
        return a + b;
    }

    public static int subtraction(int a, int b){
        return a - b;
    }

    public static int multiply(int a, int b){
        return a * b;
    }

    public static int divide(int a, int b){
        if (a == 0 && b == 0){
            throw new IllegalArgumentException("infinite");
        }
        else if (b == 0){
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        else{
            return a / b;
        }
    }
}
