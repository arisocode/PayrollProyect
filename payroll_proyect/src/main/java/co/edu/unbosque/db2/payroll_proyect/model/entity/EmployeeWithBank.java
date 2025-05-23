package co.edu.unbosque.db2.payroll_proyect.model.entity;

import java.time.LocalDate;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name = "v_employeeWithBank")
public class EmployeeWithBank {

    @Id
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Status")
    private Boolean status;

    @Column(name = "BirthDate")
    private LocalDate birthDate;

    @Column(name = "Nit")
    private String nit;

    @Column(name = "StartDate")
    private LocalDate startDate;

    @Column(name = "DocumentType")
    private Integer documentType;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Email")
    private String email;

    @Column(name = "Address")
    private String address;

    @Column(name = "BankId")
    private Integer bankId;

    @Column(name = "AccountNumber")
    private String accountNumber;

    @Column(name = "AccountType")
    private Boolean accountType;

}
