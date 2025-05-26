package co.edu.unbosque.db2.payroll_proyect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.db2.payroll_proyect.model.entity.ContractThirdParty;

public interface IContractThirdPartyRepository extends JpaRepository<ContractThirdParty, Integer> {
    List<ContractThirdParty> findByContractId(Integer contractId);
}

