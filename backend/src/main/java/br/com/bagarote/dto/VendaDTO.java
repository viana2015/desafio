package br.com.bagarote.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bagarote.model.MetodoPagamento;
import br.com.bagarote.model.Venda;
import br.com.bagarote.model.VendaProduto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class VendaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idVenda;
	
	private Long idCliente;
	private String nomeCliente;
	
	private Long idEmpresa;
	private String nomeFantasia;
	
	
	private List<VendaProdutoDTO> vendaProdutos = new ArrayList<>();


	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataVenda;
	
	private BigDecimal valorTotal;
	private BigDecimal valorDesconto;
	private BigDecimal valorAcrescimo;
	private BigDecimal valorPago;
	
	private MetodoPagamento metodoPagamento;
	
	
	public VendaDTO(Long idVenda, Long idCliente, String nomeCliente, Long idEmpresa, String nomeFantasia,
			LocalDateTime dataVenda, BigDecimal valorTotal, BigDecimal valorDesconto, BigDecimal valorAcrescimo,
			BigDecimal valorPago, MetodoPagamento metodoPagamento) {
		super();
		this.idVenda = idVenda;
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.idEmpresa = idEmpresa;
		this.nomeFantasia = nomeFantasia;
		this.dataVenda = dataVenda;
		this.valorTotal = valorTotal;
		this.valorDesconto = valorDesconto;
		this.valorAcrescimo = valorAcrescimo;
		this.valorPago = valorPago;
		this.metodoPagamento = metodoPagamento;
	}
	
	
	

	public VendaDTO(Venda entity) {
		
		idVenda = entity.getIdVenda();
		idCliente = entity.getCliente().getIdCliente();
		nomeCliente = entity.getCliente().getNome();
		idEmpresa = entity.getEmpresa().getIdEmpresa();
		nomeFantasia = entity.getEmpresa().getNomeFantasia();
		dataVenda = entity.getDataVenda();
		valorTotal = entity.getValorTotal();
		valorDesconto = entity.getValorDesconto();
		valorAcrescimo = entity.getValorAcrescimo();
		valorPago = entity.getValorPago();
		metodoPagamento = entity.getMetodoPagamento();
		vendaProdutos = entity.getProdutos().stream()
				.map(x -> new VendaProdutoDTO(x)).collect(Collectors.toList());
		
	}
	
	/*public VendaDTO(Venda entity, Set<VendaProduto> vendProdutos) {
		this(entity);
		
		vendProdutos.forEach(prod -> this.vendaProdutos.add(new VendaProdutoDTO()));
		//entity.getProdutos().forEach(p->p.getVendaProdutoId().setVenda(entity));
	}
*/
	
	
}
