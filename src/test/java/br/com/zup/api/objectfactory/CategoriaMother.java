package br.com.zup.api.objectfactory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.zup.api.dto.CategoriaDTO;
import br.com.zup.api.model.Categoria;

@Component
public class CategoriaMother {

	public static final String DESCRIPTION_MODEL_TEST = "Description Categoria";
	public static final String DESCRIPTION_DTO_TEST = "Description DTO";

	public static Categoria getCategoriaModelWithIdPattern() {
		final Categoria categoria = new Categoria();
		categoria.setId(1L);
		categoria.setDescription(DESCRIPTION_MODEL_TEST);
		return categoria;
	}

	public static Categoria getCategoriaModelwithoutIdPattern() {
		final Categoria categoria = new Categoria();
		categoria.setDescription(DESCRIPTION_MODEL_TEST);
		return categoria;
	}

	public static CategoriaDTO getCategoriaDTOPattern() {
		final CategoriaDTO categoria = new CategoriaDTO();
		categoria.setCode(1L);
		categoria.setDescription(DESCRIPTION_DTO_TEST);
		return categoria;
	}

	public static List<Categoria> getListCategoriaModelPattern() {
		final List<Categoria> list = new ArrayList<>();
		list.add(getCategoriaModelWithIdPattern());
		return list;
	}

	public static List<CategoriaDTO> getListCategoriaDTOPattern() {
		final List<CategoriaDTO> list = new ArrayList<>();
		list.add(getCategoriaDTOPattern());
		return list;
	}
}
