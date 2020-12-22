package nl.thijsbeltman.simplecalculator.service;

import nl.thijsbeltman.simplecalculator.model.Calculation;
import nl.thijsbeltman.simplecalculator.model.CalculationResult;
import nl.thijsbeltman.simplecalculator.model.Operator;
import nl.thijsbeltman.simplecalculator.repository.CalculationResultRepository;
import nl.thijsbeltman.simplecalculator.service.util.SimpleCalculator;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CalculationServiceTest {

    @InjectMocks
    private CalculationService calculationService;

    @Mock
    private SimpleCalculator simpleCalculator;

    @Mock
    private CalculationResultRepository repository;

    @Test
    public void runCalculationsShouldReturnResults(){
        //given
        Calculation calculation1 = new Calculation(1,2, Operator.ADDITION);
        Calculation calculation2 = new Calculation(4,2, Operator.SUBSTRACTION);
        Calculation calculation3 = new Calculation(4,2, Operator.DIVISION);
        Calculation calculation4 = new Calculation(4,2, Operator.MULTIPLICATION);
        given(simpleCalculator.add(1, 2)).willReturn(4.0); //intentionally wrong to ensure mock usage
        given(simpleCalculator.subtract(4,2)).willReturn(8.0);
        given(simpleCalculator.divide(4,2)).willReturn(8.0);
        given(simpleCalculator.multiply(4,2)).willReturn(3.0);

        //when
        List<CalculationResult> actual =
                calculationService.runCalculations(Arrays.asList(
                        calculation1, calculation2, calculation3, calculation4)
                ).stream().collect(Collectors.toList());

        //assert
        assertThat(actual.size(), Is.is(4));
        assertThat(actual.get(0).getResult(), Is.is(4.0));
        assertThat(actual.get(1).getResult(), Is.is(8.0));
        assertThat(actual.get(2).getResult(), Is.is(8.0));
        assertThat(actual.get(3).getResult(), Is.is(3.0));
        verify(repository, times(4)).save(any(CalculationResult.class));
    }

    @Test
    public void getCalculationsShouldReturnCalculationHistory(){
        //given
        CalculationResult result1 = new CalculationResult(1.0);
        result1.setFirstNumber(2);
        result1.setSecondNumber(4);
        result1.setOperator(Operator.MULTIPLICATION);
        CalculationResult result2 = new CalculationResult(5.0);
        result2.setFirstNumber(6);
        result2.setSecondNumber(8);
        result2.setOperator(Operator.DIVISION);
        List<CalculationResult> results = Arrays.asList(result1, result2);
        given(repository.findAll()).willReturn(results);

        //when
        Collection<CalculationResult> actualCalculations = calculationService.getCalculations();

        //assert
        assertThat(actualCalculations, Is.is(results));
    }

}