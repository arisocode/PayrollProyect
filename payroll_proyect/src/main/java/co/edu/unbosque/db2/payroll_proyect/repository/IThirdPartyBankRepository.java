package co.edu.unbosque.db2.payroll_proyect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.unbosque.db2.payroll_proyect.model.entity.ThirdPartyBank;

public interface IThirdPartyBankRepository extends JpaRepository<ThirdPartyBank, Integer> {

    @Query("SELECT tp FROM ThirdPartyBank tp WHERE tp.nit = :nit")
    Optional<ThirdPartyBank> findByNit(@Param("nit") String nit);
    void deleteByNit(String nit);

}
