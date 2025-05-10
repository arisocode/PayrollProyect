package co.edu.unbosque.db2.payroll_proyect.mapper;

import org.springframework.stereotype.Component;

import co.edu.unbosque.db2.payroll_proyect.mapper.interfaces.DataMapper;
import co.edu.unbosque.db2.payroll_proyect.model.dto.EmployeeDTO;
import co.edu.unbosque.db2.payroll_proyect.model.entity.Employee;
import co.edu.unbosque.db2.payroll_proyect.model.entity.EmployeeWithBank;

//DataMapper de empleado
@Component
public class EmployeeMapper implements DataMapper<Employee, EmployeeDTO> {

    //Convierto en DTO
    @Override
    public EmployeeDTO toDTO(Employee entity) {
        if (entity == null) return null;

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setStatus(entity.getStatus());
        dto.setBirthDate(entity.getBirthDate());
        dto.setNit(entity.getNit());
        dto.setStartDate(entity.getStartDate());
        dto.setDocumentType(entity.getDocumentType());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setAddress(entity.getAddress());

        return dto;
    }

    //Convierto en entidad
    @Override
    public Employee toEntity(EmployeeDTO dto) {
        if (dto == null) return null;

        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setStatus(dto.getStatus());
        employee.setBirthDate(dto.getBirthDate());
        employee.setNit(dto.getNit());
        employee.setStartDate(dto.getStartDate());
        employee.setDocumentType(dto.getDocumentType());
        employee.setPhone(dto.getPhone());
        employee.setEmail(dto.getEmail());
        employee.setAddress(dto.getAddress());

        return employee;
    }

    public static EmployeeDTO toDTOBank(EmployeeWithBank entity) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setSecondName(entity.getSecondName());
        dto.setFLastName(entity.getFLastName());
        dto.setSLastName(entity.getSLastName());
        dto.setName(entity.getName());
        dto.setStatus(entity.getStatus());
        dto.setBirthDate(entity.getBirthDate());
        dto.setNit(entity.getNit());
        dto.setStartDate(entity.getStartDate());
        dto.setDocumentType(entity.getDocumentType());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setAddress(entity.getAddress());
        dto.setBankId(entity.getBankId());
        dto.setAccountNumber(entity.getAccountNumber());
        dto.setAccountType(entity.getAccountType());
        return dto;
    }

}
