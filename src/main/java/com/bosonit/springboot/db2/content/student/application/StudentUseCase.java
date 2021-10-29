package com.bosonit.springboot.db2.content.student.application;

import com.bosonit.springboot.db2.config.exception.NotFoundException;
import com.bosonit.springboot.db2.config.exception.UnprocesableException;
import com.bosonit.springboot.db2.content.persona.domain.Persona;
import com.bosonit.springboot.db2.content.persona.infraestructure.repository.port.PersonaPortRep;
import com.bosonit.springboot.db2.content.profesor.domain.Profesor;
import com.bosonit.springboot.db2.content.profesor.infraestructure.repository.port.ProfesorPortRep;
import com.bosonit.springboot.db2.content.student.application.port.StudentPort;
import com.bosonit.springboot.db2.content.student.domain.Student;
import com.bosonit.springboot.db2.content.student.infraestructure.controller.dto.input.StudentInputDTO;
import com.bosonit.springboot.db2.content.student.infraestructure.controller.dto.output.StudentFullOutputDTO;
import com.bosonit.springboot.db2.content.student.infraestructure.controller.dto.output.StudentSimpleOutputDTO;
import com.bosonit.springboot.db2.content.student.infraestructure.repository.port.StudentPortRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentUseCase implements StudentPort {

    @Autowired
    StudentPortRep studentRepository;

    @Autowired
    PersonaPortRep personaRepository;

    @Autowired
    ProfesorPortRep profesorRepository;

    @Override
    public Optional<StudentSimpleOutputDTO> save(StudentInputDTO studentInputDTO) throws UnprocesableException {
        validateStudent(studentInputDTO);
        Persona persona = personaRepository.getById(studentInputDTO.getId_persona()).orElseThrow(
                () -> new NotFoundException("Persona con ID: "+studentInputDTO.getId_persona()+" no encontrada") );
        Profesor profesor = null;
        if(studentInputDTO.getId_profesor() != null)
            profesor = profesorRepository.getById(studentInputDTO.getId_profesor()).orElse(null);
        Student nuevoStudent = new Student(studentInputDTO, persona, profesor);
        return Optional.of(new StudentSimpleOutputDTO( studentRepository.save(nuevoStudent) ));
    }

    @Override
    public Optional<StudentSimpleOutputDTO> getById(String id, String type) {
        if(studentRepository.getById(id).isPresent()) {
            switch(type){
                case "simple":
                    return Optional.of(new StudentSimpleOutputDTO(studentRepository.getById(id).orElse(new Student())));
                case "full":
                    return Optional.of(new StudentFullOutputDTO(studentRepository.getById(id).orElse(new Student())));
                default:
                    throw new UnprocesableException("Parámetro '"+type+"' no reconocido (debe ser full/simple)");
            }
        }
        throw new NotFoundException("Persona con ID: "+id+" no encontrada");
    }

    @Override
    public List<StudentSimpleOutputDTO> getAll() {
        List<StudentSimpleOutputDTO> studentSimpleOutputDTO = new ArrayList<>();
        studentRepository.getAll().forEach(
                (student) -> studentSimpleOutputDTO.add( new StudentSimpleOutputDTO(student) ));
        return studentSimpleOutputDTO;
    }

    @Override
    public Optional<StudentSimpleOutputDTO> deleteById(String id) throws NotFoundException {
        if(studentRepository.getById(id).isPresent()) {
            StudentSimpleOutputDTO studentSimpleOutputDTO = new StudentSimpleOutputDTO(studentRepository.getById(id).get());
            studentRepository.deleteById(id);
            return Optional.of(studentSimpleOutputDTO);
        }
        else
            throw new NotFoundException("Student con ID: "+id+" no encontrado");
    }

    @Override
    public Optional<StudentSimpleOutputDTO> edit(String id, StudentInputDTO studentInputDTO) throws NotFoundException, UnprocesableException {
        Student oldStudent = studentRepository.getById(id).orElseThrow(
                () -> new NotFoundException("Student con ID: "+id+" no encontrado")
        );
        studentInputDTO.setComments( studentInputDTO.getComments() != null ? studentInputDTO.getComments() : oldStudent.getComments() );
        studentInputDTO.setBranch( studentInputDTO.getBranch() != null ? studentInputDTO.getBranch() : oldStudent.getBranch() );

        Persona persona = personaRepository.getById(studentInputDTO.getId_persona()).orElseThrow(
                () -> new NotFoundException("Persona con ID: "+studentInputDTO.getId_persona()+" no encontrada") );
        if(persona.getProfesor() != null) throw new UnprocesableException("Un profesor no puede ser estudiante");

        Profesor profesor = profesorRepository.getById(studentInputDTO.getId_profesor()).orElse(null);
        Student newStudent = new Student(studentInputDTO, persona, profesor);
        newStudent.setId_student(id);
        return Optional.of(new StudentSimpleOutputDTO( studentRepository.save(newStudent) ));
    }

    public boolean validateStudent(StudentInputDTO studentInputDTO) throws UnprocesableException {
        if(studentInputDTO.getBranch() == null) throw new UnprocesableException("El branch no puede ser null");
        return true;
    }
}
