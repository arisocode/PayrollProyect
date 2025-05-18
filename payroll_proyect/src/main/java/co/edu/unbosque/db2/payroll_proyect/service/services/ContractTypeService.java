package co.edu.unbosque.db2.payroll_proyect.service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.db2.payroll_proyect.mapper.interfaces.DataMapper;
import co.edu.unbosque.db2.payroll_proyect.model.dto.ContractTypeDTO;
import co.edu.unbosque.db2.payroll_proyect.model.entity.ContractType;
import co.edu.unbosque.db2.payroll_proyect.repository.IContractTypeRepository;
import co.edu.unbosque.db2.payroll_proyect.service.interfaces.IContractTypeService;

@Service
public class ContractTypeService implements IContractTypeService{

    @Autowired
    private final IContractTypeRepository contractTypeReposiroty;

    private final DataMapper<ContractType, ContractTypeDTO> contractTypeMapper;

    public ContractTypeService(IContractTypeRepository contractTypeReposiroty, DataMapper<ContractType, ContractTypeDTO> contractTypeMapper){
        this.contractTypeReposiroty = contractTypeReposiroty;
        this.contractTypeMapper = contractTypeMapper;
    }

    @Override
    public ContractTypeDTO save(ContractTypeDTO dto) {
        ContractType contractType = contractTypeMapper.toEntity(dto);
        ContractType saved = contractTypeReposiroty.save(contractType);
        return contractTypeMapper.toDTO(saved);
    }

    @Override
    public List<ContractTypeDTO> findAll() {
        return contractTypeReposiroty.findAll()
                .stream()
                .map(contractTypeMapper::toDTO)
                .collect(Collectors.toList());
    }
}
