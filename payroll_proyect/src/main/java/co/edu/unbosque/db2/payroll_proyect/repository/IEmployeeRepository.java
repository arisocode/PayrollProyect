package co.edu.unbosque.db2.payroll_proyect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.unbosque.db2.payroll_proyect.model.entity.Employee;
import co.edu.unbosque.db2.payroll_proyect.model.interfaces.IEmployeeProjection;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "SELECT * FROM V_EmployeesToLiquidate", nativeQuery = true)
    List<IEmployeeProjection> findEmployeesToLiquidate();
}
