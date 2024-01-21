package com.aaron.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aaron.entity.Usuario;

public interface UsuarioReposioty extends JpaRepository<Usuario, Integer>{

}
