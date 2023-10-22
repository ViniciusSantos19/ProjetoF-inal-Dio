package me.dio.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import me.dio.dtos.UsuarioDto;
import me.dio.entity.Usuario;
import me.dio.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repositorio;
	@Autowired
	private ModelMapper mapper;

	private List<UsuarioDto> converterEmUsuarioDto(List<Usuario> usuarios) {
		List<UsuarioDto> usuarioDtos = usuarios.stream().map(usuario -> mapper.map(usuario, UsuarioDto.class))
				.collect(Collectors.toList());
		return usuarioDtos;
	}

	public ResponseEntity<UsuarioDto> inserirUsuario(UsuarioDto usuarioDto, UriComponentsBuilder uriBuilder) {
		Usuario usuario = mapper.map(usuarioDto, Usuario.class);

		repositorio.save(usuario);

		URI url = uriBuilder.path("/Usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

		return ResponseEntity.created(url).body(usuarioDto);
	}

	public ResponseEntity<UsuarioDto> atualizarUsuario(UsuarioDto usuarioDto, Long id) {
		Optional<Usuario> usuarioOptinal = repositorio.findById(id);

		if (usuarioOptinal.isPresent()) {
			Usuario usuario = usuarioOptinal.get();
			mapper.map(usuarioOptinal, usuario);
			repositorio.save(usuario);
			return new ResponseEntity<UsuarioDto>(usuarioDto, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<UsuarioDto> deletarUsuario(Long id) {
		Optional<Usuario> usuarioOptinal = repositorio.findById(id);

		if (usuarioOptinal.isPresent()) {
			Usuario usuario = usuarioOptinal.get();
			UsuarioDto usuarioDto = mapper.map(usuario, UsuarioDto.class);
			repositorio.delete(usuario);
			return new ResponseEntity<UsuarioDto>(usuarioDto, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public List<UsuarioDto> listarTodosUsuarios() {
		List<Usuario> usuarios = repositorio.findAll();
		return converterEmUsuarioDto(usuarios);
	}

	public UsuarioDto findById(Long id) {
		Optional<Usuario> usuarioOptional = repositorio.findById(id);
		if (usuarioOptional.isPresent()) {
			Usuario usuario = usuarioOptional.get();
			return mapper.map(usuario, UsuarioDto.class);
		}
		return null;
	}
}
