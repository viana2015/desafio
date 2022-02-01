package br.com.bagarote.dto;

import java.io.Serializable;

import br.com.bagarote.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long idCliente;
	private String nome;
	private String cpf;
	private String telefone;
	
	private EmpresaDTO empresa;
	
	
	public ClienteDTO(Cliente entity) {
		super();
		this.idCliente = entity.getIdCliente();
		this.nome = entity.getNome();
		this.cpf = entity.getCpf();
		this.telefone = entity.getCpf();
		//empresaId = entity.getEmpresa().getIdEmpresa();
		//nomeFantasia = entity.getEmpresa().getNomeFantasia();
		empresa = new EmpresaDTO(entity.getEmpresa());
	}
	
}
