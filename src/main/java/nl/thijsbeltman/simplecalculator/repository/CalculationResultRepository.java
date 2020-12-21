package nl.thijsbeltman.simplecalculator.repository;

import nl.thijsbeltman.simplecalculator.model.CalculationResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CalculationResultRepository extends CrudRepository<CalculationResult, Integer> {

}
