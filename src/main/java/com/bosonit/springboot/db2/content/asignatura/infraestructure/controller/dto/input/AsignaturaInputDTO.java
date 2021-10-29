package com.bosonit.springboot.db2.content.asignatura.infraestructure.controller.dto.input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AsignaturaInputDTO {
    String asignatura;
    String coments;
    Date finish_date;
}
