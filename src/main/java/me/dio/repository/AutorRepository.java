package me.dio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.entity.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{

}
