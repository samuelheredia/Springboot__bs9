package com.bosonit.springboot.db2.content.core.proxy;

import com.bosonit.springboot.db2.content.profesor.infraestructure.controller.dto.output.ProfesorSimpleOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="simpleFeign", url ="http://localhost:8081")
public interface PersonasProxy {

    @GetMapping("/profesor/{id}")
    public ProfesorSimpleOutputDTO getProfesor(@PathVariable("id") String id);
}
