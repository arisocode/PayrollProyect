package co.edu.unbosque.db2.payroll_proyect.service.interfaces;

import java.util.List;

import co.edu.unbosque.db2.payroll_proyect.model.dto.PositionDTO;

public interface IPositionService {

    PositionDTO save(PositionDTO dto);
    PositionDTO findByCode(String code);
    void deleteByCode(String code);
    List<PositionDTO> findAll();
}
