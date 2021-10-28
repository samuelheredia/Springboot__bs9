package com.bosonit.springboot.db2.content.profesor.infraestructure.repository.port;

import com.bosonit.springboot.db2.content.profesor.domain.Profesor;

import java.util.List;
import java.util.Optional;

public interface ProfesorPortRep {
    Profesor save(Profesor profesor);
    Optional<Profesor> getById(String id);

    List<Profesor> getAll();
    void deleteById(String id);
}
