package com.bosonit.springboot.db2.content.profesor.infraestructure.controller;

import com.bosonit.springboot.db2.config.exception.NotFoundException;
import com.bosonit.springboot.db2.content.profesor.application.port.ProfesorPort;
import com.bosonit.springboot.db2.content.profesor.infraestructure.controller.dto.input.ProfesorInputDTO;
import com.bosonit.springboot.db2.content.profesor.infraestructure.controller.dto.output.ProfesorSimpleOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("profesor")
public class ProfesorController {
    @Autowired
    ProfesorPort profesorUseCase;

    @PostMapping
    public ProfesorSimpleOutputDTO create(@RequestBody ProfesorInputDTO profesorInputDTO){
        return profesorUseCase.save(profesorInputDTO).orElse( new ProfesorSimpleOutputDTO() );
    }

    @GetMapping("{id}") // READ
    public ProfesorSimpleOutputDTO getById(@PathVariable String id, @RequestParam(name = "outputType", defaultValue = "simple") String type ) {
        return profesorUseCase.getById(id, type).orElseThrow(() -> new NotFoundException("No encontrado profesor con id: " + id));
    }

    @GetMapping("all")
    public List<ProfesorSimpleOutputDTO> getAll()  {
        return profesorUseCase.getAll();
    }

    @PutMapping("{id}")
    public ProfesorSimpleOutputDTO editById(@PathVariable String id, @RequestBody ProfesorInputDTO profesorInputDTO) {
        return profesorUseCase.edit(id, profesorInputDTO).orElse( new ProfesorSimpleOutputDTO() );
    }

    @DeleteMapping("{id}")
    public ProfesorSimpleOutputDTO delete(@PathVariable String id){
        return profesorUseCase.deleteById(id).orElse(new ProfesorSimpleOutputDTO() );
    }
}
