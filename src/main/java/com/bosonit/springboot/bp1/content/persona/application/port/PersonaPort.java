package com.bosonit.springboot.bp1.content.persona.application.port;

import com.bosonit.springboot.bp1.content.persona.infraestructure.controller.dto.input.PersonaInputDTO;
import com.bosonit.springboot.bp1.content.persona.infraestructure.controller.dto.output.PersonaOutputDTO;

import java.util.List;
import java.util.Optional;


public interface PersonaPort {
    Optional<PersonaOutputDTO> save(PersonaInputDTO personaInputDTO);
    Optional<PersonaOutputDTO> getById(int id);
    List<PersonaOutputDTO> getByName(String name);
    List<PersonaOutputDTO> getAll();
    Optional<PersonaOutputDTO> deleteById(int id) throws Exception;

    Optional<PersonaOutputDTO> edit(int id, PersonaInputDTO personaInputDTO);
}
