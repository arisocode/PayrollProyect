package co.edu.unbosque.db2.payroll_proyect.model.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeToLiquidateDTO {

    @JsonProperty("employeeId")
    private Long employeeId;

    @JsonProperty("nit")
    private String nit;

    @JsonProperty("name")
    private String name;

    @JsonProperty("contractId")
    private Long contractId;

    @JsonProperty("contractCode")
    private String contractCode;

    @JsonProperty("salary")
    private Double salary;

    @JsonProperty("paymentPeriod")
    private Integer paymentPeriod;

    @JsonProperty("contractStartDate")
    private LocalDate contractStartDate;

    @JsonProperty("contractEndDate")
    private LocalDate contractEndDate;

    @JsonProperty("lastLiquidationDate")
    private LocalDate lastLiquidationDate;

    @JsonProperty("needsLiquidation")
    private Integer needsLiquidation;
}
