package com.bosonit.springboot.db2.content.persona.infraestructure.controller.dto.output;

import com.bosonit.springboot.db2.content.persona.domain.Persona;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaStudentOutputDTO extends PersonaOutputDTO {
    String id_student;
    int hoursWeek;
    String comments;
    String branch;

    public PersonaStudentOutputDTO(Persona persona){
        super(persona);
        this.id_student = persona.getStudent().getId_student();
        this.hoursWeek = persona.getStudent().getNum_hours_week();
        this.comments = persona.getStudent().getComments();
        this.branch = persona.getStudent().getBranch();
    }
}
