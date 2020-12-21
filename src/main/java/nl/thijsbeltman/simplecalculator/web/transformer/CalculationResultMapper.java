package nl.thijsbeltman.simplecalculator.web.transformer;

import nl.thijsbeltman.simplecalculator.model.Calculation;
import nl.thijsbeltman.simplecalculator.model.CalculationResult;
import nl.thijsbeltman.simplecalculator.web.rest.dto.CalculationDto;
import nl.thijsbeltman.simplecalculator.web.rest.dto.CalculationResultDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CalculationResultMapper {

    CalculationResultMapper INSTANCE = Mappers.getMapper( CalculationResultMapper.class );

    CalculationResultDto toDto(CalculationResult calculationResult);
    CalculationResult toCalculationResult(CalculationResultDto dto);

}