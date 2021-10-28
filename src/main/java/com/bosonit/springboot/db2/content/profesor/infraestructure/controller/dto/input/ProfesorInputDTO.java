package com.bosonit.springboot.db2.content.profesor.infraestructure.controller.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfesorInputDTO {
    String comments;
    int id_persona;
    String branch;
}
