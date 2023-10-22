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
import me.dio.dtos.AutorDto;
import me.dio.service.AutorService;

@RestController
@RequestMapping("/Autores")
public class AutorController {

	@Autowired
	AutorService service;
	
	@GetMapping
    public List<AutorDto> getAllAutores() {
        return service.listarTodosAutores();
    }

    @GetMapping("/{id}")
    public AutorDto getAutorById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<AutorDto> deleteAutorById(@PathVariable Long id) {
        return service.deletarAutor(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AutorDto> atualizarAutor(@PathVariable Long id, @RequestBody AutorDto autor) {
        return service.atualizarAutor(autor, id);
    }

    @PostMapping
    public ResponseEntity<AutorDto> inserirAutor(@RequestBody AutorDto autor, UriComponentsBuilder uriBuilder) {
        return service.inserirAutor(autor, uriBuilder);
    }
	
}
