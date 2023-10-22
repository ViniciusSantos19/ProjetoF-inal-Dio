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

import me.dio.dtos.AutorDto;
import me.dio.entity.Autor;
import me.dio.repository.AutorRepository;

@Service
public class AutorService {
	@Autowired
	AutorRepository repositorio;
	
	@Autowired
	ModelMapper mapper;
	
	private List<AutorDto> converterEmAutorDto(List<Autor> autores) {
        List<AutorDto> autorDtos = autores.stream()
            .map(autor -> mapper.map(autor, AutorDto.class))
            .collect(Collectors.toList());
        return autorDtos;
    }

    public ResponseEntity<AutorDto> inserirAutor(AutorDto autorDto, UriComponentsBuilder
    		uriBuilder) {
        Autor autor = mapper.map(autorDto, Autor.class);
        repositorio.save(autor);
        URI url = uriBuilder.path("/autores/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(url).body(autorDto);
    }

    public ResponseEntity<AutorDto> atualizarAutor(AutorDto autorDto, Long id) {
        Optional<Autor> autorOptional = repositorio.findById(id);
        if (autorOptional.isPresent()) {
            Autor autor = autorOptional.get();
            mapper.map(autorDto, autor);
            repositorio.save(autor);
            return new ResponseEntity<AutorDto>(autorDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<AutorDto> deletarAutor(Long id) {
        Optional<Autor> autorOptional = repositorio.findById(id);
        if (autorOptional.isPresent()) {
            Autor autor = autorOptional.get();
            AutorDto autorDto = mapper.map(autor, AutorDto.class);
            repositorio.delete(autor);
            return new ResponseEntity<AutorDto>(autorDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public List<AutorDto> listarTodosAutores() {
        List<Autor> autores = repositorio.findAll();
        return converterEmAutorDto(autores);
    }

    public AutorDto findById(Long id) {
        Optional<Autor> autorOptional = repositorio.findById(id);
        if (autorOptional.isPresent()) {
            Autor autor = autorOptional.get();
            return mapper.map(autor, AutorDto.class);
        }
        return null;
    }
	
}
