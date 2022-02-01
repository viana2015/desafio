package br.com.bagarote.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.bagarote.dto.ClienteDTO;
import br.com.bagarote.model.Cliente;
import br.com.bagarote.model.Empresa;
import br.com.bagarote.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
		
	public ResponseEntity<List<ClienteDTO>> getAll() {
		List<Cliente> list = repository.findAll();
		List<ClienteDTO> listDTO =  list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	 
		
    }
	
	
	public ClienteDTO getByIdCliente(Long idCliente) {
		Optional<Cliente> obj = repository.findById(idCliente);
		Cliente entity = obj.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado para este id informado"));
	    return new ClienteDTO(entity);
    }
	
	
	public ClienteDTO create(ClienteDTO objDto) {
	    Cliente entity = new Cliente();
	    entity.setNome(objDto.getNome());
	    entity.setCpf(objDto.getCpf());
	    entity.setTelefone(objDto.getTelefone()); 
	    
	    Empresa emp = new Empresa();
	    emp.setIdEmpresa(objDto.getEmpresa().getIdEmpresa());
	    emp.setNomeFantasia(objDto.getEmpresa().getNomeFantasia());
	    entity.setEmpresa(emp);
	    
	    entity = repository.save(entity);
	    return new ClienteDTO(entity);
	    	
    }
	
	
	
	public ClienteDTO update(Long idCliente, ClienteDTO objDto) {
	    try {
	    	Cliente entity = repository.getById(idCliente);
	    	entity.setNome(objDto.getNome());
	    	entity.setCpf(objDto.getCpf());
	    	entity.setTelefone(objDto.getTelefone());
	    	
	    	Empresa emp = new Empresa();
		    //emp.setIdEmpresa(objDto.getEmpresaId());
		    //emp.setNomeFantasia(objDto.getNomeFantasia());
		    entity.setEmpresa(emp);
	    	
	    	entity = repository.save(entity);
	    	return new ClienteDTO(entity);
			
		} catch (EntityNotFoundException ex) {
			throw new ResourceNotFoundException("Id informado não consta no banco de dados " + idCliente);
		}
		
	}
		
}
