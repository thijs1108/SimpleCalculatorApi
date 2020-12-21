package nl.thijsbeltman.simplecalculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calculation {

    private int firstNumber;
    private int secondNumber;
    private Operator operator;

}
