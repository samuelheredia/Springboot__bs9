package com.bosonit.springboot.db2.content.profesor.infraestructure.repository;

import com.bosonit.springboot.db2.content.profesor.domain.Profesor;
import com.bosonit.springboot.db2.content.profesor.infraestructure.repository.jpa.ProfesorRepositoryJPA;
import com.bosonit.springboot.db2.content.profesor.infraestructure.repository.port.ProfesorPortRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorRepository implements ProfesorPortRep {

    @Autowired
    ProfesorRepositoryJPA profesorRepositoryJPA;

    @Override
    public Profesor save(Profesor profesor) {
        return profesorRepositoryJPA.save(profesor);
    }

    @Override
    public Optional<Profesor> getById(String id) {
        return profesorRepositoryJPA.findById(id);
    }

    @Override
    public List<Profesor> getAll() {
        return profesorRepositoryJPA.findAll();
    }

    @Override
    public void deleteById(String id) {
        profesorRepositoryJPA.deleteById(id);
    }
}
