package co.edu.unbosque.db2.payroll_proyect.service.interfaces;

import co.edu.unbosque.db2.payroll_proyect.model.dto.EmployeeDTO;

//Interfaz del servicio de empleado
public interface IEmployeeService {

    EmployeeDTO save(EmployeeDTO dto);
    EmployeeDTO findByNit(String nit);
    void deleteByNiT(String nit);
}
