package br.com.bagarote.dto;

import java.math.BigDecimal;

import br.com.bagarote.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProdutoDTO {

	private Long idProduto;
	private String produto;
	private String descricao;
	private BigDecimal valorBase;
	private byte[] imagemProduto;
	
	private EmpresaDTO empresa;

	public ProdutoDTO(Produto entity) {
		idProduto = entity.getIdProduto();
		produto = entity.getProduto();
		descricao = entity.getDescricao();
		valorBase = entity.getValorBase();
		imagemProduto = entity.getImagemProduto();
		empresa = new EmpresaDTO(entity.getEmpresa());
	}
	
	
	
	
}
