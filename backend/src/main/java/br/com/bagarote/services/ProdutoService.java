package br.com.bagarote.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.bagarote.dto.ProdutoDTO;
import br.com.bagarote.model.Empresa;
import br.com.bagarote.model.Produto;
import br.com.bagarote.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
		
	public ResponseEntity<List<ProdutoDTO>> getAll() {
		List<Produto> list = repository.findAll();
		List<ProdutoDTO> listDTO =  list.stream().map(obj -> new ProdutoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	 
		
    }
	
	@Transactional
	public ProdutoDTO getByIdProduto(Long idProduto) {
		Optional<Produto> obj = repository.findById(idProduto);
		Produto entity = obj.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado para este id informado"));
	    return new ProdutoDTO(entity);
    }
	
	@Transactional
	public ProdutoDTO create(ProdutoDTO objDto) {
	    Produto entity = new Produto();
	    entity.setIdProduto(objDto.getIdProduto());
	    entity.setProduto(objDto.getProduto());
	    entity.setDescricao(objDto.getDescricao());
	    entity.setValorBase(objDto.getValorBase());
	    entity.setImagemProduto(objDto.getImagemProduto());
	    
	    Empresa emp = new Empresa();
	    emp.setIdEmpresa(objDto.getEmpresa().getIdEmpresa());
	    emp.setNomeFantasia(objDto.getEmpresa().getNomeFantasia());
	    entity.setEmpresa(emp);
	    
	    entity = repository.save(entity);
	    return new ProdutoDTO(entity);
	    	
    }
	
	
	@Transactional
	public ProdutoDTO update(Long idProduto, ProdutoDTO objDto) {
	    try {
	    	 Produto entity = new Produto();
	 	    entity.setIdProduto(objDto.getIdProduto());
	 	    entity.setProduto(objDto.getProduto());
	 	    entity.setDescricao(objDto.getDescricao());
	 	    entity.setValorBase(objDto.getValorBase());
	 	    entity.setImagemProduto(objDto.getImagemProduto());
	 	    
	 	    Empresa emp = new Empresa();
	 	    emp.setIdEmpresa(objDto.getEmpresa().getIdEmpresa());
	 	    emp.setNomeFantasia(objDto.getEmpresa().getNomeFantasia());
	 	    entity.setEmpresa(emp);
	 	    
	 	    entity = repository.save(entity);
	 	    return new ProdutoDTO(entity);
			
		} catch (EntityNotFoundException ex) {
			throw new ResourceNotFoundException("Id informado não consta no banco de dados " + idProduto);
		}
		
	}
		
}
