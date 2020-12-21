package nl.thijsbeltman.simplecalculator.web.rest.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
public class CalculationDto {

    private int firstNumber;
    private int secondNumber;
    private String operator;

}
