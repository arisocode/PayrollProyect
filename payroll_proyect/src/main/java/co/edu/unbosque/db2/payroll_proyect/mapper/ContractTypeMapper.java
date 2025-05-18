package co.edu.unbosque.db2.payroll_proyect.mapper;

import org.springframework.stereotype.Component;

import co.edu.unbosque.db2.payroll_proyect.mapper.interfaces.DataMapper;
import co.edu.unbosque.db2.payroll_proyect.model.dto.ContractTypeDTO;
import co.edu.unbosque.db2.payroll_proyect.model.entity.ContractType;

@Component
public class ContractTypeMapper implements DataMapper<ContractType, ContractTypeDTO>{

    @Override
    public ContractTypeDTO toDTO(ContractType entity) {
        if (entity == null) return null;

        ContractTypeDTO dto = new ContractTypeDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());

        return dto;
    }

    @Override
    public ContractType toEntity(ContractTypeDTO dto) {
        if (dto == null) return null;

        ContractType entity = new ContractType();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());

        return entity;
    }
    
}
