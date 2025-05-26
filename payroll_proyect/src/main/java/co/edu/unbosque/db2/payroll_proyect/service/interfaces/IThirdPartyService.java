package co.edu.unbosque.db2.payroll_proyect.service.interfaces;

import java.util.List;

import co.edu.unbosque.db2.payroll_proyect.model.dto.ThirdPartyDTO;

public interface IThirdPartyService {

    ThirdPartyDTO save(ThirdPartyDTO dto);
    ThirdPartyDTO findByNit(String nit);
    void deleteByNiT(String nit);
    List<ThirdPartyDTO> findAll();
}
