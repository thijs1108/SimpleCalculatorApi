package nl.thijsbeltman.simplecalculator.web.rest.dto;

public enum OperatorDto {

    ADDITION("addition"), SUBSTRACTION("substraction"),
    MULTIPLICATION("substracition"), DIVISION("division");

    private String operator;

    OperatorDto(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return this.operator;
    }

}
