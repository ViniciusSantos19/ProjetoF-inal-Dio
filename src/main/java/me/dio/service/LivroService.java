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

import me.dio.dtos.LivroDto;
import me.dio.entity.Livro;
import me.dio.repository.LivroRepository;

@Service
public class LivroService {
	@Autowired
	LivroRepository repositorio;

	@Autowired
	ModelMapper mapper;

	private List<LivroDto> converterEmLivroDto(List<Livro> livros) {
		List<LivroDto> livroDtos = livros.stream().map(livro -> mapper.map(livro, LivroDto.class))
				.collect(Collectors.toList());
		return livroDtos;
	}

	public ResponseEntity<LivroDto> inserirLivro(LivroDto livroDto, UriComponentsBuilder uriBuilder) {
		Livro livro = mapper.map(livroDto, Livro.class);
		repositorio.save(livro);
		URI url = uriBuilder.path("/livros/{id}").buildAndExpand(livro.getId()).toUri();
		return ResponseEntity.created(url).body(livroDto);
	}

	public ResponseEntity<LivroDto> atualizarLivro(LivroDto livroDto, Long id) {
		Optional<Livro> livroOptional = repositorio.findById(id);
		if (livroOptional.isPresent()) {
			Livro livro = livroOptional.get();
			mapper.map(livroDto, livro);
			repositorio.save(livro);
			return new ResponseEntity<LivroDto>(livroDto, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<LivroDto> deletarLivro(Long id) {
		Optional<Livro> livroOptional = repositorio.findById(id);
		if (livroOptional.isPresent()) {
			Livro livro = livroOptional.get();
			LivroDto livroDto = mapper.map(livro, LivroDto.class);
			repositorio.delete(livro);
			return new ResponseEntity<>(livroDto, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public List<LivroDto> listarTodosLivros() {
		List<Livro> livros = repositorio.findAll();
		return converterEmLivroDto(livros);
	}

	public LivroDto findById(Long id) {
		Optional<Livro> livroOptional = repositorio.findById(id);
		if (livroOptional.isPresent()) {
			Livro livro = livroOptional.get();
			return mapper.map(livro, LivroDto.class);
		}
		return null;
	}

}
