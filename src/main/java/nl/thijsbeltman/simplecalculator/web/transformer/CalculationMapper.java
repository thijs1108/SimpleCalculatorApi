package nl.thijsbeltman.simplecalculator.web.transformer;

import nl.thijsbeltman.simplecalculator.model.Calculation;
import nl.thijsbeltman.simplecalculator.web.rest.dto.CalculationDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CalculationMapper {

    CalculationMapper INSTANCE = Mappers.getMapper( CalculationMapper.class );

    CalculationDto toDto(Calculation calculation);
    Calculation toCalculation(CalculationDto dto);

}
