package br.com.zup.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.api.converter.GastoConverter;
import br.com.zup.api.dto.GastoDTO;
import br.com.zup.api.exception.GastoNotFoundException;
import br.com.zup.api.exception.OptimisticLockException;
import br.com.zup.api.model.Categoria;
import br.com.zup.api.model.Gasto;
import br.com.zup.api.repository.CategoriaRepository;
import br.com.zup.api.repository.GastoRepository;

@Transactional(readOnly = true)
@Service
public class GastoServiceImpl implements GastoService {

	@Autowired
	private GastoRepository gastoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	private static final String OPTIMISTIC_lOCK = "Gasto já foi atualizado por outra movimentaçãos.";

	@Transactional(readOnly = false)
	@Override
	public GastoDTO insert(final GastoDTO gastoDTO) {
		final Gasto gasto = GastoConverter.fromDTO(gastoDTO);
		gasto.setCategoria(categorizeGastos(gastoDTO.getDescription()));
		return GastoConverter.toDTO(gastoRepository.save(gasto));
	}

	@Transactional(readOnly = false)
	@Override
	public GastoDTO update(final GastoDTO gastoDTO) {
		final Gasto gasto = GastoConverter.fromDTO(gastoDTO);
		gasto.setCategoria(categorizeGastos(gastoDTO.getDescription()));
		validateLockOptimistic(gasto);
		return GastoConverter.toDTO(gastoRepository.save(gasto));
	}

	@Override
	public List<GastoDTO> findGastosByUserCode(final Long userCode) {
		final List<Gasto> gastos = gastoRepository.findByUserCodeAndGastoDateBefore(userCode, getLocalDateTimeMinus5Seconds());
		if (gastos.isEmpty()) {
			throw new GastoNotFoundException("Nenhum gasto encontrado para o usuário: " + userCode);
		}
		return GastoConverter.toDTO(gastos);
	}

	@Override
	public List<GastoDTO> findGastosByFilter(final GastoDTO gastoDTO) {
		final List<Gasto> gastos = gastoRepository.findByUserCodeAndGastoDateBetween(gastoDTO.getUserCode(),
				getLocalDateTimeStartTime(gastoDTO.getDate()), getLocalDateEndTime(gastoDTO.getDate()));
		if (gastos.isEmpty()) {
			throw new GastoNotFoundException("Nenhum gasto encontrado para o usuário: " + gastoDTO.getUserCode()
			+ " and date: " + gastoDTO.getDate());
		}
		return GastoConverter.toDTO(gastos);
	}

	private Categoria categorizeGastos(final String description) {
		Categoria categoria = categoriaRepository.findByDescriptionEqualsIgnoreCase(description);
		if (description != null && categoria == null) {
			categoria = new Categoria();
			categoria.setDescription(description);
			return categoriaRepository.save(categoria);
		}
		return categoria;
	}

	private void validateLockOptimistic(final Gasto gasto) {
		if (!gastoRepository.findById(gasto.getId()).get().getVersion().equals(gasto.getVersion())) {
			throw new OptimisticLockException(OPTIMISTIC_lOCK);
		}
	}

	public static LocalDateTime getLocalDateTimeMinus5Seconds() {
		return LocalDateTime.now().minusSeconds(5);
	}

	public static LocalDateTime getLocalDateTimeStartTime(final LocalDateTime GastoDate) {
		return LocalDateTime.of(GastoDate.getYear(), GastoDate.getMonth(), GastoDate.getDayOfMonth(), 0, 0, 0);
	}

	public static LocalDateTime getLocalDateEndTime(final LocalDateTime GastoDate) {
		return LocalDateTime.of(GastoDate.getYear(), GastoDate.getMonth(), GastoDate.getDayOfMonth(), 23, 59, 59);
	}
}
