package co.edu.unbosque.db2.payroll_proyect.service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.db2.payroll_proyect.mapper.interfaces.DataMapper;
import co.edu.unbosque.db2.payroll_proyect.model.dto.ScheduleDTO;
import co.edu.unbosque.db2.payroll_proyect.model.entity.Schedule;
import co.edu.unbosque.db2.payroll_proyect.repository.IScheduleRepository;
import co.edu.unbosque.db2.payroll_proyect.service.interfaces.IScheduleService;

@Service
public class ScheduleService implements IScheduleService {

    @Autowired
    private final IScheduleRepository scheduleRepository;

    private final DataMapper<Schedule, ScheduleDTO> scheduleMapper;
  
    public ScheduleService(IScheduleRepository scheduleRepository, DataMapper<Schedule, ScheduleDTO> scheduleMapper){
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
    }
  
    @Override
    public ScheduleDTO save(ScheduleDTO dto) {
        Schedule schedule = scheduleMapper.toEntity(dto);
        Schedule saved = scheduleRepository.save(schedule);
        return scheduleMapper.toDTO(saved);
    }

    @Override
    public List<ScheduleDTO> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(scheduleMapper::toDTO)
                .collect(Collectors.toList());
    }


}
