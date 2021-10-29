package com.bosonit.springboot.db2.content.asignatura.application;

import com.bosonit.springboot.db2.config.exception.NotFoundException;
import com.bosonit.springboot.db2.config.exception.UnprocesableException;
import com.bosonit.springboot.db2.content.asignatura.application.port.AsignaturaPort;
import com.bosonit.springboot.db2.content.asignatura.domain.Asignatura;
import com.bosonit.springboot.db2.content.asignatura.infraestructure.controller.dto.input.AsignaturaInputDTO;
import com.bosonit.springboot.db2.content.asignatura.infraestructure.controller.dto.output.AsignaturaOutputDTO;
import com.bosonit.springboot.db2.content.asignatura.infraestructure.repository.port.AsignaturaPortRep;
import com.bosonit.springboot.db2.content.persona.domain.Persona;
import com.bosonit.springboot.db2.content.profesor.domain.Profesor;
import com.bosonit.springboot.db2.content.student.domain.Student;
import com.bosonit.springboot.db2.content.student.infraestructure.controller.dto.output.StudentSimpleOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaUseCase implements AsignaturaPort {

    @Autowired
    AsignaturaPortRep asignaturaRepository;

    @Override
    public Optional<AsignaturaOutputDTO> save(AsignaturaInputDTO asignaturaInputDTO) throws UnprocesableException {
        return Optional.of(new AsignaturaOutputDTO( asignaturaRepository.save(new Asignatura(asignaturaInputDTO) ) ));
    }

    @Override
    public Optional<AsignaturaOutputDTO> getById(String id) {
        return Optional.of(new AsignaturaOutputDTO(asignaturaRepository.getById(id).orElseThrow(
                () -> new NotFoundException("No encontrada asignatura con ID: "+id)
        )));
    }

    @Override
    public List<AsignaturaOutputDTO> getByStudentId(String idStudent) {
        List<AsignaturaOutputDTO> asignaturaOutputDTOS = new ArrayList<>();
        /*
        asignaturaRepository.getByStudentID(idStudent).forEach(
                (asignatura) -> asignaturaOutputDTOS.add( new AsignaturaOutputDTO(asignatura) )
        );

         */
        return asignaturaOutputDTOS;
    }

    @Override
    public List<AsignaturaOutputDTO> getAll() {
        List<AsignaturaOutputDTO> asignaturaOutputDTOS = new ArrayList<>();
        asignaturaRepository.getAll().forEach(
                (asignatura) -> asignaturaOutputDTOS.add( new AsignaturaOutputDTO(asignatura) )
        );
        return asignaturaOutputDTOS;
    }

    @Override
    public Optional<AsignaturaOutputDTO> deleteById(String id) throws NotFoundException {
        if(asignaturaRepository.getById(id).isPresent()) {
            AsignaturaOutputDTO asignaturaOutputDTO = new AsignaturaOutputDTO(asignaturaRepository.getById(id).get());
            asignaturaRepository.deleteById(id);
            return Optional.of(asignaturaOutputDTO);
        }
        else
            throw new NotFoundException("No encontrada asignatura con ID: "+id);
    }

    @Override
    public Optional<AsignaturaOutputDTO> edit(String id, AsignaturaInputDTO asignaturaInputDTO) throws NotFoundException, UnprocesableException {
        Asignatura oldAsignatura = asignaturaRepository.getById(id).orElseThrow(
                () -> new NotFoundException("No encontrada asignatura con ID: "+id)
        );

        Asignatura newAsignatura = new Asignatura(asignaturaInputDTO);
        newAsignatura.setId_asignatura(id);
        newAsignatura.setAsignatura(asignaturaInputDTO.getAsignatura() != null? asignaturaInputDTO.getAsignatura() : oldAsignatura.getAsignatura() );
        newAsignatura.setComents(asignaturaInputDTO.getComents() != null? asignaturaInputDTO.getComents() : oldAsignatura.getComents() );
        newAsignatura.setFinish_date(asignaturaInputDTO.getFinish_date() != null? asignaturaInputDTO.getFinish_date() : oldAsignatura.getFinish_date() );

        return Optional.of( new AsignaturaOutputDTO( asignaturaRepository.save(newAsignatura) ) );
    }
}
