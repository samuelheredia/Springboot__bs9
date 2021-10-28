package com.bosonit.springboot.db2.content.student.infraestructure.controller.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentInputDTO {
    int hoursWeek;
    String comments;
    int id_persona;
    String branch;
}