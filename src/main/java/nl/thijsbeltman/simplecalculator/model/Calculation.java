package nl.thijsbeltman.simplecalculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Calculation extends AbstractEntity{

    private int firstNumber;
    private int secondNumber;
    private Operator operator;

}
