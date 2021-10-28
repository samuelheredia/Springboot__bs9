package com.bosonit.springboot.db2.content.student.domain;

import com.bosonit.springboot.db2.content.StringPrefixedSequenceIdGenerator;
import com.bosonit.springboot.db2.content.persona.domain.Persona;
import com.bosonit.springboot.db2.content.student.infraestructure.controller.dto.input.StudentInputDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @GenericGenerator(
            name = "student_seq",
            strategy = "com.bosonit.springboot.db2.content.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value =
                            "ST"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value =
                            "%03d")
            })
    @Column(name = "id_student")
    String id_student; // PK, autoincrement
    @OneToOne
    @JoinColumn(name="id_persona", nullable = false)
    Persona persona;
    @Column(name="num_hours_week", nullable = false)
    int num_hours_week;
    @Column(name="comments")
    String comments;
    @Column(name="branch", nullable = false)
    String branch;

    public Student(int num_hours_week, String comments, String branch, Persona persona ){
        this.num_hours_week = num_hours_week;
        this.comments = comments;
        this.branch = branch;
        this.persona = persona;
    }

    public Student(String id, StudentInputDTO studentInputDTO, Persona persona){
        this.id_student = id;
        this.num_hours_week = studentInputDTO.getHoursWeek();
        this.comments = studentInputDTO.getComments() != null ? studentInputDTO.getComments() : this.comments;
        this.branch = studentInputDTO.getBranch();
        this.persona = persona;
    }

    public Student(StudentInputDTO studentInputDTO, Persona persona){
        this.num_hours_week = studentInputDTO.getHoursWeek();
        this.comments = studentInputDTO.getComments() != null ? studentInputDTO.getComments() : this.comments;
        this.branch = studentInputDTO.getBranch();
        this.persona = persona;
    }
}
