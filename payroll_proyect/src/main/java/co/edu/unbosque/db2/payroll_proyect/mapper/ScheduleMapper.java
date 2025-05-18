package co.edu.unbosque.db2.payroll_proyect.mapper;

import org.springframework.stereotype.Component;

import co.edu.unbosque.db2.payroll_proyect.mapper.interfaces.DataMapper;
import co.edu.unbosque.db2.payroll_proyect.model.dto.ScheduleDTO;
import co.edu.unbosque.db2.payroll_proyect.model.entity.Schedule;

@Component
public class ScheduleMapper implements DataMapper<Schedule, ScheduleDTO>{

    @Override
    public ScheduleDTO toDTO(Schedule entity) {
        if (entity == null) return null;

        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setStartHour(entity.getStartHour());
        dto.setEndHour(entity.getEndHour());
        dto.setDescription(entity.getDescription());

        return dto;
    }

    @Override
    public Schedule toEntity(ScheduleDTO dto) {
        if (dto == null) return null;

        Schedule entity = new Schedule();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setStartHour(dto.getStartHour());
        entity.setEndHour(dto.getEndHour());
        entity.setDescription(dto.getDescription());

        return entity;
    }

}
