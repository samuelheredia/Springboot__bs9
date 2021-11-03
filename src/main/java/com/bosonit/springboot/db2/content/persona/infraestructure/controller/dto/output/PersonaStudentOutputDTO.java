package com.bosonit.springboot.db2.content.persona.infraestructure.controller.dto.output;

import com.bosonit.springboot.db2.content.asignatura.domain.Asignatura;
import com.bosonit.springboot.db2.content.persona.domain.Persona;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PersonaStudentOutputDTO extends PersonaOutputDTO {
    String id_student;
    String profesorAsignado;
    int hoursWeek;
    String comments;
    String branch;
    List<String> asignaturas;

    public PersonaStudentOutputDTO(Persona persona){
        super(persona);
        this.id_student = persona.getStudent().getId_student();
        this.hoursWeek = persona.getStudent().getNum_hours_week();
        this.comments = persona.getStudent().getComments();
        this.branch = persona.getStudent().getBranch();
        this.profesorAsignado = persona.getStudent().getProfesor() != null ? persona.getStudent().getProfesor().getId_profesor() : "No asignado";
        this.asignaturas = persona.getStudent().getAsignaturas().stream().map(Asignatura::getAsignatura).collect(Collectors.toList());
    }
}
