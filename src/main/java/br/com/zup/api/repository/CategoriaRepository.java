package br.com.zup.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.api.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	List<Categoria> findByDescriptionContainingIgnoreCase(String description);

	Categoria findByDescriptionEqualsIgnoreCase(String description);
}
