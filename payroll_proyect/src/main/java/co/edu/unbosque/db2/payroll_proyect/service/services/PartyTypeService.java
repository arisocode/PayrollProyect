package co.edu.unbosque.db2.payroll_proyect.service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.db2.payroll_proyect.mapper.interfaces.DataMapper;
import co.edu.unbosque.db2.payroll_proyect.model.dto.PartyTypeDTO;
import co.edu.unbosque.db2.payroll_proyect.model.entity.PartyType;
import co.edu.unbosque.db2.payroll_proyect.repository.IPartyTypeRepository;
import co.edu.unbosque.db2.payroll_proyect.service.interfaces.IPartyTypeService;

@Service
public class PartyTypeService implements IPartyTypeService{

    @Autowired
    private final IPartyTypeRepository partyTypeRepository;

    private final DataMapper<PartyType, PartyTypeDTO> partyTypeMapper;
  
    public PartyTypeService(IPartyTypeRepository partyTypeRepository, DataMapper<PartyType, PartyTypeDTO> partyTypeMapper){
        this.partyTypeRepository = partyTypeRepository;
        this.partyTypeMapper = partyTypeMapper;
    }
  
    @Override
    public PartyTypeDTO save(PartyTypeDTO dto) {
        PartyType partyType = partyTypeMapper.toEntity(dto);
        PartyType saved = partyTypeRepository.save(partyType);
        return partyTypeMapper.toDTO(saved);
    }

    @Override
    public List<PartyTypeDTO> findAll() {
        return partyTypeRepository.findAll()
                .stream()
                .map(partyTypeMapper::toDTO)
                .collect(Collectors.toList());
    }

}
