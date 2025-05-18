package co.edu.unbosque.db2.payroll_proyect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.db2.payroll_proyect.model.entity.Position;

public interface IPositionRepository extends JpaRepository<Position, Integer> {

    Optional<Position> findByCode(String code);
    void deleteByCode(String code);
    boolean existsByCode(String code);

}

