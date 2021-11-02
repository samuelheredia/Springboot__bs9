package com.bosonit.springboot.db2.content.profesor.application;

import com.bosonit.springboot.db2.config.exception.NotFoundException;
import com.bosonit.springboot.db2.config.exception.UnprocesableException;
import com.bosonit.springboot.db2.content.Mapper;
import com.bosonit.springboot.db2.content.persona.application.port.PersonaPort;
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
    Mapper mapper;

    @Override
    public Optional<ProfesorSimpleOutputDTO> save(ProfesorInputDTO profesorInputDTO) {
        validateProfesor(profesorInputDTO);
        Profesor profesor = mapper.createProfesor(profesorInputDTO);
        if(profesor.getPersona().getStudent() != null) throw new UnprocesableException("El estudiante "+profesor.getPersona().getStudent().getId_student()
                +" no puede ser profesor");
        if(profesor.getPersona().getProfesor() != null) throw new UnprocesableException("La persona con ID "+profesor.getPersona().getId_persona()
                +" ya es profesor");
        return Optional.of(new ProfesorSimpleOutputDTO( profesorRepository.save(profesor) ));
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
    public Profesor getProfesorById(String id) {
        return profesorRepository.getById(id).orElseThrow(
                () -> new NotFoundException("No encontrado profesor con ID: "+id)
        );
    }

    @Override
    public Optional<ProfesorSimpleOutputDTO> edit(String id, ProfesorInputDTO profesorInputDTO) throws NotFoundException, UnprocesableException {

        Profesor oldProfesor = profesorRepository.getById(id).orElseThrow(
                () -> new NotFoundException("Student con ID: "+id+" no encontrado")
        );
        profesorInputDTO.setComments( profesorInputDTO.getComments() != null ? profesorInputDTO.getComments() : oldProfesor.getComments() );
        profesorInputDTO.setBranch( profesorInputDTO.getBranch() != null ? profesorInputDTO.getBranch() : oldProfesor.getBranch() );

        Profesor nuevoProfesor = mapper.createProfesor(profesorInputDTO);
        nuevoProfesor.setId_profesor(id);

        if(nuevoProfesor.getPersona().getStudent() != null) throw new UnprocesableException("El estudiante "+nuevoProfesor.getPersona().getStudent().getId_student()
                +" no puede ser profesor");
        if(nuevoProfesor.getPersona().getProfesor() != null) throw new UnprocesableException("La persona con ID "+nuevoProfesor.getPersona().getId_persona()
        +" ya es profesor");

        return Optional.of(new ProfesorSimpleOutputDTO( profesorRepository.save(nuevoProfesor) ));
    }

    public boolean validateProfesor(ProfesorInputDTO profesorInputDTO) throws UnprocesableException {
        if(profesorInputDTO.getBranch() == null) throw new UnprocesableException("El branch no puede ser null");
        return true;
    }
}