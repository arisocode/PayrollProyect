package co.edu.unbosque.db2.payroll_proyect.model.dto;

import java.time.LocalDate;

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
public class NoveltyDTO {

    @JsonProperty("id")
    private int id;

    @JsonProperty("date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonProperty("hours")
    private double hours;

    @JsonProperty("days")
    private double days;

    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("description")
    private String description;

    @JsonProperty("noveltyTypeId")
    private int noveltyTypeId;

    @JsonProperty("employeeId")
    private int employeeId;

    @JsonProperty("contractId")
    private int contractId;
}
