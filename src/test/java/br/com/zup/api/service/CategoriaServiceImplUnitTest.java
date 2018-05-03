package br.com.zup.api.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.zup.api.converter.CategoriaConverter;
import br.com.zup.api.dto.CategoriaDTO;
import br.com.zup.api.exception.CategoriaNotFoundException;
import br.com.zup.api.model.Categoria;
import br.com.zup.api.objectfactory.CategoriaMother;
import br.com.zup.api.repository.CategoriaRepository;
import br.com.zup.api.service.CategoriaServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CategoriaServiceImplUnitTest {

	@InjectMocks
	private CategoriaServiceImpl categoriaServiceImpl;

	@Mock
	private CategoriaRepository categoriaRepository;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	private static final String DESCRIPTION_MODEL_TEST = CategoriaMother.DESCRIPTION_MODEL_TEST;

	@Test
	public void findCategoriaSuggestionByDescriptionTest() {
		final List<Categoria> listCategoria = CategoriaMother.getListCategoriaModelPattern();
		Mockito.when(categoriaRepository.findByDescriptionContainingIgnoreCase(DESCRIPTION_MODEL_TEST)).thenReturn(listCategoria);
		final List<CategoriaDTO> listReturn = categoriaServiceImpl.findCategoriaSuggestionByDescription(DESCRIPTION_MODEL_TEST);

		Assert.assertEquals(listReturn.size(), 1);
		Assert.assertEquals(listCategoria.get(0), CategoriaConverter.fromDTO(listReturn.get(0)));
	}

	@Test
	public void findCategoriaSuggestionByDescriptionErrorTest() {
		exception.expect(CategoriaNotFoundException.class);
		final CategoriaNotFoundException exception = new CategoriaNotFoundException("Categories not found with this description: "+ DESCRIPTION_MODEL_TEST);
		Mockito.when(categoriaRepository.findByDescriptionContainingIgnoreCase(DESCRIPTION_MODEL_TEST)).thenThrow(exception);
		categoriaServiceImpl.findCategoriaSuggestionByDescription(DESCRIPTION_MODEL_TEST);
	}
}
