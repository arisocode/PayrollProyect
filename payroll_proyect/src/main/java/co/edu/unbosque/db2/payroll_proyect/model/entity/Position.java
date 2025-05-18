package co.edu.unbosque.db2.payroll_proyect.model.entity;

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
@Table(name = "Jobposition")
public class Position {

    @Id
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Code", length = 20)
    private String code;

    @Column(name = "Name", length = 100)
    private String name;

    @Column(name = "Basesalary")
    private double baseSalary;

    @Column(name = "Description", length = 255)
    private String description;

}
