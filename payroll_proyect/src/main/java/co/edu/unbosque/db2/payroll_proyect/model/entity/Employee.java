package co.edu.unbosque.db2.payroll_proyect.model.entity;

import java.time.LocalDate;

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
@Table(name = "Employee")
public class Employee {

    @Id
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Name", length = 100)
    private String name;

    @Column(name = "Status")
    private Boolean status;

    @Column(name = "BirthDate")
    private LocalDate birthDate;

    @Column(name = "Nit", length = 20)
    private String nit;

    @Column(name = "StartDate")
    private LocalDate startDate;

    @Column(name = "DocumentType")
    private Integer documentType;

    @Column(name = "Phone", length = 15)
    private String phone;

    @Column(name = "Email", length = 100)
    private String email;

    @Column(name = "Address", length = 100)
    private String address;

}
