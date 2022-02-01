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
public class EmpresaSimpleDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long idEmpresa;
	private String nomeFantasia;
	
	
	
	public EmpresaSimpleDTO(Empresa entity) {
		super();
		this.idEmpresa = entity.getIdEmpresa();
		this.nomeFantasia = entity.getNomeFantasia();
		
	}
	
	
	
}
