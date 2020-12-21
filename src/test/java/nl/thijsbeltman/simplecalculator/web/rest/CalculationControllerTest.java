package nl.thijsbeltman.simplecalculator.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.thijsbeltman.simplecalculator.model.CalculationResult;
import nl.thijsbeltman.simplecalculator.model.Operator;
import nl.thijsbeltman.simplecalculator.service.CalculationService;
import nl.thijsbeltman.simplecalculator.web.rest.dto.CalculationDto;
import nl.thijsbeltman.simplecalculator.web.rest.dto.CalculationResultDto;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CalculationControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CalculationController calculationController;

    @Mock
    private CalculationService calculationService;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(calculationController).build();
    }

    @Test
    public void testGetCalculations() throws Exception {
        //given
        CalculationResult result1 = new CalculationResult(3.0);
        result1.setFirstNumber(1);
        result1.setSecondNumber(2);
        result1.setOperator(Operator.ADDITION);
        CalculationResult result2 = new CalculationResult(8.0);
        result2.setFirstNumber(2);
        result2.setSecondNumber(4);
        result2.setOperator(Operator.MULTIPLICATION);
        given(calculationService.getCalculations()).willReturn(Arrays.asList(result1, result2));

        //when
        MockHttpServletResponse response = mockMvc.perform(
                get("/calculations")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //assert
        assertThat(response.getStatus(), CoreMatchers.is(200));
        assertThat(response.getContentAsString(), CoreMatchers.containsString("\"secondNumber\":2,"));
        assertThat(response.getContentAsString(), CoreMatchers.containsString("\"operator\":\"ADDITION\","));
        assertThat(response.getContentAsString(), CoreMatchers.containsString("\"operator\":\"MULTIPLICATION\","));
    }

    @Test
    public void testPostCalculations() throws Exception {
        //given
        CalculationDto dto = new CalculationDto();
        dto.setFirstNumber(1);
        dto.setSecondNumber(2);
        dto.setOperator("ADDITION");
        List<CalculationDto> dtos = Arrays.asList(dto);

        CalculationResult mockResult = new CalculationResult();
        mockResult.setResult(3.0);
        mockResult.setOperator(Operator.ADDITION);
        mockResult.setFirstNumber(1);
        mockResult.setSecondNumber(2);
        List<CalculationResult> mockResults = Arrays.asList(mockResult);
        given(calculationService.runCalculations(ArgumentMatchers.anyCollection())).willReturn(mockResults);

        //when
        final ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder().build();
        MockHttpServletResponse response = mockMvc.perform(
                post("/calculations")
                        .content(objectMapper.writeValueAsString(dtos))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //assert
        assertThat(response.getStatus(), CoreMatchers.is(HttpStatus.CREATED.value()));
        assertThat(response.getContentAsString(), CoreMatchers.containsString("\"result\":3"));
    }


}