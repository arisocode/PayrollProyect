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
public class ThirdPartyDTO {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("nit")
    private String nit;

    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("email")
    private String email;

    @JsonProperty("address")
    private String address;

    @JsonProperty("typeId")
    private Integer typeId;

    @JsonProperty("bankId")
    private Integer bankId;

    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("accountType")
    private Boolean accountType;

}
