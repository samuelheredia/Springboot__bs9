package com.bosonit.springboot.db2.content.profesor.infraestructure.controller.dto.output;

import com.bosonit.springboot.db2.content.profesor.domain.Profesor;
import com.bosonit.springboot.db2.content.student.domain.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProfesorFullOutputDTO extends ProfesorSimpleOutputDTO {
    int id_persona; // PK, autoincrement
    String usuario; // not null, max/min 10/6
    String password; // not null
    String name; // not null
    String surname;
    String company_email; //not null, check-email true
    String personal_email; //not null, check-email true
    String city; //not null
    Boolean active; //not null
    Date created_date; //not null
    String imagen_url;
    Date termination_date;
    List<String> students;

    public ProfesorFullOutputDTO(Profesor profesor){
        super(profesor);

        this.id_persona = profesor.getPersona().getId_persona();
        this.usuario = profesor.getPersona().getUsuario();
        this.password = profesor.getPersona().getPassword();
        this.name = profesor.getPersona().getName();
        this.surname = profesor.getPersona().getSurname();
        this.company_email = profesor.getPersona().getCompany_email();
        this.personal_email = profesor.getPersona().getPersonal_email();
        this.city = profesor.getPersona().getCity();
        this.active = profesor.getPersona().getActive();
        this.created_date = profesor.getPersona().getCreated_date();
        this.imagen_url = profesor.getPersona().getImagen_url();
        this.termination_date = profesor.getPersona().getTermination_date();
        this.students = profesor.getStudents().stream().map(Student::getId_student).collect(Collectors.toList());

    }
}
