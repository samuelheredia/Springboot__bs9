package com.bosonit.springboot.db2.content.student.infraestructure.controller.dto.output;

import com.bosonit.springboot.db2.content.student.domain.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StudentFullOutputDTO extends StudentSimpleOutputDTO {
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

    public StudentFullOutputDTO(Student student){
        super(student);

        this.id_persona = student.getPersona().getId_persona();
        this.usuario = student.getPersona().getUsuario();
        this.password = student.getPersona().getPassword();
        this.name = student.getPersona().getName();
        this.surname = student.getPersona().getSurname();
        this.company_email = student.getPersona().getCompany_email();
        this.personal_email = student.getPersona().getPersonal_email();
        this.city = student.getPersona().getCity();
        this.active = student.getPersona().getActive();
        this.created_date = student.getPersona().getCreated_date();
        this.imagen_url = student.getPersona().getImagen_url();
        this.termination_date = student.getPersona().getTermination_date();
    }
}
