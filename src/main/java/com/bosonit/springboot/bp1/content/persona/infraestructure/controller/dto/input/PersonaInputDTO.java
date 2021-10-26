package com.bosonit.springboot.bp1.content.persona.infraestructure.controller.dto.input;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class PersonaInputDTO {
    //int id_persona; // PK, autoincrement
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
}