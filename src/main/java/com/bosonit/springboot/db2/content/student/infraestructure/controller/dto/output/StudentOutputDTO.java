package com.bosonit.springboot.db2.content.student.infraestructure.controller.dto.output;

import com.bosonit.springboot.db2.content.student.domain.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentOutputDTO {
    String id;
    int id_persona;
    int hoursWeek;
    String comments;
    String branch;

    public StudentOutputDTO(Student student){
        this.id = student.getId_student();
        this.id_persona = student.getPersona().getId_persona();
        this.hoursWeek = student.getNum_hours_week();
        this.comments = student.getComments();
        this.branch = student.getBranch();
    }
}
