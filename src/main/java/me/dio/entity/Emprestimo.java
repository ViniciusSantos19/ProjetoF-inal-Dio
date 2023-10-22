package me.dio.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dio.entity.enums.StatusEntregra;

@Data
@NoArgsConstructor
@Entity
@Table(name = "emprestimos")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "id_livro")
    private Livro livro;
    private Date dataEmprestimo;
    private Date dataDevolucaoPrevista;
    @Enumerated(EnumType.STRING)
    private StatusEntregra status; 

}
