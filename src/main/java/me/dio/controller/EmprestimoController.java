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
import me.dio.dtos.EmprestimoDto;
import me.dio.service.EmprestimoService;

@RestController
@RequestMapping("/Emprestimos")
public class EmprestimoController {
	
	@Autowired
    EmprestimoService service;

    @GetMapping
    public List<EmprestimoDto> getAllEmprestimos() {
        return service.listarTodosEmprestimos();
    }

    @GetMapping("/{id}")
    public EmprestimoDto getEmprestimoById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<EmprestimoDto> deleteEmprestimoById(@PathVariable Long id) {
        return service.deletarEmprestimo(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EmprestimoDto> atualizarEmprestimo(@PathVariable Long id, @RequestBody EmprestimoDto emprestimo) {
        return service.atualizarEmprestimo(emprestimo, id);
    }

    @PostMapping
    public ResponseEntity<EmprestimoDto> inserirEmprestimo(@RequestBody EmprestimoDto emprestimo, UriComponentsBuilder uriBuilder) {
        return service.inserirEmprestimo(emprestimo, uriBuilder);
    }
	
}
