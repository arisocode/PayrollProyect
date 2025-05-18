package co.edu.unbosque.db2.payroll_proyect.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("startHour")
    private double startHour;

    @JsonProperty("endHour")
    private double endHour;

    @JsonProperty("description")
    private String description;
    
}
