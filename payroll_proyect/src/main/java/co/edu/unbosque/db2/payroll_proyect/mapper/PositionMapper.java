package co.edu.unbosque.db2.payroll_proyect.mapper;

import org.springframework.stereotype.Component;

import co.edu.unbosque.db2.payroll_proyect.mapper.interfaces.DataMapper;
import co.edu.unbosque.db2.payroll_proyect.model.dto.PositionDTO;
import co.edu.unbosque.db2.payroll_proyect.model.entity.Position;

@Component
public class PositionMapper implements DataMapper<Position, PositionDTO> {

    @Override
    public PositionDTO toDTO(Position entity) {
        if (entity == null) return null;

        PositionDTO dto = new PositionDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setBaseSalary(entity.getBaseSalary());
        dto.setDescription(entity.getDescription());

        return dto;
    }

    @Override
    public Position toEntity(PositionDTO dto) {
        if (dto == null) return null;

        Position entity = new Position();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setBaseSalary(dto.getBaseSalary());
        entity.setDescription(dto.getDescription());

        return entity;
    }
}

