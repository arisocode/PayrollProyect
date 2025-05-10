package co.edu.unbosque.db2.payroll_proyect.exception;

import co.edu.unbosque.db2.payroll_proyect.model.dto.EmployeeDTO;

public class SuccessResponse {
    private String message;
    private EmployeeDTO employee;

    public SuccessResponse(String message, EmployeeDTO employee) {
        this.message = message;
        this.employee = employee;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    
}
