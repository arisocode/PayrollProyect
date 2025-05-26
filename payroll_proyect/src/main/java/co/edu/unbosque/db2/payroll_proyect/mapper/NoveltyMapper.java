package co.edu.unbosque.db2.payroll_proyect.mapper;

import org.springframework.stereotype.Component;

import co.edu.unbosque.db2.payroll_proyect.mapper.interfaces.DataMapper;
import co.edu.unbosque.db2.payroll_proyect.model.dto.NoveltyDTO;
import co.edu.unbosque.db2.payroll_proyect.model.entity.Novelty;

@Component
public class NoveltyMapper implements DataMapper<Novelty, NoveltyDTO>{

    @Override
    public NoveltyDTO toDTO(Novelty entity) {
        if (entity == null) return null;

        NoveltyDTO dto = new NoveltyDTO();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setHours(entity.getHours());
        dto.setDays(entity.getDays());
        dto.setStatus(entity.getStatus());
        dto.setDescription(entity.getDescription());
        dto.setNoveltyTypeId(entity.getNoveltyTypeId());
        dto.setEmployeeId(entity.getEmployeeId());
        dto.setContractId(entity.getContractId());

        return dto;
    }

    @Override
    public Novelty toEntity(NoveltyDTO dto) {
        if (dto == null) return null;

        Novelty entity = new Novelty();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setHours(dto.getHours());
        entity.setDays(dto.getDays());
        entity.setStatus(dto.getStatus());
        entity.setDescription(dto.getDescription());
        entity.setNoveltyTypeId(dto.getNoveltyTypeId());
        entity.setEmployeeId(dto.getEmployeeId());
        entity.setContractId(dto.getContractId());

        return entity;
    }
}
