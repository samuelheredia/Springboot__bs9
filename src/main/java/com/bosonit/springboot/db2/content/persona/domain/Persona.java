package com.bosonit.springboot.db2.content.persona.domain;

import com.bosonit.springboot.db2.content.persona.infraestructure.controller.dto.input.PersonaInputDTO;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    @Id
    @GeneratedValue
    int id_persona; // PK, autoincrement

    @Column(name="usuario", nullable = false, length=10)
    String usuario; // not null, max/min 10/6
    @Column(name="password", nullable = false)
    String password; // not null
    @Column(name="name", nullable = false)
    String name; // not null
    String surname;
    @NotNull
    @Column(name="emailc", nullable = false, length=25)
    String company_email; //not null, check-email true
    @Column(name="emailp", nullable = false, length=25)
    String personal_email; //not null, check-email true
    @Column(nullable = false)
    String city; //not null
    @Column(nullable = false)
    Boolean active; //not null
    @Column(nullable = false)
    Date created_date; //not null
    String imagen_url;
    Date termination_date;

    public Persona(int id, PersonaInputDTO personaInputDTO){
        this.setId_persona(id);
        this.setUsuario(personaInputDTO.getUsuario());
        this.setPassword(personaInputDTO.getPassword());
        this.setName(personaInputDTO.getName());
        this.setSurname(personaInputDTO.getSurname());
        this.setCompany_email(personaInputDTO.getCompany_email());
        this.setPersonal_email(personaInputDTO.getPersonal_email());
        this.setCity(personaInputDTO.getCity());
        this.setActive(personaInputDTO.getActive());
        this.setCreated_date(personaInputDTO.getCreated_date());
        this.setImagen_url(personaInputDTO.getImagen_url());
        this.setTermination_date(personaInputDTO.getTermination_date());
    }

    public Persona(PersonaInputDTO personaInputDTO){
        this.setUsuario(personaInputDTO.getUsuario());
        this.setPassword(personaInputDTO.getPassword());
        this.setName(personaInputDTO.getName());
        this.setSurname(personaInputDTO.getSurname());
        this.setCompany_email(personaInputDTO.getCompany_email());
        this.setPersonal_email(personaInputDTO.getPersonal_email());
        this.setCity(personaInputDTO.getCity());
        this.setActive(personaInputDTO.getActive());
        this.setCreated_date(personaInputDTO.getCreated_date());
        this.setImagen_url(personaInputDTO.getImagen_url());
        this.setTermination_date(personaInputDTO.getTermination_date());
    }
}
