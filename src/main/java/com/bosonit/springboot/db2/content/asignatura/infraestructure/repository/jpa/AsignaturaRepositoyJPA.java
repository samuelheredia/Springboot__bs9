package com.bosonit.springboot.db2.content.asignatura.infraestructure.repository.jpa;

import com.bosonit.springboot.db2.content.asignatura.domain.Asignatura;
import com.bosonit.springboot.db2.content.persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AsignaturaRepositoyJPA extends JpaRepository<Asignatura, String> {

}
