package com.bosonit.springboot.db2.content.student.domain;

import com.bosonit.springboot.db2.content.StringPrefixedSequenceIdGenerator;
import com.bosonit.springboot.db2.content.asignatura.domain.Asignatura;
import com.bosonit.springboot.db2.content.persona.domain.Persona;
import com.bosonit.springboot.db2.content.profesor.domain.Profesor;
import com.bosonit.springboot.db2.content.student.infraestructure.controller.dto.input.StudentInputDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
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
    @Column(name="num_hours_week", nullable = false)
    int num_hours_week;
    @Column(name="comments")
    String comments;
    @Column(name="branch", nullable = false)
    String branch;

    @OneToOne
    @JoinColumn(name="id_persona", nullable = false)
    Persona persona;

    @ManyToMany
    @JoinTable(name="estudiante_asignatura",
    joinColumns = { @JoinColumn(name = "id_student") },
    inverseJoinColumns = { @JoinColumn(name = "id_asignatura") })
    private Set<Asignatura> asignaturas = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="id_profesor")
    Profesor profesor;

    public Student(int num_hours_week, String comments, String branch, Persona persona, Profesor profesor ){
        this.num_hours_week = num_hours_week;
        this.comments = comments;
        this.branch = branch;
        this.persona = persona;
        this.profesor = profesor;
    }

    public Student(String id, StudentInputDTO studentInputDTO, Persona persona, Profesor profesor){
        this.id_student = id;
        this.num_hours_week = studentInputDTO.getHoursWeek();
        this.comments = studentInputDTO.getComments() != null ? studentInputDTO.getComments() : this.comments;
        this.branch = studentInputDTO.getBranch();
        this.persona = persona;
        this.profesor = profesor;
    }

    public Student(StudentInputDTO studentInputDTO, Persona persona, Profesor profesor){
        this.num_hours_week = studentInputDTO.getHoursWeek();
        this.comments = studentInputDTO.getComments() != null ? studentInputDTO.getComments() : this.comments;
        this.branch = studentInputDTO.getBranch();
        this.persona = persona;
        this.profesor = profesor;
    }

    public void addAsignatura(Asignatura asignatura){
        asignaturas.add(asignatura);
    }
    public void removeAsignatura(Asignatura asignatura){ asignaturas.remove(asignatura); }
}
