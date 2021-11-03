package com.bosonit.springboot.db2.content.persona.infraestructure.repository.port;


import com.bosonit.springboot.db2.content.persona.domain.Persona;

import java.util.List;
import java.util.Optional;


public interface PersonaPortRep {
    Persona save(Persona persona);
    Optional<Persona> getById(int id);
    List<Persona> getByName(String name);
    List<Persona> getAll();
    void deleteById(int id);
}
