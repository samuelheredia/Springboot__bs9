package com.bosonit.springboot.db2.content.profesor.infraestructure.controller.dto.output;

import com.bosonit.springboot.db2.content.profesor.domain.Profesor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfesorSimpleOutputDTO {
    String id;
    int id_persona;
    String comments;
    String branch;

    public ProfesorSimpleOutputDTO(Profesor profesor){
        this.id = profesor.getId_profesor();
        this.id_persona = profesor.getPersona().getId_persona();
        this.comments = profesor.getComments();
        this.branch = profesor.getBranch();
    }
}
