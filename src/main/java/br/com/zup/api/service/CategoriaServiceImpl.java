package br.com.zup.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.api.converter.CategoriaConverter;
import br.com.zup.api.dto.CategoriaDTO;
import br.com.zup.api.exception.CategoriaNotFoundException;
import br.com.zup.api.model.Categoria;
import br.com.zup.api.repository.CategoriaRepository;

@Transactional(readOnly = true)
@Service
public class CategoriaServiceImpl implements CategoriaService{

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public List<CategoriaDTO> findCategoriaSuggestionByDescription(final String description) {
		final List<Categoria> categorias = categoriaRepository.findByDescriptionContainingIgnoreCase(description);
		if(categorias.isEmpty()) {
			throw new CategoriaNotFoundException("Nenhuma categoria encontrada com a descrição: "+ description);
		}
		return CategoriaConverter.toDTO(categorias);
	}

}
