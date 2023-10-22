package me.dio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
