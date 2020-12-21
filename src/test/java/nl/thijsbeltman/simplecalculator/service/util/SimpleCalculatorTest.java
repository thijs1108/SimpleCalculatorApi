package nl.thijsbeltman.simplecalculator.service.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SimpleCalculatorTest {

    private SimpleCalculator simpleCalculator;

    @BeforeEach
    public void setUp() {
        simpleCalculator = new SimpleCalculator();
    }

    @Test
    public void addShouldAddNumbers() {
        assertThat(simpleCalculator.add(1,2), is(3.0));
    }

    @Test
    public void substractShouldSubstractNumbers() {
        assertThat(simpleCalculator.subtract(1,2), is(-1.0));
    }

    @Test
    public void multiplyShouldMultiplyNumbers() {
        assertThat(simpleCalculator.multiply(2,4), is(8.0));
    }

    @Test
    public void devideShouldDevideNumbers() {
        assertThat(simpleCalculator.divide(10,3), is(10/3.0));
    }

    @Test
    public void devideByZeroShouldThrowError() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            simpleCalculator.divide(10,0);
        });
        assertThat(exception.getMessage(), is(simpleCalculator.DIVIDE_BY_ZERO_ERROR));
    }
}
