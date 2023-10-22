package me.dio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.entity.Emprestimo;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{

}
