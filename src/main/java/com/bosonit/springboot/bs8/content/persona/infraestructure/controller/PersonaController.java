package com.bosonit.springboot.bs8.content.persona.infraestructure.controller;

import com.bosonit.springboot.bs8.content.persona.application.port.PersonaPort;
import com.bosonit.springboot.bs8.content.persona.infraestructure.controller.dto.input.PersonaInputDTO;
import com.bosonit.springboot.bs8.content.persona.infraestructure.controller.dto.output.PersonaOutputDTO;
import com.bosonit.springboot.bs8.exception.NotFoundException;
import com.bosonit.springboot.bs8.exception.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("persona")
public class PersonaController {

    @Autowired
    PersonaPort personaPort;

    // Añadir persona
    @PostMapping // CREATE
    public PersonaOutputDTO addPersona(@RequestBody PersonaInputDTO personaInputDTO){
        return personaPort.save(personaInputDTO).orElse( new PersonaOutputDTO() );
    }

    // Buscar persona por id
    @GetMapping("{id}") // READ
    public PersonaOutputDTO getById(@PathVariable int id) {
        //return personaPort.getById(id).orElseThrow(() -> new BeanNotFoundException("Error 404"));
        return personaPort.getById(id).orElseThrow(() -> new NotFoundException("No encontrada persona con id: "+id));
    }

    // Busca persona por nombre
    @GetMapping("nombre/{nombre}") // READ
    public List<PersonaOutputDTO> getByName(@PathVariable String nombre) {
        return personaPort.getByName(nombre);
    }

    // Busca todas las personas
    @GetMapping("all")
    public List<PersonaOutputDTO> getAll()  {
        return personaPort.getAll();
    }

    // Actualiza persona
    @PutMapping("{id}")
    public PersonaOutputDTO editById(@PathVariable int id, @RequestBody PersonaInputDTO personaInputDTO) {
        //return personaPort.edit(id, personaInputDTO).orElseThrow( () -> new UnprocesableException("Error 406") );
        return personaPort.edit(id, personaInputDTO).orElse( new PersonaOutputDTO() );
    }

    // Borra persona
    @DeleteMapping("{id}")
    public String deletePersona(@PathVariable int id) {
        return "Borrada persona con ID: "+personaPort.deleteById(id).orElse( new PersonaOutputDTO() ).getId_persona();
    }
}