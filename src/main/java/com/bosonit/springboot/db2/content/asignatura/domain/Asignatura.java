package com.bosonit.springboot.db2.content.asignatura.domain;

import com.bosonit.springboot.db2.content.StringPrefixedSequenceIdGenerator;
import com.bosonit.springboot.db2.content.asignatura.infraestructure.controller.dto.input.AsignaturaInputDTO;
import com.bosonit.springboot.db2.content.student.domain.Student;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asignatura_seq")
    @GenericGenerator(
            name = "asignatura_seq",
            strategy = "com.bosonit.springboot.db2.content.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value =
                            "AS"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value =
                            "%03d")
            })
    @Column(name = "id_asignatura")
    String id_asignatura;
    String asignatura;
    String coments;
    @Column(nullable = false)
    Date initial_date; // Not null
    Date finish_date;

    @ManyToMany(mappedBy = "asignaturas",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE })
    Set<Student> students = new HashSet<>();

    public Asignatura(AsignaturaInputDTO asignaturaInputDTO){
        this.coments = asignaturaInputDTO.getComents();
        this.asignatura = asignaturaInputDTO.getAsignatura();
        this.finish_date = asignaturaInputDTO.getFinish_date();
        this.initial_date = new Date();
    }

    public Asignatura(String comments, String asignatura, Date finish_date){
        this.coments = comments;
        this.asignatura = asignatura;
        this.finish_date = finish_date;
        this.initial_date = new Date();
    }

    public boolean addStudent(Student student){
        return students.add(student);
    }

    public boolean removeStudent(Student student){
        return students.remove(student);
    }
}