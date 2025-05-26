package co.edu.unbosque.db2.payroll_proyect.model.dto;

import java.math.BigDecimal;
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
public class FinalliquidationDto {

    @JsonProperty("Id")
    private Integer id;

    @JsonProperty("LiquidationDate")
    private LocalDate liquidationDate;

    @JsonProperty("TotalValue")
    private BigDecimal totalValue;

    @JsonProperty("Status")
    private Short status;

    @JsonProperty("WorkedDays")
    private BigDecimal workedDays;

    @JsonProperty("WorkedHours")
    private BigDecimal workedHours;

    @JsonProperty("VacationsPending")
    private BigDecimal vacationsPending;

    @JsonProperty("EmployeeId")
    private Integer employee;

    @JsonProperty("ContractId")
    private Integer contract;

    @JsonProperty("TerminationReasonId")
    private Integer terminationReason;

}