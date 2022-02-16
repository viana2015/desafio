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

import br.com.bagarote.dto.EmpresaDTO;
import br.com.bagarote.dto.EmpresaSimpleDTO;
import br.com.bagarote.services.EmpresaService;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
	
	@Autowired
	private EmpresaService service;
	
	@GetMapping
	public ResponseEntity<List<EmpresaSimpleDTO>> getAll() {
		return service.getAll();
    }
	
	@GetMapping("/{idEmpresa}")
	public ResponseEntity<EmpresaDTO> getByIdCliente(@PathVariable Long idEmpresa) {
	    return ResponseEntity.ok(service.getByIdEmpresa(idEmpresa));
    }
	@PostMapping
	public ResponseEntity<EmpresaDTO> create(@RequestBody EmpresaDTO objDto) {
	    objDto = service.create(objDto);
	    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idEmpresa}").
	    										buildAndExpand(objDto.getIdEmpresa()).toUri();
	    return ResponseEntity.created(uri).body(objDto);
    }
	
	@PutMapping("/{idEmpresa}")
	public ResponseEntity<EmpresaDTO> update(@PathVariable Long idEmpresa, @RequestBody EmpresaDTO objDto) {
		objDto = service.update(idEmpresa, objDto);
		return ResponseEntity.ok().body(objDto);
    }
	
	
}
