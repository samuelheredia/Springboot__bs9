package com.bosonit.springboot.db2.content.asignatura.infraestructure.repository.jpa;

import com.bosonit.springboot.db2.content.asignatura.domain.Asignatura;
import com.bosonit.springboot.db2.content.persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AsignaturaRepositoyJPA extends JpaRepository<Asignatura, String> {
    @Query("SELECT a FROM Asignatura a "
            + "INNER JOIN a.students s "
            + "WHERE s.id_student = :idEstudiante"
    )
    List<Asignatura> buscaPorIdEstudiante(@Param("idEstudiante") String idEstudiante);
}
