package com.bosonit.springboot.db2.content.asignatura.infraestructure.repository;

import com.bosonit.springboot.db2.content.asignatura.domain.Asignatura;
import com.bosonit.springboot.db2.content.asignatura.infraestructure.repository.jpa.AsignaturaRepositoyJPA;
import com.bosonit.springboot.db2.content.asignatura.infraestructure.repository.port.AsignaturaPortRep;
import com.bosonit.springboot.db2.content.student.infraestructure.repository.port.StudentPortRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaRepository implements AsignaturaPortRep {

    @Autowired
    AsignaturaRepositoyJPA asignaturaRepositoyJPA;

    @Override
    public Asignatura save(Asignatura asignatura) {
        return asignaturaRepositoyJPA.save(asignatura);
    }

    @Override
    public Optional<Asignatura> getById(String id) {
        return asignaturaRepositoyJPA.findById(id);
    }

    @Override
    public List<Asignatura> getByStudentId(String idStudent) {
        return asignaturaRepositoyJPA.buscaPorIdEstudiante(idStudent);
        //return new ArrayList<>();
    }

    @Override
    public List<Asignatura> getAll() {
        return asignaturaRepositoyJPA.findAll();
    }

    @Override
    public void deleteById(String id) {
        asignaturaRepositoyJPA.deleteById(id);
    }
}
