package nl.thijsbeltman.simplecalculator.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;

@RestController
@RequestMapping("/calculations")
@Slf4j
public class CalculationController {

    Collection<String> strings = Arrays.asList("abc", "def");

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<String>> getCalculations() {
        return ResponseEntity.ok(strings);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Collection<String>> addCalculation(@RequestBody Collection<String> stringsDto) {
        log.info("Saving {}", stringsDto);
        return ResponseEntity.status(201).body(stringsDto);
    }


}
