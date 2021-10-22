package com.bosonit.springboot.db1;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data
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
    // Rutina comprobacion email
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
}
