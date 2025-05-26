package co.edu.unbosque.db2.payroll_proyect.service.interfaces;

import java.util.List;

import co.edu.unbosque.db2.payroll_proyect.model.dto.NoveltyTypeDTO;

public interface INoveltyTypeService {

    NoveltyTypeDTO save(NoveltyTypeDTO dto);
    List<NoveltyTypeDTO> findAll();
}
