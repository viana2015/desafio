package br.com.bagarote.dto;

import java.io.Serializable;

import br.com.bagarote.model.ClienteEndereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteEnderecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long idEndereco;
	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String uf;
	
	
	private ClienteDTO cliente;


	public ClienteEnderecoDTO(ClienteEndereco entity) {
		super();
		this.idEndereco = entity.getIdEndereco();
		this.cep = entity.getCep();
		this.logradouro = entity.getLogradouro();
		this.numero = entity.getNumero();
		this.complemento = entity.getComplemento();
		this.bairro = entity.getBairro();
		this.cidade = entity.getCidade();
		this.uf = entity.getUf();
		cliente = new ClienteDTO(entity.getCliente());
	}
	
	
	
}
