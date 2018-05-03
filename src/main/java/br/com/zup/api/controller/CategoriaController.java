package br.com.zup.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.api.service.CategoriaService;

@RequestMapping("/api/v1/categorias")
@CrossOrigin
@RestController
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/{description}")
	public ResponseEntity<?> findCategoriaSuggestionByDescription(@PathVariable final String description){
		return new ResponseEntity<>(categoriaService.findCategoriaSuggestionByDescription(description), HttpStatus.OK);
	}
}
