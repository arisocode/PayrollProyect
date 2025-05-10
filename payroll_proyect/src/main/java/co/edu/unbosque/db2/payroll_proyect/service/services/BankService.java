package co.edu.unbosque.db2.payroll_proyect.service.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.db2.payroll_proyect.mapper.interfaces.DataMapper;
import co.edu.unbosque.db2.payroll_proyect.model.dto.BankDTO;
import co.edu.unbosque.db2.payroll_proyect.model.entity.Bank;
import co.edu.unbosque.db2.payroll_proyect.repository.IBankRepository;
import co.edu.unbosque.db2.payroll_proyect.service.interfaces.IBankService;

@Service
public class BankService implements IBankService {

    @Autowired
    private IBankRepository bankRepository;

    private final DataMapper<Bank, BankDTO> bankMapper;

    public BankService(IBankRepository bankRepository, DataMapper<Bank, BankDTO> bankMapper) {
        this.bankRepository = bankRepository;
        this.bankMapper = bankMapper;
    }

    @Override
    public BankDTO save(BankDTO dto) {
        if (bankRepository.existsByCode(dto.getCode())) {
            throw new IllegalArgumentException("Ya existe un banco con ese código");
        }
        Bank bank = bankMapper.toEntity(dto);
        Bank saved = bankRepository.save(bank);
        return bankMapper.toDTO(saved);
    }

    @Override
    public BankDTO findByCode(String code) {
        Optional<Bank> bankOpt = bankRepository.findByCode(code);
        return bankOpt.map(bankMapper::toDTO).orElse(null);
    }

    @Override
    public void deleteByCode(String code) {
        Optional<Bank> bankOpt = bankRepository.findByCode(code);
        bankOpt.ifPresent(bankRepository::delete);
    }

    @Override
    public List<BankDTO> findAll() {
        return bankRepository.findAll()
                .stream()
                .map(bankMapper::toDTO)
                .collect(Collectors.toList());
    }
}
