package nl.thijsbeltman.simplecalculator.service;

import jdk.jshell.spi.ExecutionControl;
import lombok.extern.slf4j.Slf4j;
import nl.thijsbeltman.simplecalculator.model.Calculation;
import nl.thijsbeltman.simplecalculator.model.CalculationResult;
import nl.thijsbeltman.simplecalculator.repository.CalculationResultRepository;
import nl.thijsbeltman.simplecalculator.service.util.SimpleCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class CalculationService {

    @Autowired
    private SimpleCalculator simpleCalculator;

    @Autowired
    private CalculationResultRepository resultRepository;

    @Transactional
    public Collection<CalculationResult> runCalculations(Collection<Calculation> calculations){
        Collection<CalculationResult> results = calculations
                .stream()
                .map(this::runCalculation)
                .collect(Collectors.toList());
        results.forEach(resultRepository::save);
        return results;
    }

    public Collection<CalculationResult> getCalculations(){
        Iterable<CalculationResult> calculationIterable = resultRepository.findAll();
        List<CalculationResult> calculationList = StreamSupport
                .stream(calculationIterable.spliterator(), false)
                .collect(Collectors.toList());
        return calculationList;
    }

    private CalculationResult runCalculation(Calculation calculation){
        CalculationResult result = new CalculationResult();
        result.setFirstNumber(calculation.getFirstNumber());
        result.setSecondNumber(calculation.getSecondNumber());
        result.setOperator(calculation.getOperator());
        switch (result.getOperator()){
            case ADDITION:
                result.setResult(simpleCalculator.add(result.getFirstNumber(),result.getSecondNumber()));
                break;
            case SUBSTRACTION:
                result.setResult(simpleCalculator.subtract(result.getFirstNumber(), result.getSecondNumber()));
                break;
            case MULTIPLICATION:
                result.setResult(simpleCalculator.multiply(result.getFirstNumber(), result.getSecondNumber()));
                break;
            case DIVISION:
                result.setResult(simpleCalculator.divide(result.getFirstNumber(), result.getSecondNumber()));
                break;
            default:
                log.error("Cant calculate {} for calculation {}", result.getOperator(), calculation);
                throw new IllegalArgumentException("Incorrect operator");
        }
        return result;
    }

}
