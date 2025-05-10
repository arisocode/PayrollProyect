package co.edu.unbosque.db2.payroll_proyect.service.interfaces;

import java.util.List;

import co.edu.unbosque.db2.payroll_proyect.model.dto.BankDTO;

//Interfaz del servicio de bancos
public interface IBankService {

    BankDTO save(BankDTO dto);
    BankDTO findByCode(String code);
    void deleteByCode(String code);
    List<BankDTO> findAll();
}
