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
@Table(name = "Contractthirdparty")
public class ContractThirdParty {

    @Id
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Thirdpartyid")
    private Integer thirdPartyId;

    @Column(name = "Contractid")
    private Integer contractId;

    @Column(name = "Paymentpercentage")
    private double percentage;
}
