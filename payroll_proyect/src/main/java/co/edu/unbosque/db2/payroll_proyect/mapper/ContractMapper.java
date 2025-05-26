package co.edu.unbosque.db2.payroll_proyect.mapper;

import org.springframework.stereotype.Component;

import co.edu.unbosque.db2.payroll_proyect.mapper.interfaces.DataMapper;
import co.edu.unbosque.db2.payroll_proyect.model.dto.ContractDTO;
import co.edu.unbosque.db2.payroll_proyect.model.entity.Contract;

@Component
public class ContractMapper implements DataMapper<Contract, ContractDTO> {

    @Override
    public ContractDTO toDTO(Contract entity) {
        if (entity == null) return null;

        ContractDTO dto = new ContractDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setContractTypeId(entity.getContractTypeId());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setSalary(String.valueOf(entity.getSalary()));
        dto.setModificationDate(entity.getModificationDate());
        dto.setStatus(entity.getStatus());
        dto.setPaymentPeriod(entity.getPaymentPeriod());
        dto.setPaymentHour(entity.getPaymentHour());
        dto.setEmployeeId(entity.getEmployeeId());
        dto.setScheduleId(entity.getScheduleId());
        dto.setJobPositionId(entity.getJobPositionId());

        dto.setThirdParties(null);
        return dto;
    }

    @Override
    public Contract toEntity(ContractDTO dto) {
        if (dto == null) return null;
        
        Contract entity = new Contract();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setContractTypeId(dto.getContractTypeId());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setSalary(Double.parseDouble(dto.getSalary()));
        entity.setModificationDate(dto.getModificationDate());
        entity.setStatus(dto.getStatus());
        entity.setPaymentPeriod(dto.getPaymentPeriod());
        entity.setPaymentHour(dto.getPaymentHour());
        entity.setEmployeeId(dto.getEmployeeId());
        entity.setScheduleId(dto.getScheduleId());
        entity.setJobPositionId(dto.getJobPositionId());

        return entity;
    }


}
