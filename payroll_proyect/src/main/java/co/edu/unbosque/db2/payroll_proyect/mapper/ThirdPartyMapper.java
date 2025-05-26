package co.edu.unbosque.db2.payroll_proyect.mapper;

import org.springframework.stereotype.Component;

import co.edu.unbosque.db2.payroll_proyect.mapper.interfaces.DataMapper;
import co.edu.unbosque.db2.payroll_proyect.model.dto.ThirdPartyDTO;
import co.edu.unbosque.db2.payroll_proyect.model.entity.ThirdPartyBank;
import co.edu.unbosque.db2.payroll_proyect.model.entity.Thirdparty;

@Component
public class ThirdPartyMapper implements DataMapper<Thirdparty, ThirdPartyDTO> {

    @Override
    public ThirdPartyDTO toDTO(Thirdparty entity) {
        if (entity == null) return null;

        ThirdPartyDTO dto = new ThirdPartyDTO();
        dto.setId(entity.getId());
        dto.setNit(entity.getNit());
        dto.setName(entity.getName());
        dto.setStatus(entity.getStatus());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setAddress(entity.getAddress());
        dto.setTypeId(entity.getTypeId());

        return dto;
    }

    @Override
    public Thirdparty toEntity(ThirdPartyDTO dto) {
        if (dto == null) return null;

        Thirdparty thirdParty = new Thirdparty();
        thirdParty.setId(dto.getId());
        thirdParty.setNit(dto.getNit());
        thirdParty.setName(dto.getName());
        thirdParty.setStatus(dto.getStatus());
        thirdParty.setPhone(dto.getPhone());
        thirdParty.setEmail(dto.getEmail());
        thirdParty.setAddress(dto.getAddress());
        thirdParty.setTypeId(dto.getTypeId());

        return thirdParty;
    }

    public ThirdPartyDTO toDTOBank(ThirdPartyBank entity){
        if (entity == null) return null;

        ThirdPartyDTO dto = new ThirdPartyDTO();
        dto.setId(entity.getId());
        dto.setNit(entity.getNit());
        dto.setName(entity.getName());
        dto.setStatus(entity.getStatus());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setAddress(entity.getAddress());
        dto.setTypeId(entity.getTypeId());
        dto.setBankId(entity.getBankId());
        dto.setAccountNumber(entity.getAccountNumber());
        dto.setAccountType(entity.getAccountType());

        return dto;
    }
}
