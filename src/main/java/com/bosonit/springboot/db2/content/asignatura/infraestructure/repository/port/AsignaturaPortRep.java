package com.bosonit.springboot.db2.content.asignatura.infraestructure.repository.port;

import com.bosonit.springboot.db2.content.asignatura.domain.Asignatura;
import com.bosonit.springboot.db2.content.student.domain.Student;

import java.util.List;
import java.util.Optional;

public interface AsignaturaPortRep {
    Asignatura save(Asignatura asignatura);
    Optional<Asignatura> getById(String id);
    List<Asignatura> getByStudentId(String idStudent);
    List<Asignatura> getAll();
    void deleteById(String id);
}
