package co.edu.unbosque.db2.payroll_proyect.service.interfaces;

import java.util.List;

import co.edu.unbosque.db2.payroll_proyect.model.entity.PayrollLiquidation;

public interface IPayrollLiquidationService {

    PayrollLiquidation liquidarEmpleado(Integer employeeId);
    List<PayrollLiquidation> getByEmployeeId(Integer employeeId);
}
