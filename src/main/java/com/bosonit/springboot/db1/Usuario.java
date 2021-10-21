package com.bosonit.springboot.db1;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Usuario {
    @Id
    String id;

    String nombreUsuario;
    int edad;
    String ciudad;
    Date fecha;
}
