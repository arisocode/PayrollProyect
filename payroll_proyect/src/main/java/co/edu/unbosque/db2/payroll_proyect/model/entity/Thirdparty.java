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
@Table(name = "Thirdparty")
public class Thirdparty {

    @Id
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Name", length = 100)
    private String name;

    @Column(name = "Nit", length = 50)
    private String nit;

    @Column(name = "Status")
    private Boolean status;

    @Column(name = "Phone", length = 15)
    private String phone;

    @Column(name = "Email", length = 100)
    private String email;

    @Column(name = "Address", length = 100)
    private String address;

    @Column(name = "Typeid")
    private Integer typeId;
}
