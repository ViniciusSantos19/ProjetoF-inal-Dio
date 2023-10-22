package me.dio.dtos;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AutorDto {
	private String nome;
    private Date dataDeNascimento;
    private String nacionalidade;
}
