package co.edu.unbosque.db2.payroll_proyect.service.interfaces;

import java.util.List;

import co.edu.unbosque.db2.payroll_proyect.model.dto.ContractDTO;

public interface IContractService {

    ContractDTO save(ContractDTO dto);
    ContractDTO findByCode(String code);
    ContractDTO createOtroSi(ContractDTO dto);
    List<ContractDTO> findAll();
}
