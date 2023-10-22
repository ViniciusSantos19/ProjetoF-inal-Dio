package me.dio.dtos;

import java.util.Date;

import lombok.Data;
import me.dio.entity.enums.StatusEntregra;

@Data
public class EmprestimoDto {
    private UsuarioDto usuario;
    private LivroDto livro;
    private Date dataEmprestimo;
    private Date dataDevolucaoPrevista;
    private StatusEntregra status;
}