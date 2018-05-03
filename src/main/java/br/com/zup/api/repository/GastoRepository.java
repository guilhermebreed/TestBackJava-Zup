package br.com.zup.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.api.model.Gasto;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long>{

	List<Gasto> findByUserCodeAndGastoDateBefore(Long idUser, LocalDateTime gastoDate);

	List<Gasto> findByUserCodeAndGastoDateBetween(Long idUser, LocalDateTime startDate, LocalDateTime endDate);
}
