package com.bosonit.springboot.db2.content.profesor.domain;

import com.bosonit.springboot.db2.content.StringPrefixedSequenceIdGenerator;
import com.bosonit.springboot.db2.content.persona.domain.Persona;
import com.bosonit.springboot.db2.content.profesor.infraestructure.controller.dto.input.ProfesorInputDTO;
import com.bosonit.springboot.db2.content.student.domain.Student;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profesor_seq")
    @GenericGenerator(
            name = "profesor_seq",
            strategy = "com.bosonit.springboot.db2.content.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value =
                            "PR"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value =
                            "%03d")
            })
    @Column(name = "id_profesor")
    String id_profesor; // PK, autoincrement
    @OneToOne
    @JoinColumn(name="id_persona", nullable = false)
    Persona persona;
    @Column(name="comments")
    String comments;
    @Column(name="branch", nullable = false)
    String branch;
    @OneToMany(mappedBy = "profesor")
    Set<Student> students = new HashSet<>();

    public Profesor(String comments, String branch, Persona persona ){
        this.comments = comments;
        this.branch = branch;
        this.persona = persona;
    }

/*
    public boolean addStudent(Student student){
        return students.add(student);
    }
    */

}
