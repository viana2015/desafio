package br.com.bagarote.dto;

import java.math.BigDecimal;

import br.com.bagarote.model.VendaProduto;
import br.com.bagarote.model.VendaProduto.VendaProdutoId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VendaProdutoDTO {

	private BigDecimal valorUnitario;
	private Integer qtd;
	private BigDecimal valorTotal;
	private VendaDTO venda;
	private ProdutoDTO produto;
	
	
	public VendaProdutoDTO(VendaProduto entity) {
		//vendaProdutoId = entity.getVendaProdutoId();
		valorUnitario = entity.getValorUnitario();
		qtd = entity.getQtd();
		valorTotal = entity.getValorTotal();
		//venda = new VendaDTO();
		//produto = new ProdutoDTO();
	}
		
	
	
}
