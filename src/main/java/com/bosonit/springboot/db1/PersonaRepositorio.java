package com.bosonit.springboot.db1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonaRepositorio extends JpaRepository<Persona, Integer> { //<Entidad, PrimaryKey>{

    @Query("select p from Persona p where p.name like :name order by name")
    List<Persona> buscaPorNombre(@Param("name") String name);

    List<Persona> findIsLikeNameOrderByName(String name);

    List<Persona> findByName(String name);
}
