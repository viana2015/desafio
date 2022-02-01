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

import br.com.bagarote.dto.ProdutoDTO;
import br.com.bagarote.services.ProdutoService;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> getAll() {
	    return service.getAll();
    }
	
	@GetMapping("/{idProduto}")
	public ResponseEntity<ProdutoDTO> getByIdProduto(@PathVariable Long idProduto) {
	    return ResponseEntity.ok(service.getByIdProduto(idProduto));
    }
	@PostMapping
	public ResponseEntity<ProdutoDTO> create(@RequestBody ProdutoDTO objDto) {
	    objDto = service.create(objDto);
	    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idProduto}").
	    										buildAndExpand(objDto.getIdProduto()).toUri();
	    return ResponseEntity.created(uri).body(objDto);
    }
	
	@PutMapping("/{idProduto}")
	public ResponseEntity<ProdutoDTO> update(@PathVariable Long idEmpresa, @PathVariable Long idProduto, @RequestBody ProdutoDTO objDto) {
		objDto = service.update(idProduto, objDto);
		return ResponseEntity.ok().body(objDto);
    }
	
}
