package com.bosonit.springboot.bp1.content.persona.infraestructure.controller;

import com.bosonit.springboot.bp1.content.persona.application.port.PersonaPort;
import com.bosonit.springboot.bp1.content.persona.infraestructure.controller.dto.input.PersonaInputDTO;
import com.bosonit.springboot.bp1.content.persona.infraestructure.controller.dto.output.PersonaOutputDTO;
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
    public PersonaOutputDTO getById(@PathVariable int id) throws Exception {
        return personaPort.getById(id).orElseThrow(() -> new Exception("Error 404"));
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
    public PersonaOutputDTO editById(@PathVariable int id, @RequestBody PersonaInputDTO personaInputDTO) throws Exception {
        return personaPort.edit(id, personaInputDTO).orElseThrow( () -> new Exception("Error 406") );
    }

    // Borra persona
    @DeleteMapping("{id}")
    public void deletePersona(@PathVariable int id) throws Exception {
        personaPort.deleteById(id).orElseThrow( () -> new Exception("Error 406") );
    }
}