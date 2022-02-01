package br.com.bagarote.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.bagarote.dto.EmpresaDTO;
import br.com.bagarote.dto.EmpresaSimpleDTO;
import br.com.bagarote.model.Empresa;
import br.com.bagarote.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository repository;
	
	public ResponseEntity<List<EmpresaSimpleDTO>> getAll() {
		List<Empresa> entity = repository.findAll();
		List<EmpresaSimpleDTO> listDTO =  entity.stream().map(obj -> new EmpresaSimpleDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	 
		
    }
	
	public EmpresaDTO getByIdEmpresa(Long idEmpresa) {
		Optional<Empresa> obj = repository.findById(idEmpresa);
		Empresa entity = obj.orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrado para este id informado"));
	    return new EmpresaDTO(entity);
    }
	
	@Transactional
	public EmpresaDTO create(EmpresaDTO objDto) {
	    Empresa entity = new Empresa();
	    entity.setNomeFantasia(objDto.getNomeFantasia());
	    entity.setRazaoSocial(objDto.getRazaoSocial());
	    entity.setCnpj(objDto.getCnpj());
	    entity.setTelefone(objDto.getTelefone());
	    entity.setResponsavelLegal(objDto.getResponsavelLegal());
	    entity = repository.save(entity);
	    return new EmpresaDTO(entity);
	    	
    }
		
	@Transactional
	public EmpresaDTO update(Long idEmpresa, EmpresaDTO objDto) {
	    try {
	    	Empresa entity = repository.getById(idEmpresa);
	    	entity.setNomeFantasia(objDto.getNomeFantasia());
		    entity.setRazaoSocial(objDto.getRazaoSocial());
		    entity.setCnpj(objDto.getCnpj());
		    entity.setTelefone(objDto.getTelefone());
		    entity.setResponsavelLegal(objDto.getResponsavelLegal());
	    	entity = repository.save(entity);
	    	return new EmpresaDTO(entity);
			
		} catch (EntityNotFoundException ex) {
			throw new ResourceNotFoundException("Id informado não consta no banco de dados " + idEmpresa);
		}
		
	}
		
}
