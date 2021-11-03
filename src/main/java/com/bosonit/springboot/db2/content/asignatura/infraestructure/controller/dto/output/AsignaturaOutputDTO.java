package com.bosonit.springboot.db2.content.asignatura.infraestructure.controller.dto.output;

import com.bosonit.springboot.db2.content.asignatura.domain.Asignatura;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AsignaturaOutputDTO {
    String id_asignatura;
    String asignatura;
    String coments;
    Date initial_date;
    Date finish_date;

    public AsignaturaOutputDTO(Asignatura asignatura){
        this.id_asignatura = asignatura.getId_asignatura();
        this.asignatura = asignatura.getAsignatura();
        this.coments = asignatura.getComents();
        this.initial_date = asignatura.getInitial_date();
        this.finish_date = asignatura.getFinish_date();
    }
}