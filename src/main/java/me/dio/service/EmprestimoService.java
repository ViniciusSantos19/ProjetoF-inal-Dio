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

import me.dio.dtos.EmprestimoDto;
import me.dio.entity.Emprestimo;
import me.dio.repository.EmprestimoRepository;

@Service
public class EmprestimoService {
	
	@Autowired
	EmprestimoRepository repositorio;

	@Autowired
	ModelMapper mapper;
	
	 private List<EmprestimoDto> converterEmEmprestimoDto(List<Emprestimo> emprestimos) {
	        return emprestimos.stream()
	                .map(emprestimo -> mapper.map(emprestimo, EmprestimoDto.class))
	                .collect(Collectors.toList());
	    }

	    public ResponseEntity<EmprestimoDto> inserirEmprestimo(EmprestimoDto emprestimoDto, UriComponentsBuilder uriBuilder) {
	        Emprestimo emprestimo = mapper.map(emprestimoDto, Emprestimo.class);
	        repositorio.save(emprestimo);
	        URI url = uriBuilder.path("/emprestimos/{id}").buildAndExpand(emprestimo.getId()).toUri();
	        return ResponseEntity.created(url).body(emprestimoDto);
	    }

	    public ResponseEntity<EmprestimoDto> atualizarEmprestimo(EmprestimoDto emprestimoDto, Long id) {
	        Optional<Emprestimo> emprestimoOptional = repositorio.findById(id);
	        if (emprestimoOptional.isPresent()) {
	            Emprestimo emprestimo = emprestimoOptional.get();
	            mapper.map(emprestimoDto, emprestimo);
	            repositorio.save(emprestimo);
	            return new ResponseEntity<>(emprestimoDto, HttpStatus.OK);
	        }
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    public ResponseEntity<EmprestimoDto> deletarEmprestimo(Long id) {
	        Optional<Emprestimo> emprestimoOptional = repositorio.findById(id);
	        if (emprestimoOptional.isPresent()) {
	            Emprestimo emprestimo = emprestimoOptional.get();
	            EmprestimoDto emprestimoDto = mapper.map(emprestimo, EmprestimoDto.class);
	            repositorio.delete(emprestimo);
	            return new ResponseEntity<>(emprestimoDto, HttpStatus.OK);
	        }
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    public List<EmprestimoDto> listarTodosEmprestimos() {
	        List<Emprestimo> emprestimos = repositorio.findAll();
	        return converterEmEmprestimoDto(emprestimos);
	    }

	    public EmprestimoDto findById(Long id) {
	        Optional<Emprestimo> emprestimoOptional = repositorio.findById(id);
	        if (emprestimoOptional.isPresent()) {
	            Emprestimo emprestimo = emprestimoOptional.get();
	            return mapper.map(emprestimo, EmprestimoDto.class);
	        }
	        return null;
	    }
	
}
