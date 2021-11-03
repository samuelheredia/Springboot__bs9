package com.bosonit.springboot.db2.content.core;

import com.bosonit.springboot.db2.content.asignatura.application.port.AsignaturaPort;
import com.bosonit.springboot.db2.content.asignatura.domain.Asignatura;
import com.bosonit.springboot.db2.content.persona.application.port.PersonaPort;
import com.bosonit.springboot.db2.content.persona.domain.Persona;
import com.bosonit.springboot.db2.content.profesor.application.port.ProfesorPort;
import com.bosonit.springboot.db2.content.profesor.domain.Profesor;
import com.bosonit.springboot.db2.content.profesor.infraestructure.controller.dto.input.ProfesorInputDTO;
import com.bosonit.springboot.db2.content.student.application.port.StudentPort;
import com.bosonit.springboot.db2.content.student.domain.Student;
import com.bosonit.springboot.db2.content.student.infraestructure.controller.dto.input.StudentInputDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Mapper {
    @Autowired
    StudentPort studentUseCase;
    @Autowired
    PersonaPort personaUseCase;
    @Autowired
    ProfesorPort profesorUseCase;
    @Autowired
    AsignaturaPort asignaturaUseCase;

    public Profesor createProfesor(ProfesorInputDTO profesorInputDTO){
        Profesor profesor = new Profesor();
        Persona persona = personaUseCase.getPersonaById(profesorInputDTO.getId_persona());
        profesor.setBranch(profesorInputDTO.getBranch());
        profesor.setComments(profesorInputDTO.getComments());
        profesor.setPersona( persona );
        persona.setProfesor(profesor);
        return profesor;
    }

    public Student createStudent(StudentInputDTO studentInputDTO){
        Student student = new Student();
        Persona persona = personaUseCase.getPersonaById(studentInputDTO.getId_persona());
        student.setComments(studentInputDTO.getComments());
        student.setBranch(studentInputDTO.getBranch());
        student.setNum_hours_week(studentInputDTO.getHoursWeek());
        student.setProfesor( profesorUseCase.getProfesorById( studentInputDTO.getId_profesor()));
        student.setPersona( persona );

        return student;
    }

    public Asignatura addAsignaturaToStudent(Student student, String idAsignatura){
        Asignatura asignatura = asignaturaUseCase.getAsignaturaById(idAsignatura);
        asignatura.addStudent(student);
        student.addAsignatura(asignatura);
        asignaturaUseCase.saveAsignatura(asignatura);
        return asignatura;
    }

    public Asignatura removeAsignaturaToStudent(Student student, String idAsignatura){
        Asignatura asignatura = asignaturaUseCase.getAsignaturaById(idAsignatura);
        asignatura.removeStudent(student);
        student.removeAsignatura(asignatura);
        asignaturaUseCase.saveAsignatura(asignatura);
        return asignatura;
    }
}