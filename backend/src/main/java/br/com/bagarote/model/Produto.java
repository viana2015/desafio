package br.com.bagarote.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "SequenceProduto", sequenceName = "SEQ_PRODUTO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceProduto")
	@EqualsAndHashCode.Include
	private Long idProduto;
	private String produto;
	private String descricao;
	private BigDecimal valorBase;
	private byte[] imagemProduto;
	
	@ManyToOne
	@JoinColumn(name = "ID_EMPRESA")
	private Empresa empresa;

	public Produto(Long idProduto, String produto, String descricao, BigDecimal valorBase, byte[] imagemProduto,
			Empresa empresa) {
		super();
		this.idProduto = idProduto;
		this.produto = produto;
		this.descricao = descricao;
		this.valorBase = valorBase;
		this.imagemProduto = imagemProduto;
		this.empresa = empresa;
	}
	
	
}
