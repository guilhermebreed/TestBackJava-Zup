package br.com.zup.api.controller;

import java.net.URI;
import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zup.api.converter.GastoConverter;
import br.com.zup.api.dto.GastoDTO;
import br.com.zup.api.service.GastoService;

@RequestMapping("/api/v1/gastos")
@CrossOrigin
@RestController
public class GastoController {

	@Autowired
	private GastoService gastoService;

	@PostMapping("")
	public ResponseEntity<?> insert(@RequestBody final GastoDTO dto){
		final GastoDTO savedGasto= gastoService.insert(dto);
		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedGasto.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody final GastoDTO dto){
		gastoService.update(dto);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{userCode}")
	public ResponseEntity<?> findGastosByUserCode(@PathVariable final Long userCode){
		return new ResponseEntity<>(gastoService.findGastosByUserCode(userCode), HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<?> findGastosByFilter(@RequestParam final Map<String, String> params) throws ParseException{
		return new ResponseEntity<>(gastoService.findGastosByFilter(GastoConverter.toDTO(params)), HttpStatus.OK);
	}
}
