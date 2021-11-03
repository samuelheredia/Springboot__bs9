package com.bosonit.springboot.db2.content.asignatura.infraestructure.controller;

import com.bosonit.springboot.db2.config.exception.NotFoundException;
import com.bosonit.springboot.db2.content.asignatura.application.port.AsignaturaPort;
import com.bosonit.springboot.db2.content.asignatura.infraestructure.controller.dto.input.AsignaturaInputDTO;
import com.bosonit.springboot.db2.content.asignatura.infraestructure.controller.dto.output.AsignaturaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("asignatura")
public class AsignaturaController {
    @Autowired
    AsignaturaPort asignaturaUseCase;

    @PostMapping
    public AsignaturaOutputDTO addAsignatura(@RequestBody AsignaturaInputDTO asignaturaInputDTO){
        return asignaturaUseCase.save(asignaturaInputDTO).orElse( new AsignaturaOutputDTO() );
    }

    @GetMapping("{id}") // READ
    public AsignaturaOutputDTO getById(@PathVariable String id) {
        return asignaturaUseCase.getById(id).orElseThrow(() -> new NotFoundException("No encontrada persona con id: " + id));
    }

    @GetMapping("student/{idStudent}") // READ
    public List<AsignaturaOutputDTO> getEstudiantes(@PathVariable String idStudent) {
        //return asignaturaUseCase.getById(id).orElseThrow(() -> new NotFoundException("No encontrada persona con id: " + id));
        return asignaturaUseCase.getByStudentId(idStudent);
    }

    @GetMapping("all")
    public List<AsignaturaOutputDTO> getAll()  {
        return asignaturaUseCase.getAll();
    }

    @PutMapping("{id}")
    public AsignaturaOutputDTO editById(@PathVariable String id, @RequestBody AsignaturaInputDTO asignaturaInputDTO) {
        return asignaturaUseCase.edit(id, asignaturaInputDTO).orElse( new AsignaturaOutputDTO() );
    }

    @DeleteMapping("{id}")
    public void deleteAsignatura(@PathVariable String id){
        asignaturaUseCase.deleteById(id);
    }
}