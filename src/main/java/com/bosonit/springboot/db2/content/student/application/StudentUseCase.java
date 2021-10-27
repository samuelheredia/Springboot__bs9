package com.bosonit.springboot.db2.content.student.application;

import com.bosonit.springboot.db2.config.exception.NotFoundException;
import com.bosonit.springboot.db2.config.exception.UnprocesableException;
import com.bosonit.springboot.db2.content.persona.domain.Persona;
import com.bosonit.springboot.db2.content.persona.infraestructure.controller.dto.input.PersonaInputDTO;
import com.bosonit.springboot.db2.content.persona.infraestructure.controller.dto.output.PersonaOutputDTO;
import com.bosonit.springboot.db2.content.persona.infraestructure.repository.port.PersonaPortRep;
import com.bosonit.springboot.db2.content.student.application.port.StudentPort;
import com.bosonit.springboot.db2.content.student.domain.Student;
import com.bosonit.springboot.db2.content.student.infraestructure.controller.dto.input.StudentInputDTO;
import com.bosonit.springboot.db2.content.student.infraestructure.controller.dto.output.StudentOutputDTO;
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

    @Override
    public Optional<StudentOutputDTO> save(StudentInputDTO studentInputDTO) throws UnprocesableException {
        Student nuevoStudent = new Student(studentInputDTO);
        nuevoStudent.addPersona( personaRepository.getById(studentInputDTO.getId_persona()).orElseThrow(
                () -> new NotFoundException("Persona con ID: "+studentInputDTO.getId_persona()+" no encontrada")
        ));
        return Optional.of( new StudentOutputDTO( studentRepository.save( nuevoStudent ) ) );
    }

    @Override
    public Optional<StudentOutputDTO> getById(String id) {
        if(studentRepository.getById(id).isPresent())
            return Optional.of( new StudentOutputDTO(studentRepository.getById(id).orElse( new Student() )) );
        throw new NotFoundException("Persona con ID: "+id+" no encontrada");
    }

    @Override
    public List<StudentOutputDTO> getAll() {
        List<StudentOutputDTO> studentOutputDTO = new ArrayList<>();
        studentRepository.getAll().forEach(
                (student) -> studentOutputDTO.add( new StudentOutputDTO(student) ));
        return studentOutputDTO;
    }

    @Override
    public Optional<StudentOutputDTO> deleteById(String id) throws NotFoundException {
        return Optional.empty();
    }

    @Override
    public Optional<StudentOutputDTO> edit(int id, StudentInputDTO studentInputDTO) throws NotFoundException, UnprocesableException {
        return Optional.empty();
    }

    /*
    public boolean validateStudent(StudentInputDTO studentInputDTO) throws UnprocesableException {
        if(personaInputDTO.getUsuario() == null) throw new UnprocesableException("El usuario no puede ser null");
        if(personaInputDTO.getUsuario().length() > 10 || personaInputDTO.getUsuario().length() < 6 )
            throw new UnprocesableException("El usuario debe tener entre 6 y 10 caracteres");
        if(personaInputDTO.getPassword() == null) throw new UnprocesableException("La password no puede ser null");
        if(personaInputDTO.getName() == null) throw new UnprocesableException("El nombre no puede ser null");
        if(personaInputDTO.getCompany_email() == null) throw new UnprocesableException("El mail de compañía no puede ser null");
        if(! personaInputDTO.getCompany_email().matches(".*@.*\\..*")) throw new UnprocesableException("Mail inválido en mail de compañía");
        if(personaInputDTO.getPersonal_email() == null) throw new UnprocesableException("El mail personal no puede ser null");
        if(! personaInputDTO.getPersonal_email().matches(".*@.*\\..*")) throw new UnprocesableException("Mail inválido en mail personal");
        if(personaInputDTO.getActive() == null) throw new UnprocesableException("La actividad no puede ser null");
        if(personaInputDTO.getCreated_date() == null) throw new UnprocesableException("La fecha de creación no puede ser null");
        return true;
    }
     */
}
