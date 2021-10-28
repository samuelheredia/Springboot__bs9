package com.bosonit.springboot.db2.content.persona.application;

import com.bosonit.springboot.db2.content.persona.application.port.PersonaPort;
import com.bosonit.springboot.db2.content.persona.domain.Persona;
import com.bosonit.springboot.db2.content.persona.infraestructure.controller.dto.input.PersonaInputDTO;
import com.bosonit.springboot.db2.content.persona.infraestructure.controller.dto.output.PersonaOutputDTO;
import com.bosonit.springboot.db2.content.persona.infraestructure.repository.port.PersonaPortRep;
import com.bosonit.springboot.db2.config.exception.NotFoundException;
import com.bosonit.springboot.db2.config.exception.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaUseCase implements PersonaPort {

    @Autowired
    PersonaPortRep personaPortRep;

    @Override
    public Optional<PersonaOutputDTO> save(PersonaInputDTO personaInputDTO) throws UnprocesableException {
        if( validatePersona(personaInputDTO) )
            return Optional.of(
                    new PersonaOutputDTO(
                            personaPortRep.save(new Persona(personaInputDTO)) ));
        return Optional.empty();
    }

    @Override
    public Optional<PersonaOutputDTO> getById(int id) {
        if(personaPortRep.getById(id).isPresent())
            return Optional.of( new PersonaOutputDTO(personaPortRep.getById(id).orElse( new Persona() )) );
        throw new NotFoundException("Persona con ID: "+id+" no encontrada");
    }

    @Override
    public List<PersonaOutputDTO> getByName(String name) {
        List<PersonaOutputDTO> personaOutputDTOList = new ArrayList<>();
        personaPortRep.getByName(name).forEach(
            (persona) -> personaOutputDTOList.add( new PersonaOutputDTO(persona) ));
        return personaOutputDTOList;
    }

    @Override
    public List<PersonaOutputDTO> getAll() {
        List<PersonaOutputDTO> personaOutputDTOList = new ArrayList<>();
        personaPortRep.getAll().forEach(
                (persona) -> personaOutputDTOList.add( new PersonaOutputDTO(persona) ));
        return personaOutputDTOList;
    }

    @Override
    public Optional<PersonaOutputDTO> deleteById(int id) {
        if(personaPortRep.getById(id).isPresent()) {
            PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(personaPortRep.getById(id).get());
            personaPortRep.deleteById(id);
            return Optional.of( personaOutputDTO );
        }
        else
            throw new NotFoundException("Persona con ID: "+id+" no encontrada");
    }

    @Override
    public Optional<PersonaOutputDTO> edit(int id, PersonaInputDTO personaInputDTO) throws UnprocesableException, NotFoundException {
        if(personaPortRep.getById(id).isPresent()) {
            Persona oldPersona = personaPortRep.getById(id).get();
            personaInputDTO.setActive( personaInputDTO.getActive() != null ? personaInputDTO.getActive() : oldPersona.getActive() );
            personaInputDTO.setCity( personaInputDTO.getCity() != null ? personaInputDTO.getCity() : oldPersona.getCity() );
            personaInputDTO.setCompany_email(personaInputDTO.getCompany_email() != null ? personaInputDTO.getCompany_email() : oldPersona.getCompany_email());
            personaInputDTO.setPersonal_email(personaInputDTO.getPersonal_email() != null ? personaInputDTO.getPersonal_email() : oldPersona.getPersonal_email());
            personaInputDTO.setImagen_url(personaInputDTO.getImagen_url() != null ? personaInputDTO.getImagen_url() : oldPersona.getImagen_url());
            personaInputDTO.setName(personaInputDTO.getName() != null ? personaInputDTO.getName() : oldPersona.getName());
            personaInputDTO.setPassword(personaInputDTO.getPassword() != null ? personaInputDTO.getPassword() : oldPersona.getPassword());
            personaInputDTO.setSurname(personaInputDTO.getSurname() != null ? personaInputDTO.getSurname() : oldPersona.getSurname());
            personaInputDTO.setTermination_date(personaInputDTO.getTermination_date() != null ? personaInputDTO.getTermination_date() : oldPersona.getTermination_date());
            personaInputDTO.setCreated_date( new Date() );
            return Optional
                    .of(new PersonaOutputDTO(personaPortRep
                        .save(new Persona(id, personaInputDTO)))); }
        else
            throw new NotFoundException("Persona con ID: "+id+" no encontrada");
    }

    public boolean validatePersona(PersonaInputDTO personaInputDTO) throws UnprocesableException {
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
}