package com.bosonit.springboot.db1.content.persona.infraestructure.controller;

import com.bosonit.springboot.db1.content.persona.application.port.PersonaPort;
import com.bosonit.springboot.db1.content.persona.infraestructure.controller.dto.input.PersonaInputDTO;
import com.bosonit.springboot.db1.content.persona.infraestructure.controller.dto.output.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PersonaController {

    @Autowired
    PersonaPort personaPort;

    // Añadir persona
    @PostMapping("/persona") // CREATE
    public PersonaOutputDTO addPersona(@RequestBody PersonaInputDTO personaInputDTO){
        return personaPort.save(personaInputDTO).orElse( new PersonaOutputDTO() );
    }

    // Buscar persona por id
    @GetMapping("/persona/{id}") // READ
    public PersonaOutputDTO getById(@PathVariable int id) throws Exception {
        return personaPort.getById(id).orElseThrow(() -> new Exception("Error 404"));
    }

    // Busca persona por nombre
    @GetMapping("/persona/nombre/{nombre}") // READ
    public List<PersonaOutputDTO> getByName(@PathVariable String nombre) {
        return personaPort.getByName(nombre);
    }

    // Busca todas las personas
    @GetMapping("/persona/all")
    public List<PersonaOutputDTO> getAll()  {
        return personaPort.getAll();
    }

    // Actualiza persona
    @PutMapping("/persona/{id}")
    public PersonaOutputDTO editById(@PathVariable int id, @RequestBody PersonaInputDTO personaInputDTO) throws Exception {
        return personaPort.edit(id, personaInputDTO).orElseThrow( () -> new Exception("Error 406") );
    }

    // Borra persona
    @DeleteMapping("/persona/{id}")
    public void deletePersona(@PathVariable int id) throws Exception {
        personaPort.deleteById(id).orElseThrow( () -> new Exception("Error 406") );
    }
/*
    public void validatePersona(Persona persona) throws Exception {
        if(persona.usuario == null) throw new NullPointerException("El usuario no puede ser null");
        if(persona.usuario.length() > 10 || persona.usuario.length() < 6 )
            throw new Exception("El usuario debe tener entre 6 y 10 caracteres");
        if(persona.password == null) throw new NullPointerException("La password no puede ser null");
        if(persona.name == null) throw new NullPointerException("El nombre no puede ser null");
        if(persona.company_email == null) throw new NullPointerException("El mail de compañía no puede ser null");
        if(! persona.company_email.matches(".*@.*\\..*")) throw new Exception("Mail inválido en mail de compañía");
        if(persona.personal_email == null) throw new NullPointerException("El mail personal no puede ser null");
        if(! persona.personal_email.matches(".*@.*\\..*")) throw new Exception("Mail inválido en mail personal");
        if(persona.active == null) throw new NullPointerException("La actividad no puede ser null");
        if(persona.created_date == null) throw new NullPointerException("La fecha de creación no puede ser null");
    }
 */
}