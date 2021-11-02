package com.bosonit.springboot.db2.content.student.infraestructure.controller;

import com.bosonit.springboot.db2.config.exception.NotFoundException;
import com.bosonit.springboot.db2.content.student.application.port.StudentPort;
import com.bosonit.springboot.db2.content.student.infraestructure.controller.dto.input.StudentInputDTO;
import com.bosonit.springboot.db2.content.student.infraestructure.controller.dto.output.StudentSimpleOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentPort studentUseCase;

    @PostMapping
    public StudentSimpleOutputDTO addStudent(@RequestBody StudentInputDTO studentInputDTO){
        return studentUseCase.save(studentInputDTO).orElse( new StudentSimpleOutputDTO() );
    }

    @GetMapping("{id}") // READ
    public StudentSimpleOutputDTO getById(@PathVariable String id, @RequestParam(name = "outputType", defaultValue = "simple") String type ) {
        return studentUseCase.getById(id, type).orElseThrow(() -> new NotFoundException("No encontrada persona con id: " + id));
    }

    @GetMapping("all")
    public List<StudentSimpleOutputDTO> getAll()  {
        return studentUseCase.getAll();
    }

    @PutMapping("{id}")
    public StudentSimpleOutputDTO editById(@PathVariable String id, @RequestBody StudentInputDTO studentInputDTO) {
        return studentUseCase.edit(id, studentInputDTO).orElse( new StudentSimpleOutputDTO() );
    }

    @PutMapping("{id}/add")
    public StudentSimpleOutputDTO addAsignatura(@PathVariable String id, @RequestBody List<String> idAsignaturas){
        //System.out.println("LISTA: "+idAsignaturas);
        return studentUseCase.addAsignaturas(id, idAsignaturas).orElse( new StudentSimpleOutputDTO() );
    }

    @DeleteMapping("{id}/remove")
    public StudentSimpleOutputDTO removeAsignatura(@PathVariable String id, @RequestBody List<String> idAsignaturas){

        return studentUseCase.removeAsignaturas(id, idAsignaturas).orElse( new StudentSimpleOutputDTO() );
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable String id){
        studentUseCase.deleteById(id);
    }
}
