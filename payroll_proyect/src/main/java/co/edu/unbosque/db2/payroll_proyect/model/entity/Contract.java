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
@Table(name = "Contract")
public class Contract {

    @Id
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Code", length = 20)
    private String code;

    @Column(name = "Contracttypeid")
    private Integer contractTypeId;

    @Column(name = "Startdate")
    private LocalDate startDate;

    @Column(name = "Enddate")
    private LocalDate endDate;

    @Column(name = "Salary")
    private double salary;

    @Column(name = "Modificationdate")
    private LocalDate modificationDate;

    @Column(name = "Status")
    private Integer status;

    @Column(name = "Paymentperiod")
    private Integer paymentPeriod;

    @Column(name = "Paymenthour")
    private double paymentHour;

    @Column(name = "Employeeid")
    private Integer employeeId;

    @Column(name = "Scheduleid")
    private Integer scheduleId;

    @Column(name = "Jobpositionid")
    private Integer jobPositionId;

}
