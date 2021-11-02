package com.bosonit.springboot.db2.content.profesor.application.port;

import com.bosonit.springboot.db2.config.exception.NotFoundException;
import com.bosonit.springboot.db2.config.exception.UnprocesableException;
import com.bosonit.springboot.db2.content.profesor.domain.Profesor;
import com.bosonit.springboot.db2.content.profesor.infraestructure.controller.dto.input.ProfesorInputDTO;
import com.bosonit.springboot.db2.content.profesor.infraestructure.controller.dto.output.ProfesorSimpleOutputDTO;

import java.util.List;
import java.util.Optional;

public interface ProfesorPort {
    Optional<ProfesorSimpleOutputDTO> save(ProfesorInputDTO profesorInputDTO);
    Optional<ProfesorSimpleOutputDTO> getById(String id, String type);
    List<ProfesorSimpleOutputDTO> getAll();
    Optional<ProfesorSimpleOutputDTO> deleteById(String id) throws NotFoundException;
    Profesor getProfesorById(String id);

    Optional<ProfesorSimpleOutputDTO> edit(String id, ProfesorInputDTO profesorInputDTO) throws NotFoundException, UnprocesableException;
}
