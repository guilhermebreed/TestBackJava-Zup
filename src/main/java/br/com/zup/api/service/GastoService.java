package br.com.zup.api.service;

import java.util.List;

import br.com.zup.api.dto.GastoDTO;

public interface GastoService {

	GastoDTO insert(GastoDTO gastoDTO);

	GastoDTO update(GastoDTO gastoDTO);

	List<GastoDTO> findGastosByUserCode(Long userCode);

	List<GastoDTO> findGastosByFilter(GastoDTO gastoDTO);
}
