package nl.thijsbeltman.simplecalculator.web.transformer;

import nl.thijsbeltman.simplecalculator.model.Calculation;
import nl.thijsbeltman.simplecalculator.model.CalculationResult;
import nl.thijsbeltman.simplecalculator.model.Operator;
import nl.thijsbeltman.simplecalculator.web.rest.dto.CalculationResultDto;
import nl.thijsbeltman.simplecalculator.web.rest.dto.OperatorDto;
import org.junit.jupiter.api.Test;
import org.hamcrest.core.Is;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CalculationResultMapperTest {

    @Test
    public void mapperShouldTransformCalculationResultToDto(){
        //given
        CalculationResult result = new CalculationResult(3.0);
        result.setOperator(Operator.ADDITION);
        result.setFirstNumber(1);
        result.setSecondNumber(2);

        CalculationResultDto expectedDto = new CalculationResultDto();
        expectedDto.setResult(3.0);
        expectedDto.setOperator("ADDITION");
        expectedDto.setFirstNumber(1);
        expectedDto.setSecondNumber(2);

        //when
        CalculationResultDto actualDto = CalculationResultMapper.INSTANCE.toDto(result);

        //assert
        assertThat(actualDto, Is.is(expectedDto));
    }

    @Test
    public void mapperShouldTransformDtoToCalculationResult(){
        //given
        CalculationResultDto result = new CalculationResultDto();
        result.setResult(3.0);
        result.setOperator("ADDITION");
        result.setFirstNumber(1);
        result.setSecondNumber(2);

        CalculationResult expected = new CalculationResult(3.0);
        expected.setOperator(Operator.ADDITION);
        expected.setFirstNumber(1);
        expected.setSecondNumber(2);

        //when
        CalculationResult actualResult = CalculationResultMapper.INSTANCE.toCalculationResult(result);

        //assert
        assertThat(actualResult, Is.is(expected));
    }

}