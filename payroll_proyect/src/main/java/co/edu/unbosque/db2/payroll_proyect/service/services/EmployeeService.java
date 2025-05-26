package co.edu.unbosque.db2.payroll_proyect.service.services;

import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import co.edu.unbosque.db2.payroll_proyect.mapper.EmployeeMapper;
import co.edu.unbosque.db2.payroll_proyect.model.dto.EmployeeDTO;
import co.edu.unbosque.db2.payroll_proyect.model.entity.EmployeeWithBank;
import co.edu.unbosque.db2.payroll_proyect.repository.IEmployeeBankRepository;
import co.edu.unbosque.db2.payroll_proyect.repository.IEmployeeRepository;
import co.edu.unbosque.db2.payroll_proyect.service.interfaces.IEmployeeService;

//Servicio del empleado
@Service
public class EmployeeService implements IEmployeeService {

    private final IEmployeeBankRepository employeeBankRepository;
    private final IEmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final SimpleJdbcCall createEmployeeProcedure;
    private final SimpleJdbcCall deleteEmployeeProcedure;

    @Autowired
    public EmployeeService(
            IEmployeeBankRepository employeeBankRepository,
            EmployeeMapper employeeMapper,
            DataSource dataSource, IEmployeeRepository employeeRepository) {

        this.employeeBankRepository = employeeBankRepository;
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;

        this.createEmployeeProcedure = new SimpleJdbcCall(dataSource)
                .withProcedureName("SP_CreateEmployee")
                .declareParameters(
                        new SqlParameter("p_employeeXml", Types.LONGVARCHAR),
                        new SqlOutParameter("p_code", Types.INTEGER),
                        new SqlOutParameter("p_message", Types.VARCHAR)
                );

        this.deleteEmployeeProcedure = new SimpleJdbcCall(dataSource)
            .withProcedureName("SP_DeleteEmployee")
            .declareParameters(
                    new SqlParameter("p_nit", Types.VARCHAR),
                    new SqlOutParameter("p_code", Types.INTEGER),
                    new SqlOutParameter("p_message", Types.VARCHAR)
            );
    }

    @Override
    public EmployeeDTO save(EmployeeDTO dto) {
    System.out.println("DTO recibido: " + dto);
    Map<String, Object> result = createEmployeeViaProcedure(dto);

    Integer code = (Integer) result.get("p_code");
    String message = (String) result.get("p_message");

    System.out.println("Resultado SP - código: " + code + ", mensaje: " + message);

    if (code != null && code == 0) {
        return dto;
    } else {
        throw new RuntimeException("Error al crear empleado: " + message);
    }
}

    public Map<String, Object> createEmployeeViaProcedure(EmployeeDTO dto) {
        String xml = buildEmployeeXml(dto);
        System.out.println("XML generado:\n" + xml);

        return createEmployeeProcedure.execute(Map.of("p_employeeXml", xml));
    }

    private String buildEmployeeXml(EmployeeDTO dto) {
        return """
            <employee>
                <nit>%s</nit>
                <name>%s</name>
                <status>%d</status>
                <birthDate>%s</birthDate>
                <startDate>%s</startDate>
                <documentType>%d</documentType>
                <phone>%s</phone>
                <email>%s</email>
                <address>%s</address>
                <bankId>%d</bankId>
                <accountNumber>%s</accountNumber>
                <accountType>%s</accountType>
            </employee>
            """.formatted(
                dto.getNit(),
                dto.getName(),
                dto.getStatus() ? 1 : 0,
                dto.getBirthDate(),
                dto.getStartDate(),
                dto.getDocumentType(),
                dto.getPhone(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getBankId(),
                dto.getAccountNumber(),
                dto.getAccountType() ? 1 : 0
        );
    }

    @Override
    public EmployeeDTO findByNit(String nit) {
        Optional<EmployeeWithBank> employeeOpt = employeeBankRepository.findByNit(nit);
        return employeeOpt.map(employeeMapper::toDTOBank).orElse(null);
    }

    @Override
    public void deleteByNiT(String nit) {
        Map<String, Object> result = deleteEmployeeProcedure.execute(Map.of("p_nit", nit));

        Integer code = (Integer) result.get("p_code");
        String message = (String) result.get("p_message");

        System.out.println("Resultado SP - código: " + code + ", mensaje: " + message);

        if (code != null && code != 0) {
            throw new RuntimeException("Error al eliminar empleado: " + message);
        }
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

}
