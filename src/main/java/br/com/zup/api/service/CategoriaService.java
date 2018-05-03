package br.com.zup.api.service;

import java.util.List;

import br.com.zup.api.dto.CategoriaDTO;

public interface CategoriaService {

	List<CategoriaDTO> findCategoriaSuggestionByDescription(String description);
}
