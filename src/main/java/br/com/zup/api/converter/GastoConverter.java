package br.com.zup.api.converter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.zup.api.dto.GastoDTO;
import br.com.zup.api.model.Categoria;
import br.com.zup.api.model.Gasto;

public class GastoConverter {

	public static Gasto fromDTO(final GastoDTO dto) {
		final Gasto gasto= new Gasto();
		final Categoria categoria= new Categoria();
		gasto.setCategoria(categoria);
		gasto.setId(dto.getCode());
		gasto.getCategoria().setDescription(dto.getDescription());
		gasto.setUserCode(dto.getUserCode());
		gasto.setGastoDate(dto.getDate());
		gasto.setValue(dto.getValue());
		gasto.setVersion(dto.getVersion());
		return gasto;
	}

	public static GastoDTO toDTO(final Gasto model) {
		final GastoDTO gastoDTO= new GastoDTO();
		gastoDTO.setCode(model.getId());
		if(model.getCategoria() != null) {
			gastoDTO.setDescription(model.getCategoria().getDescription());
		}
		gastoDTO.setUserCode(model.getUserCode());
		gastoDTO.setDate(model.getGastoDate());
		gastoDTO.setValue(model.getValue());
		gastoDTO.setVersion(model.getVersion());
		return gastoDTO;
	}

	public static List<GastoDTO> toDTO(final List<Gasto> list){
		final List<GastoDTO> results = new ArrayList<>();
		for (final Gasto gasto : list){
			results.add(toDTO(gasto));
		}
		return results;
	}

	public static GastoDTO toDTO(final Map<String, String> params) throws ParseException {
		final GastoDTO dto = new GastoDTO();

		for (final Map.Entry<String, String> entry : params.entrySet()) {
			if(entry.getKey().equalsIgnoreCase("userCode")) {
				dto.setUserCode(Long.parseLong(entry.getValue()));
			}
			if(entry.getKey().equalsIgnoreCase("date")) {
				final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				dto.setDate(LocalDateTime.parse(entry.getValue(), formatter));
			}
		}
		return dto;
	}
}
