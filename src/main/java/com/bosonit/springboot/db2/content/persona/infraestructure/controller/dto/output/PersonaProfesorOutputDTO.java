package com.bosonit.springboot.db2.content.persona.infraestructure.controller.dto.output;

import com.bosonit.springboot.db2.content.persona.domain.Persona;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaProfesorOutputDTO extends PersonaOutputDTO {
    String id_profesor;
    String comments;
    String branch;

    public PersonaProfesorOutputDTO(Persona persona){
        super(persona);
        this.id_profesor = persona.getProfesor().getId_profesor();
        this.comments = persona.getProfesor().getComments();
        this.branch = persona.getProfesor().getBranch();
    }
}
