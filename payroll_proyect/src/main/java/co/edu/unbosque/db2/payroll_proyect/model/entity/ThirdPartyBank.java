package co.edu.unbosque.db2.payroll_proyect.model.entity;

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
@Table(name = "v_thirdpartybank")
public class ThirdPartyBank {

    @Id
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Nit")
    private String nit;

    @Column(name = "Status")
    private Boolean status;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Email")
    private String email;

    @Column(name = "Address")
    private String address;

    @Column(name = "Typeid")
    private Integer typeId;

    @Column(name = "Bankid")
    private Integer bankId;

    @Column(name = "Accountnumber")
    private String accountNumber;

    @Column(name = "Accounttype")
    private Boolean accountType;
}
