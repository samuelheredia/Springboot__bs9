package com.bosonit.springboot.db1;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, String> { //<Entidad, PrimaryKey>

}
