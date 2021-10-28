package com.bosonit.springboot.db2.content.profesor.infraestructure.repository.jpa;

import com.bosonit.springboot.db2.content.profesor.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepositoryJPA extends JpaRepository<Profesor, String> {
}
