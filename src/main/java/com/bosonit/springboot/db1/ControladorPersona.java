package com.bosonit.springboot.db1;

import com.bosonit.springboot.db1.content.persona.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControladorPersona {

    @Autowired
    PersonaRepositorio personaRepositorio;

    // Añadir persona
    @PostMapping("/persona") // CREATE
    public Persona addPersona(@RequestBody Persona persona){
        try {
            validatePersona(persona);
            personaRepositorio.save(persona);
            return persona;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Persona();
    }

    // Buscar persona por id
    @GetMapping("/persona/{id}") // READ
    public Persona getById(@PathVariable int id) throws Exception {
        return personaRepositorio.findById(id).orElseThrow(() -> new Exception("No encontrado"));
    }

    // Busca persona por nombre
    @GetMapping("/persona/nombre/{nombre}") // READ
    public List<Persona> getByName(@PathVariable String nombre) {
        return personaRepositorio.buscaPorNombre(nombre);
        //return personaRepositorio.findByName(nombre);
    }

    // Busca todas las personas
    @GetMapping("/persona/all")
    public List<Persona> getAll()  {
        return personaRepositorio.findAll();
    }

    // Actualiza persona
    @PutMapping("/persona")
    public Persona editById(@RequestBody Persona persona){
        try {
            validatePersona(persona);
            personaRepositorio.save(persona);
            return persona;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Persona();
    }

    // Borra persona
    @DeleteMapping("/persona/{id}")
    public Persona deletePersona(@PathVariable int id){
        Persona ret = personaRepositorio.findById(id).orElse(new Persona());
        personaRepositorio.deleteById(id);
        return ret;
    }

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
}