package com.bosonit.springboot.db2.content.student.domain;

import com.bosonit.springboot.db2.content.Branch;
import com.bosonit.springboot.db2.content.StringPrefixedSequenceIdGenerator;
import com.bosonit.springboot.db2.content.persona.domain.Persona;
import com.bosonit.springboot.db2.content.student.infraestructure.controller.dto.input.StudentInputDTO;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ausencias_seq")
    @GenericGenerator(
            name = "ausencias_seq",
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
    //@Column(name="id_profesor")
    //String id_profesor; //ref
    @Column(name="branch", nullable = false)
    String branch;

    public Student(Persona persona, int num_hours_week, String comments, String branch){
        this.persona = persona;
        this.num_hours_week = num_hours_week;
        this.comments = comments;
        this.branch = branch;
    }

    public Student(StudentInputDTO studentInputDTO){
        //this.persona = persona;
        this.num_hours_week = studentInputDTO.getHoursWeek();
        this.comments = studentInputDTO.getComments();
        this.branch = studentInputDTO.getBranch();
    }

    public void addPersona(Persona persona){
        this.persona = persona;
    }
}
