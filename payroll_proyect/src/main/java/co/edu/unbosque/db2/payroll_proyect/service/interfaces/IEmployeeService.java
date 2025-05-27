package co.edu.unbosque.db2.payroll_proyect.service.interfaces;

import java.util.List;

import co.edu.unbosque.db2.payroll_proyect.model.dto.EmployeeDTO;
import co.edu.unbosque.db2.payroll_proyect.model.dto.EmployeeToLiquidateDTO;

//Interfaz del servicio de empleado
public interface IEmployeeService {

    EmployeeDTO save(EmployeeDTO dto);
    EmployeeDTO findByNit(String nit);
    void deleteByNiT(String nit);
    List<EmployeeDTO> findAll();
    List<EmployeeToLiquidateDTO> getEmployeesToLiquidate();
}
