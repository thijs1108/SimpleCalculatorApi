package nl.thijsbeltman.simplecalculator.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.thijsbeltman.simplecalculator.web.rest.dto.CalculationDto;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CalculationControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CalculationController calculationController;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(calculationController).build();
    }

    @Test
    public void testGetCalculations() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                get("/calculations")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus(), CoreMatchers.is(200));
        assertThat(response.getContentAsString(), CoreMatchers.containsString("\"secondNumber\":2,"));
    }

    @Test
    public void testPostCalculations() throws Exception {
        //given
        CalculationDto dto = new CalculationDto();
        dto.setFirstNumber(1);
        dto.setSecondNumber(2);
        dto.setOperator("ADDITION");
        List<CalculationDto> dtos = Arrays.asList(dto);

        //when
        final ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder().build();
        MockHttpServletResponse response = mockMvc.perform(
                post("/calculations")
                        .content(objectMapper.writeValueAsString(dtos))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //asert
        assertThat(response.getStatus(), CoreMatchers.is(HttpStatus.CREATED.value()));
        assertThat(response.getContentAsString(), CoreMatchers.containsString("\"secondNumber\":2"));

    }


}