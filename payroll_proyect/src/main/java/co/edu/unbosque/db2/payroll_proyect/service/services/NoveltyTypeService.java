package co.edu.unbosque.db2.payroll_proyect.service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.db2.payroll_proyect.mapper.interfaces.DataMapper;
import co.edu.unbosque.db2.payroll_proyect.model.dto.NoveltyTypeDTO;
import co.edu.unbosque.db2.payroll_proyect.model.entity.NoveltyType;
import co.edu.unbosque.db2.payroll_proyect.repository.INoveltyTypeRepository;
import co.edu.unbosque.db2.payroll_proyect.service.interfaces.INoveltyTypeService;

@Service
public class NoveltyTypeService implements INoveltyTypeService{

    @Autowired
    private final INoveltyTypeRepository noveltyTypeRepository;

    private final DataMapper<NoveltyType, NoveltyTypeDTO> noveltyTypeMapper;

    public NoveltyTypeService(INoveltyTypeRepository noveltyTypeRepository, DataMapper<NoveltyType, NoveltyTypeDTO> noveltyTypeMapper){
        this.noveltyTypeRepository = noveltyTypeRepository;
        this.noveltyTypeMapper = noveltyTypeMapper;
    }

    @Override
    public NoveltyTypeDTO save(NoveltyTypeDTO dto) {
        NoveltyType noveltyType = noveltyTypeMapper.toEntity(dto);
        NoveltyType saved = noveltyTypeRepository.save(noveltyType);
        return noveltyTypeMapper.toDTO(saved);
    }

    @Override
    public List<NoveltyTypeDTO> findAll() {
       return noveltyTypeRepository.findAll()
                .stream()
                .map(noveltyTypeMapper::toDTO)
                .collect(Collectors.toList());
    }

}
