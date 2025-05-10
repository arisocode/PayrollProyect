package co.edu.unbosque.db2.payroll_proyect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.db2.payroll_proyect.model.entity.Employee;

//Repositorio JPA
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByNit(String nit);
    void deleteByNit(String nit);
}
