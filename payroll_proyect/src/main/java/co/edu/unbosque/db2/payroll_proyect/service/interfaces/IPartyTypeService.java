package co.edu.unbosque.db2.payroll_proyect.service.interfaces;

import java.util.List;

import co.edu.unbosque.db2.payroll_proyect.model.dto.PartyTypeDTO;

public interface IPartyTypeService {

    PartyTypeDTO save(PartyTypeDTO dto);
    List<PartyTypeDTO> findAll();
}
