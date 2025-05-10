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
public class EmployeeDTO {

    @JsonProperty("id")
    private int id;

    @JsonProperty("primerNombre")
    private String firstName;

    @JsonProperty("segundoNombre")
    private String secondName;

    @JsonProperty("primerApellido")
    private String fLastName;

    @JsonProperty("segundoApellido")
    private String sLastName;

    @JsonProperty("nombre")
    private String name;

    @JsonProperty("estado")
    private Boolean status;

    @JsonProperty("fechaNacimiento")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @JsonProperty("nit")
    private String nit;

    @JsonProperty("fechaInicio")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonProperty("tipoDocumento")
    private int documentType;

    @JsonProperty("telefono")
    private String phone;

    @JsonProperty("email")
    private String email;

    @JsonProperty("direccion")
    private String address;

    @JsonProperty("bancoId")
    private Integer bankId;

    @JsonProperty("numeroCuenta")
    private String accountNumber;

    @JsonProperty("tipoCuenta")
    private Boolean accountType;

}
