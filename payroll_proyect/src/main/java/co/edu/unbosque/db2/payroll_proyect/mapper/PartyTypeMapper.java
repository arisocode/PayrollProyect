package co.edu.unbosque.db2.payroll_proyect.mapper;

import org.springframework.stereotype.Component;

import co.edu.unbosque.db2.payroll_proyect.mapper.interfaces.DataMapper;
import co.edu.unbosque.db2.payroll_proyect.model.dto.PartyTypeDTO;
import co.edu.unbosque.db2.payroll_proyect.model.entity.PartyType;

@Component
public class PartyTypeMapper implements DataMapper<PartyType, PartyTypeDTO>{

    @Override
    public PartyTypeDTO toDTO(PartyType entity) {
        if (entity == null) return null;

        PartyTypeDTO dto = new PartyTypeDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());

        return dto;
    }

    @Override
    public PartyType toEntity(PartyTypeDTO dto) {
        if (dto == null) return null;

        PartyType entity = new PartyType();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        return entity;
    }

}
