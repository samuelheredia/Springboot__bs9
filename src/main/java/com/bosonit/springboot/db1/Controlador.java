package com.bosonit.springboot.db1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @Transactional(rollbackFor=Exception.class, propagation = Propagation.NOT_SUPPORTED)
    @PutMapping //UPDATE
    public Usuario editById(@RequestBody Usuario usuario){
        /*
        DefaultTransactionDefinition definition = null;
        TransactionStatus status = null;
        TransactionManager transactionManager;
        try{
            definition = new DefaultTransactionDefinition();
            status = transactionManager.getTransaction(definition);
            transactionManager.getTransaction(definition);
        } catch (Exception k){
            transactionManager.rollback(status);
        }
        */
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