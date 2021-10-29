package com.bosonit.springboot.db2.content.profesor.application;

import com.bosonit.springboot.db2.config.exception.NotFoundException;
import com.bosonit.springboot.db2.config.exception.UnprocesableException;
import com.bosonit.springboot.db2.content.persona.domain.Persona;
import com.bosonit.springboot.db2.content.persona.infraestructure.repository.port.PersonaPortRep;
import com.bosonit.springboot.db2.content.profesor.application.port.ProfesorPort;
import com.bosonit.springboot.db2.content.profesor.domain.Profesor;
import com.bosonit.springboot.db2.content.profesor.infraestructure.controller.dto.input.ProfesorInputDTO;
import com.bosonit.springboot.db2.content.profesor.infraestructure.controller.dto.output.ProfesorFullOutputDTO;
import com.bosonit.springboot.db2.content.profesor.infraestructure.controller.dto.output.ProfesorSimpleOutputDTO;
import com.bosonit.springboot.db2.content.profesor.infraestructure.repository.port.ProfesorPortRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesorUseCase implements ProfesorPort {

    @Autowired
    ProfesorPortRep profesorRepository;

    @Autowired
    PersonaPortRep personaRepository;

    @Override
    public Optional<ProfesorSimpleOutputDTO> save(ProfesorInputDTO profesorInputDTO) throws UnprocesableException {
        validateProfesor(profesorInputDTO);
        Persona persona = personaRepository.getById(profesorInputDTO.getId_persona()).orElseThrow(
                () -> new NotFoundException("Persona con ID: "+profesorInputDTO.getId_persona()+" no encontrada") );
        if(persona.getStudent() != null) {
            persona.setStudent(null);
            personaRepository.save(persona);
        }
        Profesor nuevoProfesor = new Profesor(profesorInputDTO, persona);
        return Optional.of(new ProfesorSimpleOutputDTO( profesorRepository.save(nuevoProfesor) ));
    }

    @Override
    public Optional<ProfesorSimpleOutputDTO> getById(String id, String type) {
        if(profesorRepository.getById(id).isPresent()) {
            switch(type){
                case "simple":
                    System.out.println("DTO simple "+type);
                    return Optional.of(new ProfesorSimpleOutputDTO(profesorRepository.getById(id).orElse(new Profesor())));
                case "full":
                    System.out.println("DTO full "+type);
                    return Optional.of(new ProfesorFullOutputDTO(profesorRepository.getById(id).orElse(new Profesor())));
                default:
                    throw new UnprocesableException("Parámetro '"+type+"' no reconocido (debe ser full/simple)");
            }
        }
        throw new NotFoundException("Profesor con ID: "+id+" no encontrado");
    }

    @Override
    public List<ProfesorSimpleOutputDTO> getAll() {
        List<ProfesorSimpleOutputDTO> profesorSimpleOutputDTOS = new ArrayList<>();
        profesorRepository.getAll().forEach(
                (profesor) -> profesorSimpleOutputDTOS.add( new ProfesorSimpleOutputDTO(profesor) ));
        return profesorSimpleOutputDTOS;
    }

    @Override
    public Optional<ProfesorSimpleOutputDTO> deleteById(String id) throws NotFoundException {
        if(profesorRepository.getById(id).isPresent()) {
            ProfesorSimpleOutputDTO profesorSimpleOutputDTO = new ProfesorSimpleOutputDTO(profesorRepository.getById(id).get());
            profesorRepository.deleteById(id);
            return Optional.of(profesorSimpleOutputDTO);
        }
        else
            throw new NotFoundException("Profesor con ID: "+id+" no encontrado");
    }

    @Override
    public Optional<ProfesorSimpleOutputDTO> edit(String id, ProfesorInputDTO profesorInputDTO) throws NotFoundException, UnprocesableException {
        Profesor oldProfesor = profesorRepository.getById(id).orElseThrow(
                () -> new NotFoundException("Student con ID: "+id+" no encontrado")
        );
        profesorInputDTO.setComments( profesorInputDTO.getComments() != null ? profesorInputDTO.getComments() : oldProfesor.getComments() );
        profesorInputDTO.setBranch( profesorInputDTO.getBranch() != null ? profesorInputDTO.getBranch() : oldProfesor.getBranch() );

        Persona persona = personaRepository.getById(profesorInputDTO.getId_persona()).orElseThrow(
                () -> new NotFoundException("Persona con ID: "+profesorInputDTO.getId_persona()+" no encontrada") );
        if(persona.getStudent() != null) throw new UnprocesableException("Un estudiante no puede ser profesor.");
        Profesor nuevoProfesor = new Profesor(profesorInputDTO, persona);
        nuevoProfesor.setId_profesor(id);
        return Optional.of(new ProfesorSimpleOutputDTO( profesorRepository.save(nuevoProfesor) ));
    }

    public boolean validateProfesor(ProfesorInputDTO profesorInputDTO) throws UnprocesableException {
        if(profesorInputDTO.getBranch() == null) throw new UnprocesableException("El branch no puede ser null");
        return true;
    }
}