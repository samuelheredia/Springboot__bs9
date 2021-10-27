package com.bosonit.springboot.db2.content.student.infraestructure.repository.port;

import com.bosonit.springboot.db2.content.persona.domain.Persona;
import com.bosonit.springboot.db2.content.persona.infraestructure.repository.jpa.PersonaRepositoryJPA;
import com.bosonit.springboot.db2.content.student.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentPortRep {
    Student save(Student student);
    Optional<Student> getById(String id);

    List<Student> getAll();
    void deleteById(String id);
}
