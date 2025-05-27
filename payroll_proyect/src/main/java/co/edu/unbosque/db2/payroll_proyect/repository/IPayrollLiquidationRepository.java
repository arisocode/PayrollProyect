package co.edu.unbosque.db2.payroll_proyect.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.unbosque.db2.payroll_proyect.model.entity.PayrollLiquidation;

public interface IPayrollLiquidationRepository extends MongoRepository<PayrollLiquidation, String>{
    
    List<PayrollLiquidation> findByEmployeeId(Integer employeeId);
    
}
