package br.com.bagarote.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bagarote.dto.VendaDTO;
import br.com.bagarote.model.Cliente;
import br.com.bagarote.model.Empresa;
import br.com.bagarote.model.MetodoPagamento;
import br.com.bagarote.model.Venda;
import br.com.bagarote.repository.VendaProdutoRepository;
import br.com.bagarote.repository.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository repository;
	
	@Autowired
	private VendaProdutoRepository vendaProdutoRepository;
	

	@Transactional(readOnly = true)
	public ResponseEntity<Page<VendaDTO>> getAll(Pageable pageable) {
		Page<Venda> list = repository.findAll(pageable);
		Page<VendaDTO> listDTO =  list.map(obj -> new VendaDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	 		
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
	
		public VendaDTO insert (VendaDTO obj) {
			
			Venda entity = new Venda();
			
			entity.setDataVenda(obj.getDataVenda());
		    entity.setValorTotal(obj.getValorTotal());
		    entity.setValorDesconto(obj.getValorDesconto());
		    entity.setValorAcrescimo(obj.getValorAcrescimo());
		    entity.setValorPago(obj.getValorPago());
		    entity.setMetodoPagamento(MetodoPagamento.PENDENTE);
		       
		    Empresa emp = new Empresa();
		    emp.setIdEmpresa(obj.getIdEmpresa());
		    emp.setNomeFantasia(obj.getNomeFantasia());
		    entity.setEmpresa(emp);
		    
		    Cliente cli = new Cliente();
		    cli.setIdCliente(obj.getIdCliente());
		    cli.setNome(obj.getNomeCliente());
		    entity.setCliente(cli);
			
		/*for (VendaProdutoDTO vendDto : obj.getVendaProdutos()) {
				//VendaProduto vnd = new VendaProduto();
				//vnd.setVendaProdutoId(vendDto.getVendaProdutoId());
				VendaProduto vnd = vendaProdutoRepository.getById(vendDto.getVendaProdutoId());
				entity.getProdutos().add(vnd);
			}*/
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
	    
	   /* entity.getProdutos().clear();
	    for (VendaProdutoDTO produtoDTO : objDto.getVendaProdutos()) {
			VendaProduto vendaProduto = vendaProdutoRepository.getById(produtoDTO.getVendaProdutoId());
			entity.getProdutos().add(vendaProduto);
			
		}
	    */
	   
	   
	}
	
	
		
	
}
