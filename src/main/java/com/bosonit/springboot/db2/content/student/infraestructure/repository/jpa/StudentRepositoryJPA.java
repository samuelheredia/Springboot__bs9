package com.bosonit.springboot.db2.content.student.infraestructure.repository.jpa;

import com.bosonit.springboot.db2.content.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepositoryJPA extends JpaRepository<Student, String> { //<Entidad, PrimaryKey>
}
