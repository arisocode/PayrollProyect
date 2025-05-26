package co.edu.unbosque.db2.payroll_proyect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.unbosque.db2.payroll_proyect.model.entity.Contract;

public interface IContractRepository extends JpaRepository<Contract, Integer> {
    
    @Query("SELECT c FROM Contract c WHERE c.code = :code AND c.status = :status")
    Optional<Contract> findByCodeAndStatus(String code, Integer status);

    @Query("SELECT c FROM Contract c WHERE c.employeeId = :employeeId AND c.status = 1")
    Optional<Contract> findActiveContractByEmployeeId(int employeeId);

}
