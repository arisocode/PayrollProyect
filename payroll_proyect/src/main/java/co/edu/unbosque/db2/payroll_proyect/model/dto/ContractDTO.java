package co.edu.unbosque.db2.payroll_proyect.model.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractDTO {

    @JsonProperty("id")
    private int id;

    @JsonProperty("code")
    private String code;

    @JsonProperty("contractTypeId")
    private int contractTypeId;

    @JsonProperty("startDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonProperty("endDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @JsonProperty("salary")
    private String salary;

    @JsonProperty("modificationDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate modificationDate;

    @JsonProperty("status")
    private int status;

    @JsonProperty("paymentPeriod")
    private int paymentPeriod;

    @JsonProperty("paymentHour")
    private double paymentHour;

    @JsonProperty("employeeId")
    private int employeeId;

    @JsonProperty("scheduleId")
    private int scheduleId;

    @JsonProperty("jobPositionId")
    private int jobPositionId;

    @JsonProperty("thirdParties")
    private List<ContractThirdPartyDTO> thirdParties;
}
