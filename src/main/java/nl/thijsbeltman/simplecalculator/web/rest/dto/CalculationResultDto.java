package nl.thijsbeltman.simplecalculator.web.rest.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
public class CalculationResultDto extends CalculationDto{
    private double result;
}
