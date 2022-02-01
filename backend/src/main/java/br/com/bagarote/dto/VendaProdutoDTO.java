package br.com.bagarote.dto;

import java.io.Serializable;
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
public class VendaProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private VendaProdutoId vendaProdutoId;
	private BigDecimal valorUnitario;
	private Integer qtd;
	private BigDecimal valorTotal;
	
	
	public VendaProdutoDTO(VendaProduto entity) {
		super();
		vendaProdutoId = entity.getVendaProdutoId();
		valorUnitario = entity.getValorUnitario();
		qtd = entity.getQtd();
		valorTotal = entity.getValorTotal();
	}
	
	
}
