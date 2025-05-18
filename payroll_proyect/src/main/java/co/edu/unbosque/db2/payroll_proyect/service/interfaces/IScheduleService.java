package co.edu.unbosque.db2.payroll_proyect.service.interfaces;

import java.util.List;

import co.edu.unbosque.db2.payroll_proyect.model.dto.ScheduleDTO;

public interface IScheduleService {

    ScheduleDTO save(ScheduleDTO dto);
    List<ScheduleDTO> findAll();
}
