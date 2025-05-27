package co.edu.unbosque.db2.payroll_proyect.model.interfaces;

import java.time.LocalDate;

public interface IEmployeeProjection {
    Long getEmployeeId();
    String getNit();
    String getName();
    Long getContractId();
    String getContractCode();
    Double getSalary();
    Integer getPaymentPeriod();
    LocalDate getContractStartDate();
    LocalDate getContractEndDate();
    LocalDate getLastLiquidationDate();
    Integer getNeedsLiquidation();
}
