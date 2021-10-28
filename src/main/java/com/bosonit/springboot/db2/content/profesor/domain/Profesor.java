package com.bosonit.springboot.db2.content.profesor.domain;

import com.bosonit.springboot.db2.content.StringPrefixedSequenceIdGenerator;
import com.bosonit.springboot.db2.content.persona.domain.Persona;
import com.bosonit.springboot.db2.content.profesor.infraestructure.controller.dto.input.ProfesorInputDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Data
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

    public Profesor(String comments, String branch, Persona persona ){
        this.comments = comments;
        this.branch = branch;
        this.persona = persona;
    }

    public Profesor(String id, ProfesorInputDTO profesorInputDTO, Persona persona){
        this.id_profesor = id;
        this.comments = profesorInputDTO.getComments() != null ? profesorInputDTO.getComments() : this.comments;
        this.branch = profesorInputDTO.getBranch();
        this.persona = persona;
    }

    public Profesor(ProfesorInputDTO profesorInputDTO, Persona persona){
        this.comments = profesorInputDTO.getComments() != null ? profesorInputDTO.getComments() : this.comments;
        this.branch = profesorInputDTO.getBranch();
        this.persona = persona;
    }
}
