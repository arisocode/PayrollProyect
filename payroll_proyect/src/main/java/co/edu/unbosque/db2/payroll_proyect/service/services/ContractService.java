package co.edu.unbosque.db2.payroll_proyect.service.services;

import java.sql.Types;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import co.edu.unbosque.db2.payroll_proyect.mapper.ContractMapper;
import co.edu.unbosque.db2.payroll_proyect.mapper.ThirdPartyMapper;
import co.edu.unbosque.db2.payroll_proyect.model.dto.ContractDTO;
import co.edu.unbosque.db2.payroll_proyect.model.dto.ContractThirdPartyDTO;
import co.edu.unbosque.db2.payroll_proyect.model.dto.ThirdPartyDTO;
import co.edu.unbosque.db2.payroll_proyect.model.entity.Contract;
import co.edu.unbosque.db2.payroll_proyect.model.entity.ContractThirdParty;
import co.edu.unbosque.db2.payroll_proyect.model.entity.Thirdparty;
import co.edu.unbosque.db2.payroll_proyect.repository.IContractRepository;
import co.edu.unbosque.db2.payroll_proyect.repository.IContractThirdPartyRepository;
import co.edu.unbosque.db2.payroll_proyect.repository.IThirdPartyRepository;
import co.edu.unbosque.db2.payroll_proyect.service.interfaces.IContractService;

@Service
public class ContractService implements IContractService{

    private final IContractRepository contractRepository;
    private final ContractMapper contractMapper;
    private final SimpleJdbcCall createContractProcedure;
    private final SimpleJdbcCall otroSiContractProcedure;
    private final IContractThirdPartyRepository contractThirdPartyRepository;
    private final IThirdPartyRepository thirdPartyRepository;
    private final ThirdPartyMapper thirdPartyMapper;


    @Autowired
    public ContractService(
            IContractRepository contractRepository,
            ContractMapper contractMapper,
            DataSource dataSource, IContractThirdPartyRepository contractThirdPartyRepository,
            IThirdPartyRepository thirdPartyRepository, ThirdPartyMapper thirdPartyMapper) {

        this.contractRepository = contractRepository;
        this.contractMapper = contractMapper;
        this.contractThirdPartyRepository = contractThirdPartyRepository;
        this.thirdPartyRepository = thirdPartyRepository;
        this.thirdPartyMapper = thirdPartyMapper;

        this.createContractProcedure = new SimpleJdbcCall(dataSource)
                .withProcedureName("SP_SaveContract")
                .declareParameters(
                        new SqlParameter("p_contractXml", Types.LONGVARCHAR),
                        new SqlOutParameter("p_code", Types.INTEGER),
                        new SqlOutParameter("p_message", Types.VARCHAR)
                );

        this.otroSiContractProcedure = new SimpleJdbcCall(dataSource)
            .withProcedureName("SP_OtroSiContract")
            .declareParameters(
                    new SqlParameter("p_contractXml", Types.LONGVARCHAR),
                    new SqlOutParameter("p_code", Types.INTEGER),
                    new SqlOutParameter("p_message", Types.VARCHAR)
            );
    }

    @Override
    public ContractDTO save(ContractDTO dto) {
        System.out.println("DTO recibido: " + dto);
        Map<String, Object> result = createContractViaProcedure(dto);

        Integer code = (Integer) result.get("p_code");
        String message = (String) result.get("p_message");

        System.out.println("Resultado SP - código: " + code + ", mensaje: " + message);

        if (code != null && code == 0) {
            return dto;
        } else {
            throw new RuntimeException("Error al crear contrato: " + message);
        }
    }

    private Map<String, Object> createContractViaProcedure(ContractDTO dto) {
        String xml = buildContractXml(dto);
        System.out.println("XML generado:\n" + xml);

        return createContractProcedure.execute(Map.of("p_contractXml", xml));
    }

    private String buildContractXml(ContractDTO dto) {
        StringBuilder thirdPartiesXml = new StringBuilder();
        if (dto.getThirdParties() != null) {
            for (ContractThirdPartyDTO tp : dto.getThirdParties()) {
                thirdPartiesXml.append("""
                    <thirdParty>
                        <nit>%s</nit>
                        <percentage>%s</percentage>
                    </thirdParty>
                    """.formatted(
                        tp.getNit(),
                        tp.getPercentage() / 100
                    )
                );
            }
        }

        String endDateXml = "";
        if (dto.getEndDate() != null) {
            endDateXml = "<endDate>%s</endDate>\n".formatted(dto.getEndDate());
        }

        return """
            <contract>
                <code>%s</code>
                <contractTypeId>%d</contractTypeId>
                <startDate>%s</startDate>
                %s
                <salary>%s</salary>
                <modificationDate>%s</modificationDate>
                <status>%d</status>
                <paymentPeriod>%d</paymentPeriod>
                <paymentHour>%s</paymentHour>
                <employeeId>%d</employeeId>
                <scheduleId>%d</scheduleId>
                <jobPositionId>%d</jobPositionId>
                <thirdParties>
                    %s
                </thirdParties>
            </contract>
            """.formatted(
                dto.getCode(),
                dto.getContractTypeId(),
                dto.getStartDate(),
                endDateXml,
                dto.getSalary(),
                dto.getModificationDate(),
                dto.getStatus(),
                dto.getPaymentPeriod(),
                dto.getPaymentHour(),
                dto.getEmployeeId(),
                dto.getScheduleId(),
                dto.getJobPositionId(),
                thirdPartiesXml.toString()
            );
    }

    @Override
    public ContractDTO findByCode(String code) {
        Optional<Contract> contractOpt = contractRepository.findByCodeAndStatus(code, 1);
        if (contractOpt.isEmpty()) {
            return null;
        }
        Contract contract = contractOpt.get();
        ContractDTO dto = contractMapper.toDTO(contract);

        List<ContractThirdParty> relations = contractThirdPartyRepository.findByContractId(contract.getId());

        List<ContractThirdPartyDTO> thirdPartyDTOs = relations.stream()
            .map(relation -> {
                Optional<Thirdparty> thirdPartyOpt = thirdPartyRepository.findById(relation.getThirdPartyId());

                String nit = thirdPartyOpt
                    .map(thirdPartyMapper::toDTO)
                    .map(ThirdPartyDTO::getNit)
                    .orElse("N/A");

                ContractThirdPartyDTO tpDto = new ContractThirdPartyDTO();
                tpDto.setNit(nit);
                tpDto.setPercentage(relation.getPercentage() * 100);
                return tpDto;
            })
            .toList();

        dto.setThirdParties(thirdPartyDTOs);

        return dto;
    }

    @Override
    public ContractDTO createOtroSi(ContractDTO dto) {
        System.out.println("DTO recibido: " + dto);
        Map<String, Object> result = createOtroSiContractProcedure(dto);

        Integer code = (Integer) result.get("p_code");
        String message = (String) result.get("p_message");

        System.out.println("Resultado SP - código: " + code + ", mensaje: " + message);

        if (code != null && code == 0) {
            return dto;
        } else {
            throw new RuntimeException("Error al crear Otro si: " + message);
        }
    }

    private Map<String, Object> createOtroSiContractProcedure(ContractDTO dto) {
        String xml = buildContractXml(dto);
        System.out.println("XML generado:\n" + xml);

        return otroSiContractProcedure.execute(Map.of("p_contractXml", xml));
    }

    @Override
    public List<ContractDTO> findAll() {
        return Collections.emptyList();
    }


}
