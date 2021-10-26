package com.bosonit.springboot.bp1.content.persona.infraestructure.repository;

import com.bosonit.springboot.bp1.content.persona.domain.Persona;
import com.bosonit.springboot.bp1.content.persona.infraestructure.repository.jpa.PersonaRepositoryJPA;
import com.bosonit.springboot.bp1.content.persona.infraestructure.repository.port.PersonaPortRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaRepository implements PersonaPortRep {

    @Autowired
    PersonaRepositoryJPA personaRepositoryJPA;

    @Override
    public Optional<Persona> save(Persona persona) {
        return Optional.of( personaRepositoryJPA.save(persona) );
    }

    @Override
    public Optional<Persona> getById(int id) {
        return personaRepositoryJPA.findById(id);
    }

    @Override
    public List<Persona> getByName(String name) {
        return personaRepositoryJPA.findByName(name);
    }

    @Override
    public List<Persona> getAll() {
        return personaRepositoryJPA.findAll();
    }

    @Override
    public void deleteById(int id) {
        personaRepositoryJPA.deleteById(id);
    }
}
