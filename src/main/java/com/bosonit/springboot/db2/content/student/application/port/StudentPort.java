package com.bosonit.springboot.db2.content.student.application.port;

import com.bosonit.springboot.db2.config.exception.NotFoundException;
import com.bosonit.springboot.db2.config.exception.UnprocesableException;
import com.bosonit.springboot.db2.content.student.domain.Student;
import com.bosonit.springboot.db2.content.student.infraestructure.controller.dto.input.StudentInputDTO;
import com.bosonit.springboot.db2.content.student.infraestructure.controller.dto.output.StudentSimpleOutputDTO;

import java.util.List;
import java.util.Optional;

public interface StudentPort {
    Optional<StudentSimpleOutputDTO> save(StudentInputDTO studentInputDTO) throws UnprocesableException;
    Optional<StudentSimpleOutputDTO> getById(String id, String type);
    List<StudentSimpleOutputDTO> getAll();
    Optional<StudentSimpleOutputDTO> deleteById(String id) throws NotFoundException;

    Optional<StudentSimpleOutputDTO> edit(String id, StudentInputDTO studentInputDTO) throws NotFoundException, UnprocesableException;
    Optional<StudentSimpleOutputDTO> addAsignaturas(String id, List<String> listaAsignaturas);
    Optional<StudentSimpleOutputDTO> removeAsignaturas(String id, List<String> listaAsignaturas);
}
