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
@Table(name = "Thirdpartytype")
public class PartyType {

    @Id
    @Column(name = "Id", nullable = false)
    private int id;

    @Column(name = "Name", nullable = false, length = 100)
    private String name;

    @Column(name = "Description", nullable = false, length = 500)
    private String description;

}
