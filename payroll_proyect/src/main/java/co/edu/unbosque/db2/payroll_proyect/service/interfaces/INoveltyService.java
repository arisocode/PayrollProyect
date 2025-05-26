package co.edu.unbosque.db2.payroll_proyect.service.interfaces;

import java.util.List;

import co.edu.unbosque.db2.payroll_proyect.model.dto.NoveltyDTO;

public interface INoveltyService {

    NoveltyDTO save(NoveltyDTO dto);
    List<NoveltyDTO> findAll();
}
