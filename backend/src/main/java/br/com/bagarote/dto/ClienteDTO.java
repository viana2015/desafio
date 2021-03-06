package br.com.bagarote.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.bagarote.model.Cliente;
import br.com.bagarote.model.ClienteEndereco;
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
	
	//private EmpresaDTO empresa;
	
	//private List<ClienteEndereco> enderecos = new ArrayList<>();
	
	
	public ClienteDTO(Cliente entity) {
		super();
		this.idCliente = entity.getIdCliente();
		this.nome = entity.getNome();
		this.cpf = entity.getCpf();
		this.telefone = entity.getCpf();
		//empresaId = entity.getEmpresa().getIdEmpresa();
		//nomeFantasia = entity.getEmpresa().getNomeFantasia();
		//empresa = new EmpresaDTO(entity.getEmpresa());
		
	}
	
	
}
