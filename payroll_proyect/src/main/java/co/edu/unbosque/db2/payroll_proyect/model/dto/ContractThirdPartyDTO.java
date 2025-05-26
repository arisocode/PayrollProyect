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
public class ContractThirdPartyDTO {

    @JsonProperty("nit")
    private String nit;

    @JsonProperty("percentage")
    private double percentage;
}
