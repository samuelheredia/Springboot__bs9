package com.bosonit.springboot.db2.content.student.infraestructure.repository;

import com.bosonit.springboot.db2.content.persona.infraestructure.repository.jpa.PersonaRepositoryJPA;
import com.bosonit.springboot.db2.content.student.domain.Student;
import com.bosonit.springboot.db2.content.student.infraestructure.repository.jpa.StudentRepositoryJPA;
import com.bosonit.springboot.db2.content.student.infraestructure.repository.port.StudentPortRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentRepository implements StudentPortRep {

    @Autowired
    StudentRepositoryJPA studentRepositoryJPA;

    @Override
    public Student save(Student student) {

        return studentRepositoryJPA.save(student);
    }

    @Override
    public Optional<Student> getById(String id) {
        return studentRepositoryJPA.findById(id);
    }

    @Override
    public List<Student> getAll() {
        return studentRepositoryJPA.findAll();
    }

    @Override
    public void deleteById(String id) {
        studentRepositoryJPA.deleteById(id);
    }
}
