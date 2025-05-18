package co.edu.unbosque.db2.payroll_proyect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.db2.payroll_proyect.model.entity.ContractType;

public interface IContractTypeRepository extends JpaRepository<ContractType, Integer> {
    

}
