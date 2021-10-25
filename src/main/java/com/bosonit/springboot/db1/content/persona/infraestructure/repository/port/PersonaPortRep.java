package com.bosonit.springboot.db1.content.persona.infraestructure.repository.port;


import com.bosonit.springboot.db1.content.persona.domain.Persona;
import com.bosonit.springboot.db1.content.persona.infraestructure.controller.dto.input.PersonaInputDTO;
import com.bosonit.springboot.db1.content.persona.infraestructure.controller.dto.output.PersonaOutputDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface PersonaPortRep {
    Optional<Persona> save(Persona persona);
    Optional<Persona> getById(int id);
    List<Persona> getByName(String name);
    List<Persona> getAll();
    void deleteById(int id);
}
