package com.bosonit.springboot.db2.content.asignatura.infraestructure.controller.dto.output;

import com.bosonit.springboot.db2.content.asignatura.domain.Asignatura;
import com.bosonit.springboot.db2.content.student.domain.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AsignaturaEstudianteOutputDTO extends AsignaturaOutputDTO{
    List<String> studentsString;

    public AsignaturaEstudianteOutputDTO(Asignatura asignatura){
        super(asignatura);
        this.studentsString = asignatura.getStudents().stream().map(Student::getId_student).collect(Collectors.toList());
    }
}
