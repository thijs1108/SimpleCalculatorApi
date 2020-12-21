package nl.thijsbeltman.simplecalculator.service.util;

import org.springframework.stereotype.Component;

@Component
public class SimpleCalculator {

    public final String DIVIDE_BY_ZERO_ERROR = "Cannot divide by zero";

    public double add(int firstNumber, int secondNumber){
        return firstNumber + secondNumber;
    }

    public double subtract(int firstNumber, int secondNumber){
        return firstNumber - secondNumber;
    }

    public double multiply(int firstNumber, int secondNumber){
        return firstNumber * secondNumber;
    }

    public double divide(int firstNumber, int secondNumber){
        if(secondNumber==0) throw new IllegalArgumentException(DIVIDE_BY_ZERO_ERROR);
        return firstNumber / (double) secondNumber;
    }

}
