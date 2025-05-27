package co.edu.unbosque.db2.payroll_proyect.service.services;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import co.edu.unbosque.db2.payroll_proyect.model.entity.PayrollLiquidation;
import co.edu.unbosque.db2.payroll_proyect.repository.IPayrollLiquidationRepository;
import co.edu.unbosque.db2.payroll_proyect.service.interfaces.IPayrollLiquidationService;

@Service
public class PayrollLiquidationService implements IPayrollLiquidationService{

    private final IPayrollLiquidationRepository payrollLiquidationRepository;
    private final SimpleJdbcCall liquidateOneEmployee;
    private final SimpleJdbcCall LiquidateAllEmployees;

    public PayrollLiquidationService(DataSource dataSource, IPayrollLiquidationRepository payrollLiquidationRepository){

        this.payrollLiquidationRepository = payrollLiquidationRepository;

        this.liquidateOneEmployee = new SimpleJdbcCall(dataSource)
                .withProcedureName("SP_LiquidateOneEmployee")
                .returningResultSet("result", (rs, rowNum) -> {
                    PayrollLiquidation liq = new PayrollLiquidation();

                    liq.setEmployeeId(rs.getInt("EmployeeId"));
                    liq.setContractId(rs.getInt("ContractId"));
                    liq.setLiquidationDate(rs.getDate("LiquidationDate").toLocalDate());
                    liq.setBasicSalary(rs.getBigDecimal("BasicSalary").doubleValue());
                    liq.setExtraHoursValue(rs.getBigDecimal("ExtraHoursValue").doubleValue());
                    liq.setLegalDeductions(rs.getBigDecimal("LegalDeductions").doubleValue());
                    liq.setTotalValue(rs.getBigDecimal("TotalValue").doubleValue());

                    liq.setStatus(rs.getInt("Status"));
                    return liq;
                })
                .declareParameters(
                        new SqlParameter("p_employeeId", Types.INTEGER),
                        new SqlOutParameter("p_code", Types.INTEGER),
                        new SqlOutParameter("p_message", Types.VARCHAR)
                );

        this.LiquidateAllEmployees = new SimpleJdbcCall(dataSource)
            .withProcedureName("SP_LiquidateMultipleEmployees")
            .declareParameters(
                    new SqlParameter("p_employeeXml", Types.LONGVARCHAR),
                    new SqlOutParameter("p_code", Types.INTEGER),
                    new SqlOutParameter("p_message", Types.VARCHAR)
            );
    }
    
    @Override
    public PayrollLiquidation liquidarEmpleado(Integer employeeId) {
        Map<String, Object> result = createOneLiquidationProcedure(employeeId);

        Integer code = (Integer) result.get("p_code");
        String message = (String) result.get("p_message");

        System.out.println("Resultado SP - código: " + code + ", mensaje: " + message);

        if (code != null && code == 0) {
            System.out.println("Contenido de result: " + result);
            @SuppressWarnings("unchecked")
            List<PayrollLiquidation> liquidaciones = (List<PayrollLiquidation>) result.get("result");

            if (!liquidaciones.isEmpty()) {
                PayrollLiquidation liq = liquidaciones.get(0);
                payrollLiquidationRepository.save(liq);
                System.out.println("Liquidación guardada en MongoDB con ID: " + liq.getId());
                return liq;
            }
        }
        
        throw new RuntimeException("Error al crear empleado: " + message);
    }

    private Map<String, Object> createOneLiquidationProcedure(Integer employeeId) {
        return liquidateOneEmployee.execute(Map.of("p_employeeId", employeeId));
    }

    @Override
    public List<PayrollLiquidation> getByEmployeeId(Integer employeeId) {
        return payrollLiquidationRepository.findByEmployeeId(employeeId);
    }

}
