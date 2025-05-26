package co.edu.unbosque.db2.payroll_proyect.mapper;

import org.springframework.stereotype.Component;

import co.edu.unbosque.db2.payroll_proyect.mapper.interfaces.DataMapper;
import co.edu.unbosque.db2.payroll_proyect.model.dto.NoveltyTypeDTO;
import co.edu.unbosque.db2.payroll_proyect.model.entity.NoveltyType;

@Component
public class NoveltyTypeMapper implements DataMapper<NoveltyType, NoveltyTypeDTO>{

    @Override
    public NoveltyTypeDTO toDTO(NoveltyType entity) {
       if (entity == null) return null;

        NoveltyTypeDTO dto = new NoveltyTypeDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setIsCalculated(entity.getIsCalculated());

        return dto;
    }

    @Override
    public NoveltyType toEntity(NoveltyTypeDTO dto) {
        if (dto == null) return null;

        NoveltyType entity = new NoveltyType();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setIsCalculated(dto.getIsCalculated());

        return entity;
    }

}
