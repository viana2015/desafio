package br.com.bagarote.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bagarote.dto.VendaDTO;
import br.com.bagarote.model.Cliente;
import br.com.bagarote.model.Empresa;
import br.com.bagarote.model.MetodoPagamento;
import br.com.bagarote.model.Venda;
import br.com.bagarote.repository.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository repository;
	
	@Transactional(readOnly = true)
	public Page<VendaDTO> getAll(Pageable pageable) {
		Page<Venda> list = repository.findAll(pageable);
		Page<VendaDTO> listDTO =  list.map(obj -> new VendaDTO(obj));
		return listDTO;
	 		
    }
	
	@Transactional(readOnly = true)
	public VendaDTO getByIdVenda(Long idVenda) {
		Optional<Venda> obj = repository.findById(idVenda);
		Venda entity = obj.orElseThrow(() -> new ResourceNotFoundException("Venda não encontrado para este id informado"));
	    return new VendaDTO(entity);
    }
	
	
	@Transactional
	public VendaDTO create(VendaDTO objDto) {
	    Venda entity = new Venda();
	    copiaDtoParaEntidade(objDto, entity);
	    entity = repository.save(entity);
	    return new VendaDTO(entity);
	    	
    }
	
	private void copiaDtoParaEntidade(VendaDTO objDto, Venda entity) {
		
		entity.setDataVenda(objDto.getDataVenda());
	    entity.setValorTotal(objDto.getValorTotal());
	    entity.setValorDesconto(objDto.getValorDesconto());
	    entity.setValorAcrescimo(objDto.getValorAcrescimo());
	    entity.setValorPago(objDto.getValorPago());
	    entity.setMetodoPagamento(MetodoPagamento.PENDENTE);
	       
	    Empresa emp = new Empresa();
	    emp.setIdEmpresa(objDto.getIdEmpresa());
	    entity.setEmpresa(emp);
	    
	    Cliente cli = new Cliente();
	    cli.setIdCliente(objDto.getIdCliente());
	    entity.setCliente(cli);
	    
	    Venda entityVenda = new Venda();
	    entity.getProdutos().forEach(p->p.getVendaProdutoId().setVenda(entityVenda));
			
	   
	}
	
}
