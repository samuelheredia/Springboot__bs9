package com.bosonit.springboot.db2.content.asignatura.application.port;

import com.bosonit.springboot.db2.config.exception.NotFoundException;
import com.bosonit.springboot.db2.config.exception.UnprocesableException;
import com.bosonit.springboot.db2.content.asignatura.domain.Asignatura;
import com.bosonit.springboot.db2.content.asignatura.infraestructure.controller.dto.input.AsignaturaInputDTO;
import com.bosonit.springboot.db2.content.asignatura.infraestructure.controller.dto.output.AsignaturaOutputDTO;

import java.util.List;
import java.util.Optional;

public interface AsignaturaPort {
    Optional<AsignaturaOutputDTO> save(AsignaturaInputDTO asignaturaInputDTO) throws UnprocesableException;
    Asignatura saveAsignatura(Asignatura asignatura);
    Optional<AsignaturaOutputDTO> getById(String id);
    List<AsignaturaOutputDTO> getByStudentId(String idStudent);
    List<AsignaturaOutputDTO> getAll();
    Asignatura getAsignaturaById(String id);
    Optional<AsignaturaOutputDTO> deleteById(String id) throws NotFoundException;
    Optional<AsignaturaOutputDTO> edit(String id, AsignaturaInputDTO asignaturaInputDTO) throws NotFoundException, UnprocesableException;
}