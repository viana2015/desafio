package br.com.bagarote.dto;

import java.io.Serializable;

import br.com.bagarote.model.Empresa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long idEmpresa;
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private String telefone;
	private String responsavelLegal;
	
	
	public EmpresaDTO(Empresa entity) {
		super();
		this.idEmpresa = entity.getIdEmpresa();
		this.nomeFantasia = entity.getNomeFantasia();
		this.razaoSocial = entity.getRazaoSocial();
		this.cnpj = entity.getCnpj();
		this.telefone = entity.getTelefone();
		this.responsavelLegal = entity.getResponsavelLegal();
		
	}
	
	
	
}
