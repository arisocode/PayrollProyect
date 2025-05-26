package co.edu.unbosque.db2.payroll_proyect.service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.db2.payroll_proyect.mapper.interfaces.DataMapper;
import co.edu.unbosque.db2.payroll_proyect.model.dto.NoveltyDTO;
import co.edu.unbosque.db2.payroll_proyect.model.entity.Novelty;
import co.edu.unbosque.db2.payroll_proyect.repository.IContractRepository;
import co.edu.unbosque.db2.payroll_proyect.repository.INoveltyRepository;
import co.edu.unbosque.db2.payroll_proyect.service.interfaces.INoveltyService;

@Service
public class NoveltyService implements INoveltyService{

    @Autowired
    private final INoveltyRepository noveltyRepository;
    private final IContractRepository contractRepository;
    private final DataMapper<Novelty, NoveltyDTO> noveltyMapper;

    public NoveltyService(INoveltyRepository noveltyRepository, DataMapper<Novelty, NoveltyDTO> noveltyMapper,
                            IContractRepository contractRepository){
        this.noveltyRepository = noveltyRepository;
        this.noveltyMapper = noveltyMapper;
        this.contractRepository = contractRepository;
    }

    @Override
    public NoveltyDTO save(NoveltyDTO dto) {
        Novelty novelty = noveltyMapper.toEntity(dto);
        if (novelty.getContractId() == 0 && dto.getEmployeeId() > 0) {
            contractRepository.findActiveContractByEmployeeId(dto.getEmployeeId())
                .ifPresentOrElse(
                    contract -> novelty.setContractId(contract.getId()),
                    () -> {
                        throw new RuntimeException("No hay contratos activos para el empleado: " + dto.getEmployeeId());
                    }
                );
        }

        Novelty saved = noveltyRepository.save(novelty);
        return noveltyMapper.toDTO(saved);
    }

    @Override
    public List<NoveltyDTO> findAll() {
        return noveltyRepository.findAll()
                .stream()
                .map(noveltyMapper::toDTO)
                .collect(Collectors.toList());
    }
}
