package me.dio.dtos;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LivroDto {
    private String titulo;
    private String isbn;
    private String genero;
    private Date anoPublicacao;
    private int quantidade;
    private AutorDto autor;
}
