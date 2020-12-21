package nl.thijsbeltman.simplecalculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CalculationResult extends Calculation{

    private double result;

}
