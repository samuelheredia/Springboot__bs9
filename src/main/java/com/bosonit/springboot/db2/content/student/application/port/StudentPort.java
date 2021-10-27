package com.bosonit.springboot.db2.content.student.application.port;

import com.bosonit.springboot.db2.config.exception.NotFoundException;
import com.bosonit.springboot.db2.config.exception.UnprocesableException;
import com.bosonit.springboot.db2.content.persona.infraestructure.controller.dto.input.PersonaInputDTO;
import com.bosonit.springboot.db2.content.persona.infraestructure.controller.dto.output.PersonaOutputDTO;
import com.bosonit.springboot.db2.content.student.infraestructure.controller.dto.input.StudentInputDTO;
import com.bosonit.springboot.db2.content.student.infraestructure.controller.dto.output.StudentOutputDTO;

import java.util.List;
import java.util.Optional;

public interface StudentPort {
    Optional<StudentOutputDTO> save(StudentInputDTO studentInputDTO) throws UnprocesableException;
    Optional<StudentOutputDTO> getById(String id);
    List<StudentOutputDTO> getAll();
    Optional<StudentOutputDTO> deleteById(String id) throws NotFoundException;

    Optional<StudentOutputDTO> edit(int id, StudentInputDTO studentInputDTO) throws NotFoundException, UnprocesableException;
}
