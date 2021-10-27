package com.bosonit.springboot.db2.content.student.infraestructure.controller.dto;

import com.bosonit.springboot.db2.config.exception.NotFoundException;
import com.bosonit.springboot.db2.content.persona.infraestructure.controller.dto.input.PersonaInputDTO;
import com.bosonit.springboot.db2.content.persona.infraestructure.controller.dto.output.PersonaOutputDTO;
import com.bosonit.springboot.db2.content.student.application.port.StudentPort;
import com.bosonit.springboot.db2.content.student.infraestructure.controller.dto.input.StudentInputDTO;
import com.bosonit.springboot.db2.content.student.infraestructure.controller.dto.output.StudentOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentPort studentUseCase;

    @PostMapping
    public StudentOutputDTO addStudent(@RequestBody StudentInputDTO studentInputDTO){
        return studentUseCase.save(studentInputDTO).orElse( new StudentOutputDTO() );
    }

    // Buscar persona por id
    @GetMapping("{id}") // READ
    public StudentOutputDTO getById(@PathVariable String id) {
        return studentUseCase.getById(id).orElseThrow(() -> new NotFoundException("No encontrada persona con id: "+id));
    }

    // Busca todas las personas
    @GetMapping("all")
    public List<StudentOutputDTO> getAll()  {
        return studentUseCase.getAll();
    }
}
