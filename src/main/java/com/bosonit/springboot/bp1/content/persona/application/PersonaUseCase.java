package com.bosonit.springboot.bp1.content.persona.application;

import com.bosonit.springboot.bp1.content.persona.application.port.PersonaPort;
import com.bosonit.springboot.bp1.content.persona.domain.Persona;
import com.bosonit.springboot.bp1.content.persona.infraestructure.controller.dto.input.PersonaInputDTO;
import com.bosonit.springboot.bp1.content.persona.infraestructure.controller.dto.output.PersonaOutputDTO;
import com.bosonit.springboot.bp1.content.persona.infraestructure.repository.port.PersonaPortRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaUseCase implements PersonaPort {

    @Autowired
    PersonaPortRep personaPortRep;

    @Override
    public Optional<PersonaOutputDTO> save(PersonaInputDTO personaInputDTO) {
        try {
            if( validatePersona(personaInputDTO) )
                return Optional.of(
                        new PersonaOutputDTO(
                                personaPortRep.save(new Persona(personaInputDTO))
                                        .orElse(new Persona())));
        }
        catch (Exception e) { e.printStackTrace(); }
        return Optional.empty();
    }

    @Override
    public Optional<PersonaOutputDTO> getById(int id) {
        return Optional.of( new PersonaOutputDTO(personaPortRep.getById(id).orElse( new Persona() )) );
    }

    @Override
    public List<PersonaOutputDTO> getByName(String name) {
        List<PersonaOutputDTO> personaOutputDTOList = new ArrayList<>();
        personaPortRep.getByName(name).forEach(
            (persona) -> {
                personaOutputDTOList.add( new PersonaOutputDTO(persona) );
            });
        return personaOutputDTOList;
    }

    @Override
    public List<PersonaOutputDTO> getAll() {
        List<PersonaOutputDTO> personaOutputDTOList = new ArrayList<>();
        personaPortRep.getAll().forEach(
                (persona) -> {
                    personaOutputDTOList.add( new PersonaOutputDTO(persona) );
                });
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
            return Optional.empty();
    }

    @Override
    public Optional<PersonaOutputDTO> edit(int id, PersonaInputDTO personaInputDTO) {
        if(personaPortRep.getById(id).isPresent()) {

            try {
                validatePersona(personaInputDTO);
                return Optional
                        .of(new PersonaOutputDTO(personaPortRep
                                .save(new Persona(id, personaInputDTO)).orElse(new Persona())));
            } catch (Exception e) { e.printStackTrace(); }
        }
        return Optional.empty();
    }

    public boolean validatePersona(PersonaInputDTO personaInputDTO) throws Exception {
        if(personaInputDTO.getUsuario() == null) throw new NullPointerException("El usuario no puede ser null");
        if(personaInputDTO.getUsuario().length() > 10 || personaInputDTO.getUsuario().length() < 6 )
            throw new Exception("El usuario debe tener entre 6 y 10 caracteres");
        if(personaInputDTO.getPassword() == null) throw new NullPointerException("La password no puede ser null");
        if(personaInputDTO.getName() == null) throw new NullPointerException("El nombre no puede ser null");
        if(personaInputDTO.getCompany_email() == null) throw new NullPointerException("El mail de compañía no puede ser null");
        if(! personaInputDTO.getCompany_email().matches(".*@.*\\..*")) throw new Exception("Mail inválido en mail de compañía");
        if(personaInputDTO.getPersonal_email() == null) throw new NullPointerException("El mail personal no puede ser null");
        if(! personaInputDTO.getPersonal_email().matches(".*@.*\\..*")) throw new Exception("Mail inválido en mail personal");
        if(personaInputDTO.getActive() == null) throw new NullPointerException("La actividad no puede ser null");
        if(personaInputDTO.getCreated_date() == null) throw new NullPointerException("La fecha de creación no puede ser null");
        return true;
    }
}