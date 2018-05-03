package br.com.zup.api.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.zup.api.converter.GastoConverter;
import br.com.zup.api.dto.GastoDTO;
import br.com.zup.api.exception.OptimisticLockException;
import br.com.zup.api.model.Categoria;
import br.com.zup.api.model.Gasto;
import br.com.zup.api.objectfactory.CategoriaMother;
import br.com.zup.api.objectfactory.GastoMother;
import br.com.zup.api.repository.CategoriaRepository;
import br.com.zup.api.repository.GastoRepository;
import br.com.zup.api.service.GastoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class GastoServiceImplUnitTest {

	@InjectMocks
	private GastoServiceImpl gastoServiceImpl;

	@Mock
	private GastoRepository gastoRepository;

	@Mock
	private CategoriaRepository categoriaRepository;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void insertWithExistingCategoriaTest() {
		final GastoDTO dto = GastoMother.getGastoDTOPattern();
		final Categoria categoria = CategoriaMother.getCategoriaModelWithIdPattern();

		final Gasto gastoModel= GastoConverter.fromDTO(dto);
		gastoModel.setCategoria(categoria);

		Mockito.when(categoriaRepository.findByDescriptionEqualsIgnoreCase(dto.getDescription())).thenReturn(categoria);
		Mockito.when(gastoRepository.save(gastoModel)).thenReturn(gastoModel);

		final GastoDTO dtoReturn = gastoServiceImpl.insert(dto);
		Assert.assertEquals(gastoModel, GastoConverter.fromDTO(dtoReturn));
		Assert.assertEquals(gastoModel.getCategoria().getDescription(), GastoConverter.fromDTO(dtoReturn).getCategoria().getDescription());
	}

	@Test
	public void insertWithCategoriaNonexistentTest() {
		final GastoDTO dto = GastoMother.getGastoDTOPattern();
		final Categoria categoriaWithoutId = CategoriaMother.getCategoriaModelwithoutIdPattern();
		final Categoria categoriaWithId = CategoriaMother.getCategoriaModelWithIdPattern();

		final Gasto gastoModel= GastoConverter.fromDTO(dto);
		gastoModel.setCategoria(categoriaWithId);

		Mockito.when(categoriaRepository.findByDescriptionEqualsIgnoreCase(dto.getDescription())).thenReturn(null);
		Mockito.when(categoriaRepository.save(categoriaWithoutId)).thenReturn(categoriaWithId);
		Mockito.when(gastoRepository.save(gastoModel)).thenReturn(gastoModel);

		final GastoDTO dtoReturn = gastoServiceImpl.insert(dto);
		Assert.assertEquals(gastoModel, GastoConverter.fromDTO(dtoReturn));
		Assert.assertEquals(gastoModel.getCategoria().getDescription(), GastoConverter.fromDTO(dtoReturn).getCategoria().getDescription());
	}

	@Test
	public void updateWithExistingCategoriaTest() {
		final GastoDTO dto =GastoMother.getGastoDTOPattern();
		final Categoria categoria= CategoriaMother.getCategoriaModelWithIdPattern();

		final Gasto gastoModel= GastoConverter.fromDTO(dto);
		gastoModel.setCategoria(categoria);
		final Optional<Gasto> optionalGasto = Optional.of(gastoModel);

		Mockito.when(categoriaRepository.findByDescriptionEqualsIgnoreCase(dto.getDescription())).thenReturn(categoria);
		Mockito.when(gastoRepository.save(gastoModel)).thenReturn(gastoModel);
		Mockito.when(gastoRepository.findById(gastoModel.getId())).thenReturn(optionalGasto);

		final GastoDTO dtoReturn = gastoServiceImpl.update(dto);
		Assert.assertEquals(gastoModel, GastoConverter.fromDTO(dtoReturn));
		Assert.assertEquals(gastoModel.getCategoria().getDescription(), GastoConverter.fromDTO(dtoReturn).getCategoria().getDescription());
	}

	@Test
	public void updateWithExistingCategoriaAndOptimisticLockErrorTest() {
		exception.expect(OptimisticLockException.class);
		final GastoDTO dto =GastoMother.getGastoDTOPattern();

		final Categoria categoria= CategoriaMother.getCategoriaModelWithIdPattern();

		final Gasto gastoModel= GastoConverter.fromDTO(dto);
		gastoModel.setCategoria(categoria);
		final Optional<Gasto> optionalGasto = Optional.of(gastoModel);

		dto.setVersion(1);
		Mockito.when(categoriaRepository.findByDescriptionEqualsIgnoreCase(dto.getDescription())).thenReturn(categoria);
		Mockito.when(gastoRepository.findById(gastoModel.getId())).thenReturn(optionalGasto);

		gastoServiceImpl.update(dto);
	}
}
