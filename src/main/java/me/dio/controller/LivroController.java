package me.dio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import me.dio.dtos.LivroDto;
import me.dio.service.LivroService;

@RestController
@RequestMapping("/Livros")
public class LivroController {
	
	@Autowired
    LivroService service;

    @GetMapping
    public List<LivroDto> getAllLivros() {
        return service.listarTodosLivros();
    }

    @GetMapping("/{id}")
    public LivroDto getLivroById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<LivroDto> deleteLivroById(@PathVariable Long id) {
        return service.deletarLivro(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<LivroDto> atualizarLivro(@PathVariable Long id, @RequestBody LivroDto livro) {
        return service.atualizarLivro(livro, id);
    }

    @PostMapping
    public ResponseEntity<LivroDto> inserirLivro(@RequestBody LivroDto livro, UriComponentsBuilder uriBuilder) {
        return service.inserirLivro(livro, uriBuilder);
    }
	
}
