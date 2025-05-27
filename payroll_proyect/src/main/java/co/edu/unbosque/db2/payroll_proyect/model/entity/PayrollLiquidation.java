package co.edu.unbosque.db2.payroll_proyect.model.entity;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "liquidations")
public class PayrollLiquidation {

    @Id
    private String id;

    private Integer employeeId;
    private Integer contractId;
    private LocalDate liquidationDate;
    private Double basicSalary;
    private Double extraHoursValue;
    private Double legalDeductions;
    private Double totalValue;
    private Integer status;


}
