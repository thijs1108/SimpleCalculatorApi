package nl.thijsbeltman.simplecalculator.web.rest;

import lombok.extern.slf4j.Slf4j;
import nl.thijsbeltman.simplecalculator.model.CalculationResult;
import nl.thijsbeltman.simplecalculator.model.Operator;
import nl.thijsbeltman.simplecalculator.web.rest.dto.CalculationDto;
import nl.thijsbeltman.simplecalculator.web.rest.dto.CalculationResultDto;
import nl.thijsbeltman.simplecalculator.web.transformer.CalculationResultMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/calculations")
@Slf4j
public class CalculationController {


    /**
     * Retrieves history of all previous calulations and their results
     * @return List of CalculationsResults
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CalculationResultDto>> getCalculations() {
        CalculationResult result1 = new CalculationResult(3.0);
        result1.setFirstNumber(1);
        result1.setSecondNumber(2);
        result1.setOperator(Operator.ADDITION);
        CalculationResult result2 = new CalculationResult(8.0);
        result2.setFirstNumber(2);
        result2.setSecondNumber(4);
        result2.setOperator(Operator.MULTIPLICATION);
        List<CalculationResultDto> dtos = Arrays.asList(result1, result2)
                .stream()
                .map(CalculationResultMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Calculates the calculations given as parameter. Returns the results of given calculations
     * @param calculationDtos List of Calculations
     * @return List of CalculationsResults
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Collection<CalculationResultDto>> addCalculation(
            @RequestBody Collection<CalculationDto> calculationDtos) {
        log.info("Saving {}", calculationDtos);
        CalculationResultDto resultDto = new CalculationResultDto();
        resultDto.setFirstNumber(1);
        resultDto.setSecondNumber(2);
        resultDto.setOperator("ADDITION");
        resultDto.setResult(3.0);
        return ResponseEntity.status(201).body(Arrays.asList(resultDto));
    }


}
