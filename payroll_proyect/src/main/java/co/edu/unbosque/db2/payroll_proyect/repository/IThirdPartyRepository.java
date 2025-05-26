package co.edu.unbosque.db2.payroll_proyect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.db2.payroll_proyect.model.entity.Thirdparty;

public interface IThirdPartyRepository extends JpaRepository<Thirdparty, Integer> {

    Optional<Thirdparty> findById(Integer id);
}
