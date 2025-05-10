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
@Table(name = "Bank")
public class Bank {

    @Id
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Code", nullable = false, length = 30)
    private String code;

    @Column(name = "Name", nullable = false, length = 100)
    private String name;

    @Column(name = "BankType")
    private Integer bankType;

    @Column(name = "Country", length = 30)
    private String country;

    @Column(name = "Address", length = 100)
    private String address;

    @Column(name = "Status")
    private Boolean status;

}
