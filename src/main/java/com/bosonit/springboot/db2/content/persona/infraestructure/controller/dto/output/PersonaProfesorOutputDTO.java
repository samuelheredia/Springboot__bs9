package com.bosonit.springboot.db2.content.persona.infraestructure.controller.dto.output;

import com.bosonit.springboot.db2.content.persona.domain.Persona;
import com.bosonit.springboot.db2.content.student.domain.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PersonaProfesorOutputDTO extends PersonaOutputDTO {
    String id_profesor;
    String comments;
    String branch;
    List<String> students;

    public PersonaProfesorOutputDTO(Persona persona){
        super(persona);
        this.id_profesor = persona.getProfesor().getId_profesor();
        this.comments = persona.getProfesor().getComments();
        this.branch = persona.getProfesor().getBranch();
        this.students = persona.getProfesor().getStudents().stream().map(Student::getId_student).collect(Collectors.toList());
    }
}
