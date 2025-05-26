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

import co.edu.unbosque.db2.payroll_proyect.mapper.ThirdPartyMapper;
import co.edu.unbosque.db2.payroll_proyect.model.dto.ThirdPartyDTO;
import co.edu.unbosque.db2.payroll_proyect.model.entity.ThirdPartyBank;
import co.edu.unbosque.db2.payroll_proyect.repository.IThirdPartyBankRepository;
import co.edu.unbosque.db2.payroll_proyect.repository.IThirdPartyRepository;
import co.edu.unbosque.db2.payroll_proyect.service.interfaces.IThirdPartyService;

@Service
public class ThirdPartyService implements IThirdPartyService{

    private final IThirdPartyBankRepository thirdPartyBankRepository;
    private final IThirdPartyRepository thirdPartyRepository;
    private final ThirdPartyMapper thirdPartyMapper;
    private final SimpleJdbcCall createThirdPartyProcedure;
    private final SimpleJdbcCall deleteThirdPartyProcedure;

    @Autowired
    public ThirdPartyService(
            IThirdPartyBankRepository thirdPartyBankRepository,
            ThirdPartyMapper thirdPartyMapper,
            DataSource dataSource, IThirdPartyRepository thirdPartyRepository) {

        this.thirdPartyBankRepository = thirdPartyBankRepository;
        this.thirdPartyRepository = thirdPartyRepository;
        this.thirdPartyMapper = thirdPartyMapper;

        this.createThirdPartyProcedure = new SimpleJdbcCall(dataSource)
                .withProcedureName("SP_CreateThirdParty")
                .declareParameters(
                        new SqlParameter("p_thirdPartyXml", Types.LONGVARCHAR),
                        new SqlOutParameter("p_code", Types.INTEGER),
                        new SqlOutParameter("p_message", Types.VARCHAR)
                );

        this.deleteThirdPartyProcedure = new SimpleJdbcCall(dataSource)
            .withProcedureName("SP_DeleteThirdParty")
            .declareParameters(
                    new SqlParameter("p_nit", Types.VARCHAR),
                    new SqlOutParameter("p_code", Types.INTEGER),
                    new SqlOutParameter("p_message", Types.VARCHAR)
            );
    }

    @Override
    public ThirdPartyDTO save(ThirdPartyDTO dto) {
        Map<String, Object> result = createThirdPartyViaProcedure(dto);

        Integer code = (Integer) result.get("p_code");
        String message = (String) result.get("p_message");

        System.out.println("Resultado SP - código: " + code + ", mensaje: " + message);

        if (code != null && code == 0) {
            return dto;
        } else {
            throw new RuntimeException("Error al crear tercero: " + message);
        }
    }

    public Map<String, Object> createThirdPartyViaProcedure(ThirdPartyDTO dto) {
        String xml = buildThirdPartyXml(dto);
        System.out.println("XML generado:\n" + xml);

        return createThirdPartyProcedure.execute(Map.of("p_thirdPartyXml", xml));
    }

    private String buildThirdPartyXml(ThirdPartyDTO dto) {
        return """
            <thirdparty>
                <nit>%s</nit>
                <name>%s</name>
                <status>%d</status>
                <phone>%s</phone>
                <email>%s</email>
                <address>%s</address>
                <bankId>%d</bankId>
                <typeId>%d</typeId>
                <accountNumber>%s</accountNumber>
                <accountType>%s</accountType>
            </thirdparty>
            """.formatted(
                dto.getNit(),
                dto.getName(),
                dto.getStatus() ? 1 : 0,
                dto.getPhone(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getBankId(),
                dto.getTypeId(),
                dto.getAccountNumber(),
                dto.getAccountType() ? 1 : 0
        );
    }

    @Override
    public ThirdPartyDTO findByNit(String nit) {
        Optional<ThirdPartyBank> thirdPartyOpt = thirdPartyBankRepository.findByNit(nit);
        return thirdPartyOpt.map(thirdPartyMapper::toDTOBank).orElse(null);
    }

    @Override
    public void deleteByNiT(String nit) {
        Map<String, Object> result = deleteThirdPartyProcedure.execute(Map.of("p_nit", nit));

        Integer code = (Integer) result.get("p_code");
        String message = (String) result.get("p_message");

        System.out.println("Resultado SP - código: " + code + ", mensaje: " + message);

        if (code != null && code != 0) {
            throw new RuntimeException("Error al eliminar contrato: " + message);
        }
    }

    @Override
    public List<ThirdPartyDTO> findAll() {
        return thirdPartyRepository.findAll()
                .stream()
                .map(thirdPartyMapper::toDTO)
                .collect(Collectors.toList());
    }

}
