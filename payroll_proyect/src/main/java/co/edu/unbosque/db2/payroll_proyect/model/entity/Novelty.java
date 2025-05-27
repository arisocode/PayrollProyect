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
@Table(name = "Novelty")
public class Novelty {

    @Id
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Date")
    private LocalDate date;

    @Column(name = "Hours")
    private double hours;

    @Column(name = "Days")
    private double days;

    @Column(name = "Status")
    private Boolean status;

    @Column(name = "Description", length = 100)
    private String description;

    @Column(name = "Noveltytypeid")
    private Integer noveltyTypeId;

    @Column(name = "Employeeid")
    private Integer employeeId;

    @Column(name = "Contractid")
    private Integer contractId;

}
