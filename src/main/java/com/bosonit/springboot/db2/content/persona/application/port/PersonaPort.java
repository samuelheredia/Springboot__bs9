package com.bosonit.springboot.db2.content.persona.application.port;

import com.bosonit.springboot.db2.content.persona.infraestructure.controller.dto.input.PersonaInputDTO;
import com.bosonit.springboot.db2.content.persona.infraestructure.controller.dto.output.PersonaOutputDTO;
import com.bosonit.springboot.db2.exception.NotFoundException;
import com.bosonit.springboot.db2.exception.UnprocesableException;

import java.util.List;
import java.util.Optional;


public interface PersonaPort {
    Optional<PersonaOutputDTO> save(PersonaInputDTO personaInputDTO) throws UnprocesableException;
    Optional<PersonaOutputDTO> getById(int id);
    List<PersonaOutputDTO> getByName(String name);
    List<PersonaOutputDTO> getAll();
    Optional<PersonaOutputDTO> deleteById(int id) throws NotFoundException;

    Optional<PersonaOutputDTO> edit(int id, PersonaInputDTO personaInputDTO) throws NotFoundException, UnprocesableException;
}
