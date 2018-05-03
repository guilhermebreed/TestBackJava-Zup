package br.com.zup.api.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.zup.api.dto.CategoriaDTO;
import br.com.zup.api.model.Categoria;

public class CategoriaConverter {

	public static Categoria fromDTO(final CategoriaDTO dto) {
		final Categoria model= new Categoria();
		model.setId(dto.getCode());
		model.setDescription(dto.getDescription());
		return model;
	}

	public static CategoriaDTO toDTO(final Categoria model) {
		final CategoriaDTO dto= new CategoriaDTO();
		dto.setCode(model.getId());
		dto.setDescription(model.getDescription());
		return dto;
	}

	public static List<CategoriaDTO> toDTO(final List<Categoria> list){
		final List<CategoriaDTO> results = new ArrayList<>();
		for (final Categoria model : list){
			results.add(toDTO(model));
		}
		return results;
	}
}
