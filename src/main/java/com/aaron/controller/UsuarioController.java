package com.aaron.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aaron.entity.Usuario;
import com.aaron.exception.UserNotFoundException;
import com.aaron.repository.UsuarioReposioty;



@RestController
// Se le manda el puerto de la aplicacion en react
@CrossOrigin("http://localhost:3000")
public class UsuarioController {

	@Autowired 
	private UsuarioReposioty repository;
	
	//Guardar Usuario
	@PostMapping("/usuario")
	Usuario newUsuario (@RequestBody Usuario newUsuario) {
		return repository.save(newUsuario);
	}
	
	@GetMapping("/usuarios")
	List<Usuario> listarUsuarios() {
		return repository.findAll();
	}
	
	// Buscar Usuario por ID
	@GetMapping("/user/{id_user}")
	Usuario getUserById(@PathVariable int id_user) {
		return repository.findById(id_user)
				// Importar desde aqui para que lo pueda eredar automaticamente 
				// la clase UserNotFoundException 
				.orElseThrow(() -> new UserNotFoundException(id_user)); // Manejo de errores al buscar
	}
	
	// Actualizar Usuario
	@PutMapping("/user/{id_user}")
	Usuario updateUser(@RequestBody Usuario usuario, @PathVariable int id_user) {
		return repository.findById(id_user)
				.map(user -> {
					user.setName(usuario.getName());
					user.setApellido(usuario.getApellido());
					user.setEmail(usuario.getEmail());
					return repository.save(usuario);
				}).orElseThrow(() -> new UserNotFoundException(id_user));
	}
	
	// Eliminar Usuario
	@DeleteMapping("/user/{id_user}")
	String deleteUser(@PathVariable int id_user) {
		if(!repository.existsById(id_user)) {
			throw new UserNotFoundException(id_user);
		}
		repository.deleteById(id_user);
		return "Usuario con id "+id_user+" eliminado correctamente.";
	}
}
