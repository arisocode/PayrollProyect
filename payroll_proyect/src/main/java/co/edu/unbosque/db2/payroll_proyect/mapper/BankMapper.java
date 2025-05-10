package co.edu.unbosque.db2.payroll_proyect.mapper;

import org.springframework.stereotype.Component;

import co.edu.unbosque.db2.payroll_proyect.mapper.interfaces.DataMapper;
import co.edu.unbosque.db2.payroll_proyect.model.dto.BankDTO;
import co.edu.unbosque.db2.payroll_proyect.model.entity.Bank;

@Component
public class BankMapper implements DataMapper<Bank, BankDTO> {

    // Convertir entidad a DTO
    @Override
    public BankDTO toDTO(Bank entity) {
        if (entity == null) return null;

        BankDTO dto = new BankDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setBankType(entity.getBankType());
        dto.setCountry(entity.getCountry());
        dto.setAddress(entity.getAddress());
        dto.setStatus(entity.getStatus());

        return dto;
    }

    // Convertir DTO a entidad
    @Override
    public Bank toEntity(BankDTO dto) {
        if (dto == null) return null;

        Bank bank = new Bank();
        bank.setId(dto.getId());
        bank.setCode(dto.getCode());
        bank.setName(dto.getName());
        bank.setBankType(dto.getBankType());
        bank.setCountry(dto.getCountry());
        bank.setAddress(dto.getAddress());
        bank.setStatus(dto.getStatus());

        return bank;
    }
}