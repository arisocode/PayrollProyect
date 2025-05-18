package co.edu.unbosque.db2.payroll_proyect.service.interfaces;

import java.util.List;

import co.edu.unbosque.db2.payroll_proyect.model.dto.ContractTypeDTO;

public interface IContractTypeService {

    ContractTypeDTO save(ContractTypeDTO dto);
    List<ContractTypeDTO> findAll();
}
