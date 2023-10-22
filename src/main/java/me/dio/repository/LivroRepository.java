package me.dio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.entity.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

}
