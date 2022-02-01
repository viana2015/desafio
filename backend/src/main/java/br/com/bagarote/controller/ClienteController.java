package br.com.bagarote.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.bagarote.dto.ClienteDTO;
import br.com.bagarote.services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> getAll() {
	    return service.getAll();
    }
	
	@GetMapping("/{idCliente}")
	public ResponseEntity<ClienteDTO> getByIdCliente(@PathVariable Long idCliente) {
	    return ResponseEntity.ok(service.getByIdCliente(idCliente));
    }
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO objDto) {
	    objDto = service.create(objDto);
	    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCliente}").
	    										buildAndExpand(objDto.getIdCliente()).toUri();
	    return ResponseEntity.created(uri).body(objDto);
    }
	
	@PutMapping("/{idCliente}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Long idEmpresa, @PathVariable Long idCliente, @RequestBody ClienteDTO objDto) {
		objDto = service.update(idCliente, objDto);
		return ResponseEntity.ok().body(objDto);
    }
	
	
}
