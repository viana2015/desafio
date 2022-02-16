package br.com.bagarote.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.bagarote.dto.VendaDTO;
import br.com.bagarote.services.VendaService;

@RestController
@RequestMapping(value = "/venda")
public class VendaController {
	
	@Autowired
	private VendaService service;
	
	@GetMapping
	public ResponseEntity<Page<VendaDTO>> getAll(Pageable pageable) {
		return service.getAll(pageable);
		//Page<VendaDTO> list = service.getAll(pageable);
	    //return ResponseEntity.ok().body(list);
    }
	
	@PostMapping
	public ResponseEntity<VendaDTO> create(@RequestBody VendaDTO objDto) {
	    objDto = service.insert(objDto);
	    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idVenda}").
	    										buildAndExpand(objDto.getIdCliente()).toUri();
	    return ResponseEntity.created(uri).body(objDto);
    }
	
	/*@PostMapping("venda")
	public ResponseEntity<?> create(@RequestBody Venda createVendaRequest) {
		createVendaRequest.getProdutos().forEach(p->p.getVendaProdutoId().setVenda(createVendaRequest));
	    return ResponseEntity.status(HttpStatus.CREATED).body(vendaRepository.save(createVendaRequest));
    }*/
	
	@GetMapping("/{idVenda}")
	public ResponseEntity<VendaDTO> getByIdCliente(@PathVariable Long idVenda) {
	    return ResponseEntity.ok(service.getByIdVenda(idVenda));
    }
}
