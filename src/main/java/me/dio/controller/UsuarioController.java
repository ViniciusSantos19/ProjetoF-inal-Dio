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
import me.dio.dtos.UsuarioDto;
import me.dio.service.UsuarioService;

@RestController
@RequestMapping("/Usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioService service;
	
	@GetMapping
	public List<UsuarioDto> getAllUsuarios() {
		return service.listarTodosUsuarios();
	}
	
	@GetMapping("id/{id}")
	public UsuarioDto getById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public  ResponseEntity<UsuarioDto> deleteUsuarioById(@PathVariable Long id) {
		return service.deletarUsuario(id);
	}
	
	@PutMapping("/{id}")
    @Transactional
	public ResponseEntity<UsuarioDto> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDto usuario) {
		return service.atualizarUsuario(usuario, id);
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDto> inserirUsuario(@RequestBody UsuarioDto usuario,  UriComponentsBuilder uriBuilder) {
		return service.inserirUsuario(usuario, uriBuilder);
	}
	
}
