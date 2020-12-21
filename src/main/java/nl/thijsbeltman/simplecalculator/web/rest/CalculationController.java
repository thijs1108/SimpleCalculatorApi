package nl.thijsbeltman.simplecalculator.web.rest;

import lombok.extern.slf4j.Slf4j;
import nl.thijsbeltman.simplecalculator.model.Calculation;
import nl.thijsbeltman.simplecalculator.model.CalculationResult;
import nl.thijsbeltman.simplecalculator.model.Operator;
import nl.thijsbeltman.simplecalculator.service.CalculationService;
import nl.thijsbeltman.simplecalculator.web.rest.dto.CalculationDto;
import nl.thijsbeltman.simplecalculator.web.rest.dto.CalculationResultDto;
import nl.thijsbeltman.simplecalculator.web.transformer.CalculationMapper;
import nl.thijsbeltman.simplecalculator.web.transformer.CalculationResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CalculationService calculationService;

    /**
     * Retrieves history of all previous calulations and their results
     * @return List of CalculationsResults
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CalculationResultDto>> getCalculations() {
        List<CalculationResultDto> dtos = calculationService.getCalculations()
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
        List<Calculation> calculations = calculationDtos
                .stream()
                .map(CalculationMapper.INSTANCE::toCalculation)
                .collect(Collectors.toList());
        Collection<CalculationResultDto> resultDtos = calculationService.runCalculations(calculations)
                .stream()
                .map(CalculationResultMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.status(201).body(resultDtos);
    }


}
