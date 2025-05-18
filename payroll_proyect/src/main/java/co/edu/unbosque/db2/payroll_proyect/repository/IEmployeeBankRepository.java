package co.edu.unbosque.db2.payroll_proyect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.unbosque.db2.payroll_proyect.model.entity.EmployeeWithBank;

//Repositorio JPA
public interface IEmployeeBankRepository extends JpaRepository<EmployeeWithBank, Integer> {

    @Query("SELECT e FROM EmployeeWithBank e WHERE e.nit = :nit")
    Optional<EmployeeWithBank> findByNit(@Param("nit") String nit);
    void deleteByNit(String nit);
}
