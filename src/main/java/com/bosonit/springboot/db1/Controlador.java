package com.bosonit.springboot.db1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controlador {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @PostMapping // CREATE
    public Usuario addUser(@RequestBody Usuario usuario){
        System.out.println("Creating");
        usuarioRepositorio.save(usuario);
        return usuario;
    }

    @GetMapping("{id}") // READ
    public Usuario getById(@PathVariable String id) throws Exception {
        System.out.println("Reading");
        return usuarioRepositorio.findById(id).orElseThrow(() -> new Exception("No encontrado"));
    }

    @PutMapping //UPDATE
    public Usuario editById(@RequestBody Usuario usuario){
        System.out.println("Updating");
        usuarioRepositorio.save(usuario);
        return usuario;
    }

    @DeleteMapping("{id}") //DELETE
    public Usuario deleteUser(@PathVariable String id){
        System.out.println("Deleting");
        Usuario ret = usuarioRepositorio.findById(id).orElse(new Usuario());
        usuarioRepositorio.deleteById(id);
        return ret;
    }


}
