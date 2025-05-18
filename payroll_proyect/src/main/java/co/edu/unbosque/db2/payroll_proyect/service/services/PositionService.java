package co.edu.unbosque.db2.payroll_proyect.service.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.db2.payroll_proyect.mapper.interfaces.DataMapper;
import co.edu.unbosque.db2.payroll_proyect.model.dto.PositionDTO;
import co.edu.unbosque.db2.payroll_proyect.model.entity.Position;
import co.edu.unbosque.db2.payroll_proyect.repository.IPositionRepository;
import co.edu.unbosque.db2.payroll_proyect.service.interfaces.IPositionService;

@Service
public class PositionService implements IPositionService{

    @Autowired
    private IPositionRepository positionRepository;

    private final DataMapper<Position, PositionDTO> positionMapper;

    public PositionService(IPositionRepository positionRepository, DataMapper<Position, PositionDTO> positionMapper) {
        this.positionRepository = positionRepository;
        this.positionMapper = positionMapper;
    }

    @Override
    public PositionDTO save(PositionDTO dto) {
        if (positionRepository.existsByCode(dto.getCode())) {
            throw new IllegalArgumentException("Ya existe un cargo con ese código");
        }
        Position position = positionMapper.toEntity(dto);
        Position saved = positionRepository.save(position);
        return positionMapper.toDTO(saved);
    }

    @Override
    public PositionDTO findByCode(String code) {
        Optional<Position> positionOpt = positionRepository.findByCode(code);
        return positionOpt.map(positionMapper::toDTO).orElse(null);
    }

    @Override
    public void deleteByCode(String code) {
        Optional<Position> positionOpt = positionRepository.findByCode(code);
        positionOpt.ifPresent(positionRepository::delete);
    }

    @Override
    public List<PositionDTO> findAll() {
        return positionRepository.findAll()
                .stream()
                .map(positionMapper::toDTO)
                .collect(Collectors.toList());
    }

}
