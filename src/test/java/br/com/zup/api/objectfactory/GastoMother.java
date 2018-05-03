package br.com.zup.api.objectfactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.zup.api.dto.GastoDTO;
import br.com.zup.api.model.Gasto;

@Component
public class GastoMother {

	public static final String DESCRIPTION_DTO_TEST = "Description Categoria";

	public static Gasto getGastoModelPattern() {
		final Gasto gasto = new Gasto();
		gasto.setId(1L);
		gasto.setCategoria(CategoriaMother.getCategoriaModelWithIdPattern());
		gasto.setGastoDate(LocalDateTime.of(2018, 5, 1, 0, 0, 0));
		gasto.setUserCode(1L);
		gasto.setValue(20);
		gasto.setVersion(0);
		return gasto;
	}

	public static GastoDTO getGastoDTOPattern() {
		final GastoDTO dto = new GastoDTO();
		dto.setCode(1L);
		dto.setDescription(DESCRIPTION_DTO_TEST);
		dto.setDate(LocalDateTime.of(2018, 5, 1, 0, 0, 0));
		dto.setUserCode(1L);
		dto.setValue(20);
		dto.setVersion(0);
		return dto;
	}

	public static List<Gasto> getListGastoModelPattern() {
		final List<Gasto> list = new ArrayList<>();
		list.add(getGastoModelPattern());
		return list;
	}
}
