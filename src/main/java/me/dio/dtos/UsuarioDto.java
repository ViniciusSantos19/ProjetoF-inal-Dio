package me.dio.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UsuarioDto {
	 	private String nome;
	    private String sobrenome;
	    private String senha;
	    private String email;
	    private String telefone;
}
