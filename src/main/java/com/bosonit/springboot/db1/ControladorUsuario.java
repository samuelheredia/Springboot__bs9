package com.bosonit.springboot.db1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControladorUsuario {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @PostMapping("/usuario") // CREATE
    public Usuario addUser(@RequestBody Usuario usuario){
        System.out.println("Creating");
        usuarioRepositorio.save(usuario);
        return usuario;
    }

    @GetMapping("/usuario/{id}") // READ
    public Usuario getById(@PathVariable String id) throws Exception {
        System.out.println("Reading");
        return usuarioRepositorio.findById(id).orElseThrow(() -> new Exception("No encontrado"));
    }

    @Transactional(rollbackFor=Exception.class, propagation = Propagation.NOT_SUPPORTED)
    @PutMapping("/usuario") //UPDATE
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

    @DeleteMapping("/usuario/{id}") //DELETE
    public Usuario deleteUser(@PathVariable String id){
        System.out.println("Deleting");
        Usuario ret = usuarioRepositorio.findById(id).orElse(new Usuario());
        usuarioRepositorio.deleteById(id);
        return ret;
    }
}