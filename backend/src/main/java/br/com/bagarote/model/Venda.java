package br.com.bagarote.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
public class Venda implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceVenda")
	@SequenceGenerator(name = "SequenceVenda", sequenceName = "SEQ_VENDA", allocationSize = 1)
	@EqualsAndHashCode.Include
	private Long idVenda;
	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name = "ID_EMPRESA")
	private Empresa empresa;
	@Column(name = "DT_VENDA")
	private LocalDateTime dataVenda;

	private BigDecimal valorTotal;
	private BigDecimal valorDesconto;
	private BigDecimal valorAcrescimo;
	private BigDecimal valorPago;
	@Enumerated(EnumType.STRING)
	private MetodoPagamento metodoPagamento;
	
	@Setter(value = AccessLevel.NONE)
	@OneToMany(mappedBy = "vendaProdutoId.venda")
	private List<VendaProduto> produtos = new ArrayList<>();

	public Venda(Long idVenda, Cliente cliente, Empresa empresa, LocalDateTime dataVenda, BigDecimal valorTotal,
			BigDecimal valorDesconto, BigDecimal valorAcrescimo, BigDecimal valorPago,
			MetodoPagamento metodoPagamento) {
		super();
		this.idVenda = idVenda;
		this.cliente = cliente;
		this.empresa = empresa;
		this.dataVenda = dataVenda;
		this.valorTotal = valorTotal;
		this.valorDesconto = valorDesconto;
		this.valorAcrescimo = valorAcrescimo;
		this.valorPago = valorPago;
		this.metodoPagamento = metodoPagamento;
	}

	
}
