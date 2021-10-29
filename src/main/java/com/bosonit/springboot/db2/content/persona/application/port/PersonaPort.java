package com.bosonit.springboot.db2.content.persona.application.port;

import com.bosonit.springboot.db2.content.persona.infraestructure.controller.dto.input.PersonaInputDTO;
import com.bosonit.springboot.db2.content.persona.infraestructure.controller.dto.output.PersonaOutputDTO;
import com.bosonit.springboot.db2.config.exception.NotFoundException;
import com.bosonit.springboot.db2.config.exception.UnprocesableException;
import com.bosonit.springboot.db2.content.persona.infraestructure.controller.dto.output.PersonaProfesorOutputDTO;

import java.util.List;
import java.util.Optional;


public interface PersonaPort {
    Optional<PersonaOutputDTO> save(PersonaInputDTO personaInputDTO) throws UnprocesableException;
    Optional<PersonaOutputDTO> getById(int id, String type);
    List<PersonaOutputDTO> getByName(String name, String type);
    List<PersonaOutputDTO> getAll(String type);
    Optional<PersonaOutputDTO> deleteById(int id) throws NotFoundException;

    Optional<PersonaOutputDTO> edit(int id, PersonaInputDTO personaInputDTO) throws NotFoundException, UnprocesableException;
}
